package cn.itcast.news;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView imageView;
    private HttpURLConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = (ImageView) findViewById(R.id.picture);

        String path = "http://192.168.2.113/img/b.jpg";
        // 开启子线程获取图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    // 创建目标路径的URL对象
                    URL url = new URL(path);

                    // 创建HttpURLConnection对象
                    conn = (HttpURLConnection) url.openConnection();

                    // 设置请求头部
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code = conn.getResponseCode();

                    if(code == 200){
                        // 获取返回的数据流
                        InputStream in = conn.getInputStream();
                        // bitmap解析数据
                        bitmap = (Bitmap) BitmapFactory.decodeStream(in);
                        // 子线程不能更新UI，
                        showImage(bitmap);
                    } else {
                        Toast.makeText(ImageActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    conn.disconnect();
                }
            }
        }).start();
    }

    private void showImage(final Bitmap bm){
        // runOnUiThread回到主线程更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bm);
            }
        });
    }
}