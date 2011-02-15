package com.animoto.api;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpStatus;

import com.animoto.api.util.DirectingManifestFactory;
import com.animoto.api.util.RenderingManifestFactory;

import com.animoto.api.resource.BaseResource;
import com.animoto.api.resource.DirectingJob;
import com.animoto.api.resource.RenderingJob;
import com.animoto.api.resource.DirectingAndRenderingJob;
import com.animoto.api.resource.Storyboard;
import com.animoto.api.resource.StoryboardBundlingJob;
import com.animoto.api.resource.Video;

import com.animoto.api.DirectingManifest;
import com.animoto.api.RenderingManifest;
import com.animoto.api.RenderingParameters;

import com.animoto.api.visual.TitleCard;
import com.animoto.api.visual.Image;

import com.animoto.api.exception.ApiException;
import com.animoto.api.exception.ContractException;
import com.animoto.api.exception.HttpExpectationException;
import com.animoto.api.exception.HttpException;

import com.animoto.api.enums.Framerate;

public class ApiClientIntegrationTest extends TestCase {
  protected ApiClient apiClient = null;

  public void setUp() {
    apiClient = ApiClientFactory.newInstance();
  }

  public void testDirecting() {
    createDirectingJob();
  }

  public void testDelete() throws HttpException, HttpExpectationException, ContractException {
    DirectingJob directingJob = createDirectingJob();
    Storyboard storyboard = directingJob.getStoryboard();

    apiClient.reload(storyboard);
    apiClient.delete(storyboard);

    try {
      apiClient.delete(storyboard); // Should fail (already deleted)
    } catch(HttpExpectationException e) {
      System.out.println("Expected exception when deleting storyboard twice: " + e);
      assertEquals(e.getReceivedCode(), HttpStatus.SC_GONE);
    }

    try {
      apiClient.reload(storyboard); // Should fail, since we deleted the storyboad
    } catch(HttpExpectationException e) {
      System.out.println("Expected exception when trying reload after delete: " + e);
      assertEquals(e.getReceivedCode(), HttpStatus.SC_GONE);
    }
  }

  public void testHttpExceptionThrownOnNetworkIssues() {
    try {
      apiClient.setHost("http://nowhere.com");
      apiClient.direct(DirectingManifestFactory.newInstance());
      fail("Expected exception to be thrown!");
    }
    catch (HttpException e) {
      assertTrue(e.getException() instanceof java.net.UnknownHostException);
    }
    catch (Exception e) {
      fail(e.toString());
    }
  }

  public void testBundle() throws HttpExpectationException, HttpException, ContractException {
    DirectingJob directingJob = createDirectingJob();
    Storyboard storyboard = directingJob.getStoryboard();

    StoryboardBundlingManifest manifest = new StoryboardBundlingManifest();
    manifest.setStoryboard(storyboard);

    StoryboardBundlingJob bundlingJob = apiClient.bundle(manifest);

    assertNotNull(bundlingJob);
    assertNotNull(bundlingJob.getLocation());
    assertNotNull(bundlingJob.getRequestId());
    //assertEquals("bundling", directingJob.getState());

    //waitForJobCompletion(bundlingJob);
  }

  public void testStoryboard() {
    DirectingJob directingJob = createDirectingJob();
    Storyboard storyboard = directingJob.getStoryboard();

    try {
      apiClient.reload(storyboard);
      assertNotNull(storyboard.getLinks());
      assertTrue(storyboard.getLinks().size() > 0);
      assertNotNull(storyboard.getMetadata());
    }
    catch (Exception e) {
      fail(e.toString());
    }
  }

  public void testDirectingInterceptor() throws Exception {
    DirectingManifest directingManifest = DirectingManifestFactory.newInstance();
    List<HttpRequestInterceptor> list = new ArrayList<HttpRequestInterceptor>();
    DummyHttpRequestInterceptor interceptor = new DummyHttpRequestInterceptor();

    list.add(interceptor);
    apiClient.direct(directingManifest, null, null, null, list);
    assertTrue(interceptor.isVisited());
  }

  public void testDirectingFail() throws Exception {
    DirectingJob directingJob = null;
    DirectingManifest directingManifest = DirectingManifestFactory.newInstance();
    Image image = new Image();
    ApiError[] apiErrors = null;

    try {
      image.setSourceUrl("http://bad.com/link.gif");
      directingManifest.clearVisuals();
      directingManifest.addVisual(image);
      directingJob = apiClient.direct(directingManifest);

      waitForJobCompletion(directingJob);

      assertTrue(directingJob.isFailed());
      assertNotNull(directingJob.getResponse());
      apiErrors = directingJob.getResponse().getStatus().getApiErrors();
      assertTrue(apiErrors.length > 0);
    }
    catch (Exception e) {
      fail(e.toString());
    }
  }

