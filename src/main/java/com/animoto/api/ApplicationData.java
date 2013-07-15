package com.animoto.api;

import java.util.List;
import java.util.ArrayList;

/**
 * Application data contains the details of your Animoto video. The details include - 
 *    String: 'title', String: 'id', String: 'kind', String: 'type'
 */

public class ApplicationData {
  private String title;
  private String id;
  private String kind;
  private String type;


  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }


  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getKind() {
    return kind;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
