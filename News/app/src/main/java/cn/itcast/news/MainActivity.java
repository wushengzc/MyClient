package cn.itcast.news;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private LinearLayout loading;
    private ListView lvNews;
    private List<NewsInfo> newsInfos;
    private TextView tv_title;
    private TextView tv_description;
    private NewsInfo newsInfo;
    private SmartImageView siv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillData();
    }

    //初始化控件
    private void initView() {
        // 加载进度条
        loading = (LinearLayout) findViewById(R.id.loading);
        // 找到ListView
        lvNews = (ListView) findViewById(R.id.lv_news);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    // 音乐播放器
                    case 0:
                        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                        startActivity(intent);
                        break;
                    // 视频播放器
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(intent1);
                        break;
                    // 网络图片
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, ImageActivity.class);
                        startActivity(intent2);
                        break;
                    // 同学录，ListView使用
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, ClassmateListActivity.class);
                        startActivity(intent3);
                        break;
                    // 补间动画
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, AnimationActivity.class);
                        startActivity(intent4);
                        break;
                    // 逐帧动画
                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, FrameActivity.class);
                        startActivity(intent5);
                        break;
                    // 抽屉动画
                    case 6:
                        Intent intent6 = new Intent(MainActivity.this, DrawlayoutActivity.class);
                        startActivity(intent6);
                        break;
                }
            }
        });
    }



    //使用AsyncHttpClient访问网络
    private void fillData() {

        //创建AsyncHttpClient实例
        AsyncHttpClient client = new AsyncHttpClient();
        //使用GET方式请求
        client.get(getString(R.string.serverurl), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                //请求成功

                try {
                    // UTF-8解析返回的json
                    String json = new String(bytes, "utf-8");
                    // JSON解析
                    newsInfos = JsonParse.getNewsInfo(json);
                    if (newsInfos == null) {
                        Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        //更新界面
                        // 隐藏进度条
                        loading.setVisibility(View.INVISIBLE);
                        // 设置适配器
                        lvNews.setAdapter(new NewsAdapter());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    //ListView适配器
    private class NewsAdapter extends BaseAdapter {
        //listview的item数
        @Override
        public int getCount() {
            return newsInfos.size();
        }

        //得到listview条目视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // 加载布局文件，转换成View对象
            View view = View.inflate(MainActivity.this, R.layout.news_item, null);

            // 从Item对应的View对象中获取4个控件
            siv = (SmartImageView) view.findViewById(R.id.siv_icon);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            newsInfo = newsInfos.get(position);

            //SmartImageView加载指定路径图片
            siv.setImageUrl(newsInfo.getIcon(), R.drawable.ic_launcher, R.drawable.ic_launcher);

            //设置新闻标题
            tv_title.setText(newsInfo.getTitle());

            //设置新闻描述
            tv_description.setText(newsInfo.getContent());
            return view;
        }

        //条目对象
        @Override
        public Object getItem(int position) {
            return null;
        }

        //条目id
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