  public void testRenderingRaisedException() throws Exception {
    DirectingJob directingJob = createDirectingJob();
    RenderingJob renderingJob = null;
    RenderingManifest renderingManifest = new RenderingManifest();
    RenderingParameters renderingParameters = new RenderingParameters();

    renderingParameters.setFramerate(Framerate.F_30);
    renderingManifest.setStoryboard(directingJob.getStoryboard());
    renderingManifest.setRenderingParameters(renderingParameters);
    try {
      renderingJob = apiClient.render(renderingManifest);
      fail("Expected error from API!");
    }
    catch (HttpExpectationException e) {
      assertEquals(201, e.getExpectedCode());
      assertEquals(400, e.getReceivedCode());
      assertNotNull(e.getApiErrors());
      assertNotNull(e.getBody());
      assertEquals(4, e.getApiErrors().length);
    }
    catch (Exception e) {
      throw e;
    }
  }

  public void testRenderingJob() {
    createRenderingJob();
  }

  public void testVideo() {
    RenderingJob renderingJob = createRenderingJob();
    Video video = null;

    try {
      video = renderingJob.getVideo();
      apiClient.reload(video);
      assertNotNull(video.getLinks());
      assertTrue(video.getLinks().size() > 0);
    }
    catch (Exception e) {
      fail(e.toString());
    }
  }

  public void testDirectingAndRendering() {
    DirectingAndRenderingJob directingAndRenderingJob;
    DirectingManifest directingManifest = DirectingManifestFactory.newInstance();
    RenderingManifest renderingManifest = RenderingManifestFactory.newInstance();

    try {
      directingAndRenderingJob = apiClient.directAndRender(directingManifest, renderingManifest);

      waitForJobCompletion(directingAndRenderingJob);

      assertTrue(directingAndRenderingJob.isCompleted());
      assertNotNull(directingAndRenderingJob.getStoryboard());
      assertNotNull(directingAndRenderingJob.getVideo());
    }
    catch (Exception e) {
      fail(e.toString());
    }
  }

  protected DirectingJob createDirectingJob() {
    DirectingManifest directingManifest = DirectingManifestFactory.newInstance();
    DirectingJob directingJob = null;

    try {
      // Post a directing job to the API.
      directingJob = apiClient.direct(directingManifest);
      assertNotNull(directingJob);
      assertNotNull(directingJob.getLocation());
      assertNotNull(directingJob.getRequestId());
      assertEquals("retrieving_assets", directingJob.getState());

      waitForJobCompletion(directingJob);

      // Job is complete!
      assertTrue(directingJob.isCompleted());
      assertNotNull(directingJob.getStoryboard());
      assertNotNull(directingJob.getResponse());
      assertNotNull(directingJob.getStoryboard().getLocation());
    }
    catch (Exception e) {
      fail(e.toString());
    }
    return directingJob;
  }

  protected RenderingJob createRenderingJob() {
    DirectingJob directingJob = createDirectingJob();
    RenderingJob renderingJob = null;
    RenderingManifest renderingManifest = RenderingManifestFactory.newInstance();

    try {
      renderingManifest.setStoryboard(directingJob.getStoryboard());
      renderingJob = apiClient.render(renderingManifest);
      assertNotNull(renderingJob.getLocation());
      assertNotNull(renderingJob.getRequestId());

      waitForJobCompletion(renderingJob);

      assertTrue(renderingJob.isCompleted());
      assertNotNull(renderingJob.getVideo());
      assertNotNull(renderingJob.getStoryboard());
    }
    catch (Exception e) {
      fail(e.toString());
    }
    return renderingJob;
  }

  /*
   * TODO: Consider making this part of ApiClient; how long to sleep
   * between polling attempts certainly is a best practices issue.
   * Thought: should we poll for a very short amount of time initially
   * (say 50 ms) and slowly increase the polling interval (adaptive
   * polling)?
   */
  private void waitForJobCompletion(BaseResource job) throws HttpException, HttpExpectationException, ContractException {
    assertTrue(job.isPending());

    while(job.isPending()) {
      assertFalse(job.isCompleted());
      assertFalse(job.isFailed());
      try {
        Thread.sleep(1000);
      }
      catch (Exception ignored) {}
      apiClient.reload(job);
    }

    assertTrue(job.isCompleted() || job.isFailed());
    assertEquals(job.isCompleted(), !job.isFailed());
  }
}
