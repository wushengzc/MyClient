package cn.itcast.news;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClassmateListActivity extends AppCompatActivity {

    private MyDatebaseHelper dbHelper = new MyDatebaseHelper(this, "classmate", null, 1);
    private List<Classmate> classmateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_list);

        ListView listView = (ListView) findViewById(R.id.classmate);

        // 从SQLite获取classmate表的数据，并存储在classmateList
        getDataFromSQLite();

        // 创建适配器
        ClassmateAdapter classmateAdapter = new ClassmateAdapter(ClassmateListActivity.this, R.layout.classmate_item, classmateList);
        // 设置适配器
        listView.setAdapter(classmateAdapter);
    }

    private void getDataFromSQLite(){
        // 获取SQLite读写对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("classmate", null, null, null, null, null, null);

        String name = "";
        int path;
        String jiguan = "";

        if(cursor.moveToFirst()){
            do {
                name = cursor.getString(cursor.getColumnIndex("name"));
                path = cursor.getInt(cursor.getColumnIndex("path"));
                jiguan = cursor.getString(cursor.getColumnIndex("jiguan"));

                // 创建Classmate实例
                Classmate classmate = new Classmate(name, path, jiguan);
                // 添加到classmateList
                classmateList.add(classmate);

            } while(cursor.moveToNext());
        }

        // 关闭连接
        cursor.close();
        db.close();
    }

    class Classmate {
        private String name;
        private int path;
        private String jiguan;

        public Classmate(String name, int path, String jiguan){
            this.name = name;
            this.path = path;
            this.jiguan = jiguan;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPath() {
            return path;
        }

        public void setPath(int path) {
            this.path = path;
        }

        public String getJiguan() {
            return jiguan;
        }

        public void setJiguan(String jiguan) {
            this.jiguan = jiguan;
        }
    }
}