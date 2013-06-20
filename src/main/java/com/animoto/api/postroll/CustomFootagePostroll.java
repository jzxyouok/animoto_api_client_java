package com.animoto.api.postroll;
import com.animoto.api.postroll.Postroll;
import com.animoto.api.Jsonable;

public class CustomFootagePostroll extends Postroll implements Jsonable {

  public CustomFootagePostroll() {
    template = "custom_footage";
  }

  public void setSourceUrl(String sourceUrl) {
    this.sourceUrl = sourceUrl;
  }

  public String getSourceUrl() {
    return this.sourceUrl;
  }

  public void setStartTime(Float startTime) {
    this.startTime = startTime;
  }

  public Float getStartTime() {
    return this.startTime;
  }

  public void setDuration(Float duration) {
    this.duration = duration;
  }
  
  public Float getDuration() {
    return this.duration;
  }


  @Override
  public void setTemplate(String template) throws RuntimeException {
    throw(new RuntimeException("The template of a CustomFootagePostroll cannot be changed"));
  }
}
