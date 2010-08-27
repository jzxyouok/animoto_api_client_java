package com.animoto.api.enums;

public enum VerticalResolution {
  VR_180P("180p"), VR_240P("240p"), VR_360P("360p"), VR_480P("480p"), VR_720P("720p"), VR_1080P("1080p");

  private String value;

  VerticalResolution(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
