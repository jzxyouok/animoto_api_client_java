package com.animoto.api.resource;

/**
 * This abstract resource represents API resources that only support HTTP GET.<p/>
 *
 * Current resources that extend this class are Storyboard and Video.<p/>
 *
 * Directing and rendering are operations that are both POST and GET.<p/>
 *
 * @see Storyboard
 * @see Video
 */
public abstract class BaseHttpGetOnlyResource extends BaseResource {
  public String getContentType() {
    throw new Error("Not supported for com.animoto.api.resource.BaseGetOnlyResource!");
  }
}
