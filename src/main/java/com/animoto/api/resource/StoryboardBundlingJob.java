package com.animoto.api.resource;

import com.animoto.api.Jsonable;
import com.animoto.api.StoryboardBundlingManifest;

/**
 * A StoryboardBundlingJob represents the status of your storyboardBundling job on the API.<p/>
 *
 * You will need to call ApiClient.reload() in order to obtain the latest information from API.<p/>
 *
 * When the directing job is complete, a StoryboardBundle should be available.
 *
 * @see com.animoto.api.ApiClient
 * @see StoryboardBundle
 */
public class StoryboardBundlingJob extends BaseResource implements Jsonable {
  private StoryboardBundlingManifest storyboardBundlingManifest;

  public String getContentType() {
    return "application/vnd.animoto.storyboard_bundling_manifest-v1+json";
  }

  public String getAccept() {
    return "application/vnd.animoto.storyboard_bundling_job-v1+json";
  }

  public void setStoryboardBundlingManifest(StoryboardBundlingManifest storyboardBundlingManifest) {
    this.storyboardBundlingManifest = storyboardBundlingManifest;
  }

  public StoryboardBundlingManifest getStoryboardBundlingManifest() {
    return storyboardBundlingManifest;
  }

  public String toJson() {
    return newGson().toJson(new Container(this));
  }

  protected boolean containsStoryboard() {
    return false;
  }

  protected boolean containsVideo() {
    return false;
  }

  /**
   * Allows for a Gson to reflect the outer class context into JSON.
   */
  private class Container {
    @SuppressWarnings("unused")
    private StoryboardBundlingJob storyboardBundlingJob;

    public Container(StoryboardBundlingJob storyboardBundlingJob) {
      this.storyboardBundlingJob = storyboardBundlingJob;
    }
  }
}
