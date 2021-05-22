package cn.itcast.news;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener  {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button play = (Button) findViewById(R.id.play_v);
        Button pause = (Button) findViewById(R.id.pause_v);
        Button replay = (Button) findViewById(R.id.replay_v);
        videoView = (VideoView) findViewById(R.id.video_view);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);

        // Uri根据视频资源路径创建
        Uri uri = Uri.parse("http://192.168.2.113/movie.mp4");
        // setVideoURI加载视频资源
        videoView.setVideoURI(uri);
        // 视频控制条
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_v:
                if(!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.pause_v:
                if(videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.replay_v:
                if(videoView.isPlaying()){
                    // 重置视频进度
                    videoView.resume();
                }
                break;
        }
    }


}