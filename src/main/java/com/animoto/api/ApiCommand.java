package com.animoto.api;

import java.util.Collection;

import com.animoto.api.resource.*;
import com.animoto.api.enums.HttpCallbackFormat;
import com.animoto.api.PartnerMetadata;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpRequestRetryHandler;

public class ApiCommand {

  private BaseResource baseResource;
  private String endpoint;
  private String httpCallback;
  private HttpCallbackFormat httpCallbackFormat;
  private HttpRequestRetryHandler httpRequestRetryHandler;
  private Collection<HttpRequestInterceptor> httpRequestInterceptors;
  private PartnerMetadata partnerMetadata;

  public void setBaseResource(BaseResource baseResource) {
    this.baseResource = baseResource;

    /*
     * TODO: This really should be made into a polymorphic call on some kind of a Job base class
     */
    if (baseResource instanceof DirectingJob) {
      setEndpoint("directing");
    }
    else if (baseResource instanceof RenderingJob) {
      setEndpoint("rendering");
    }
    else if (baseResource instanceof DirectingAndRenderingJob) {
      setEndpoint("directing_and_rendering");
    }
    else if (baseResource instanceof StoryboardBundlingJob) {
      setEndpoint("storyboard_bundling");
    }
    else if (baseResource instanceof StoryboardUnbundlingJob) {
      setEndpoint("storyboard_unbundling");
    }
  }

  public BaseResource getBaseResource() {
    return baseResource;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setHttpCallback(String httpCallback) {
    this.httpCallback = httpCallback;
  }

  public String getHttpCallback() {
    return httpCallback;
  }

  public void setHttpCallbackFormat(HttpCallbackFormat httpCallbackFormat) {
    this.httpCallbackFormat = httpCallbackFormat;
  }

  public HttpCallbackFormat getHttpCallbackFormat() {
    return httpCallbackFormat;
  }

  public void setPartnerMetadata(PartnerMetadata partnerMetadata){
    this.partnerMetadata = partnerMetadata;
  }

  public PartnerMetadata getPartnerMetadata() {
    return partnerMetadata;
  }

  public void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
    this.httpRequestRetryHandler = httpRequestRetryHandler;
  }

  public HttpRequestRetryHandler getHttpRequestRetryHandler() {
    return httpRequestRetryHandler;
  }

  public void setHttpRequestInterceptors(Collection<HttpRequestInterceptor> httpRequestInterceptors) {
    this.httpRequestInterceptors = httpRequestInterceptors;
  }

  public Collection<HttpRequestInterceptor> getHttpRequestInterceptors() {
    return httpRequestInterceptors;
  }
}
