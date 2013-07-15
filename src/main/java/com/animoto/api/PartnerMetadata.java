package com.animoto.api;

import java.util.List;
import java.util.ArrayList;
import com.animoto.api.ApplicationData;

/**
 * Partner Metadata contains the Animoto user and video information. The details include - 
 *    String: 'partnerUserId', Boolean: 'commercialUse', 
 *    String: 'partnerIntent', ApplicationData: applicationData
 */

public class PartnerMetadata{
  private String partnerUserId;
  private Boolean commercialUse;
  private String partnerIntent;
  private ApplicationData applicationData;


  public void setPartnerUserId(String partnerUserId) {
    this.partnerUserId = partnerUserId;
  }

  public String getPartnerUserId() {
    return partnerUserId;
  }


  public void setCommercialUse(Boolean commercialUse) {
    this.commercialUse = commercialUse;
  }

  public Boolean getCommercialUse() {
    return commercialUse;
  }

  public void setPartnerIntent(String partnerIntent) {
    this.partnerIntent = partnerIntent;
  }

  public String getPartnerIntent() {
    return partnerIntent;
  }

  public void setApplicationData(ApplicationData applicationData) {
    this.applicationData = applicationData;
  }

  public ApplicationData getApplicationData() {
    return applicationData;
  }
}
