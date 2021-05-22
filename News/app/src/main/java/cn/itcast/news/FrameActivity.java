package cn.itcast.news;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_flower;
    private Button btn_start;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        iv_flower = (ImageView) findViewById(R.id.iv_flower);
        btn_start = (Button) findViewById(R.id.btn_play);
        btn_start.setOnClickListener(this);
        //拿到AnimationDrawable对象
        animation = (AnimationDrawable) iv_flower.getBackground();
    }

    public void onClick(View v) {
        //判断动画是否在播放
        if (!animation.isRunning()) {
            //动画没有在播放状态,则播放
            animation.start();
            btn_start.setBackgroundResource(android.R.drawable.ic_media_pause);
        } else {
            //动画在播放状态,则停止
            animation.stop();
            btn_start.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }
}