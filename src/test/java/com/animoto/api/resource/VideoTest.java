package com.animoto.api.resource;

import junit.framework.TestCase;

import com.animoto.api.RenderingParameters;
import com.animoto.api.resource.Video;
import com.animoto.api.enums.Framerate;
import com.animoto.api.enums.Resolution;
import com.animoto.api.enums.Format;

public class VideoTest extends TestCase {
  public void testFromJson() {
    Video video = new Video();
    String json = "{\"response\":{\"payload\":{\"video\":{\"metadata\":{\"rendering_parameters\":{\"resolution\":\"720p\",\"format\":\"h264\",\"framerate\":30}},\"links\":{\"self\":\"https://api2-sandbox.animoto.com/videos/4ced2532f0c886406200062d\",\"storyboard\":\"https://api2-sandbox.animoto.com/storyboards/4ced252cf0c88655f6000002\",\"file\":\"http://testconsumer1-4c64-sa-0.s3.amazonaws.com/Video/4ced2532f0c886406200062d/720p_3c2a.mp4?Signature=ExDB6T%2F15ldF9mcwBfCz1XIXIl0%3D&Expires=1290631624&AWSAccessKeyId=AKIAI7FBSVU753FBARAQ\"}}},\"status\":{\"code\":200}}}";

    try {
      video.fromJson(json); 
    }
    catch (Exception e) {
      fail(e.toString());
    }
    assertNotNull(video.getMetadata());
    assertNotNull(video.getMetadata().getRenderingParameters());

    RenderingParameters renderingParameters = video.getMetadata().getRenderingParameters();
    assertEquals(Framerate.F_30, renderingParameters.getFramerate()); 
    assertEquals(Resolution.R_720P, renderingParameters.getResolution());
    assertEquals(Format.H264, renderingParameters.getFormat()); 
  }
}
