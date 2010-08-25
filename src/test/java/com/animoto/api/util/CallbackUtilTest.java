package com.animoto.api.util;

import junit.framework.TestCase;

import com.animoto.api.resource.Resource;
import com.animoto.api.resource.RenderingJob;

public class CallbackUtilTest extends TestCase {
  public void testGenerateFromJson() throws Exception {
    String json = "{\"response\":{\"payload\":{\"rendering_job\":{\"state\":\"completed\",\"links\":{\"self\":\"https://api2-sandbox.animoto.com/jobs/rendering/4c75768a534c491de7000014\",\"storyboard\":\"https://api2-sandbox.animoto.com/storyboards/4c757684534c4961b1000001\",\"video\":\"https://api2-sandbox.animoto.com/videos/4c75768b534c4961ba000001\"}}}}}";
    Resource resource = CallbackUtil.generateFromJson(json);
    assertTrue(resource instanceof RenderingJob);
    RenderingJob renderingJob = (RenderingJob) resource;
    assertEquals("https://api2-sandbox.animoto.com/videos/4c75768b534c4961ba000001", renderingJob.getLinks().get("video"));
    assertTrue(renderingJob.isCompleted());
    assertEquals("https://api2-sandbox.animoto.com/storyboards/4c757684534c4961b1000001", renderingJob.getLinks().get("storyboard"));
    assertNotNull(renderingJob.getStoryboard());
    assertEquals("https://api2-sandbox.animoto.com/jobs/rendering/4c75768a534c491de7000014", renderingJob.getUrl());
  }
}
