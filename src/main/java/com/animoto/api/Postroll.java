package com.animoto.api;

public class Postroll {
  public static final String POWERED_BY_ANIMOTO = "powered_by_animoto";
  public static final String WHITE_LABEL = "white_label";

  protected String template;

  public Postroll() {
    template = Postroll.POWERED_BY_ANIMOTO;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public String getTemplate() {
    return this.template;
  }
}
