package com.wiltech.novamaxapp.video;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.wiltech.novamaxapp.R;

public class VideoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //get the player ready
        final VideoView vdoTutorial = (VideoView)findViewById(R.id.vdoTutorial);
       // vdoTutorial.setVideoPath("https://www.youtube.com/watch?v=AZ_crUAUCxY");
        //vdoTutorial.setVideoPath("http://download.itcuties.com/teaser/itcuties-teaser-480.mp4");

        vdoTutorial.setVideoPath("https://www.thenewboston.com/forum/project_files/006_testVideo.mp4");

        //Player controls
        MediaController mcontroler = new MediaController(this);
        mcontroler.setAnchorView(vdoTutorial);
        vdoTutorial.setMediaController(mcontroler);

        vdoTutorial.start();
    }



}
