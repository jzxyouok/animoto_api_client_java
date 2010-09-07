package com.animoto.api.util;

import com.animoto.api.DirectingManifest;
import com.animoto.api.Song;
import com.animoto.api.visual.*;
import com.animoto.api.enums.*;

public class DirectingManifestFactory {
  public static DirectingManifest newInstance() {
    DirectingManifest directingManifest = new DirectingManifest();
    Image image = new Image();
    TitleCard titleCard = new TitleCard();
    Footage footage = new Footage();
    Song song = new Song();
    String json = null;

    song.setSourceUrl("http://api.client.java.animoto.s3.amazonaws.com/test_assets/song.mp3");
    song.setDuration(new Float(120));
    song.setStartTime(new Float(5));
    directingManifest.setSong(song);

    image.setSourceUrl("http://api.client.java.animoto.s3.amazonaws.com/test_assets/image.jpg");
    image.setRotation(Rotation.TWO);
    image.setCover(true);
    directingManifest.addVisual(image);

    titleCard.setH1("hello");
    titleCard.setH2("world");
    directingManifest.addVisual(titleCard);

    footage.setSourceUrl("http://api.client.java.animoto.s3.amazonaws.com/test_assets/footage.mp4");
    footage.setAudioMix(AudioMix.MIX);
    directingManifest.addVisual(footage);

    directingManifest.setTitle("My Animoto Video");
    directingManifest.setPacing(Pacing.HALF);
    return directingManifest;
  }
}
