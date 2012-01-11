package com.animoto.api;

import com.animoto.api.Postroll;

public class CustomFootagePostroll extends Postroll {
  private String sourceUrl;

  public CustomFootagePostroll() {
    template = "custom_footage";
  }

  public void setSourceUrl(String sourceUrl) {
    this.sourceUrl = sourceUrl;
  }

  public String getSourceUrl() {
    return this.sourceUrl;
  }
}
