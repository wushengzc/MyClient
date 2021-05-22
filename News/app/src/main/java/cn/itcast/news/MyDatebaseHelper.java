package cn.itcast.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatebaseHelper extends SQLiteOpenHelper {

    // 创建classmate的sql语句
    private static final String CREATE_TABLE = "CREATE TABLE classmate(" +
            "id integer primary key autoincrement, " +
            "name text, " +
            "path integer, " +
            "jiguan text)";

    // 要插入classmate的数据
    private static String[] names = new String[]{"白龙马", "唐僧", "沙僧", "猪八戒", "孙悟空"};
    private static int[] paths = {R.drawable.bailongma, R.drawable.tangseng, R.drawable.shaseng, R.drawable.zhubajie, R.drawable.sunwukong};
    private static String[] jiguan = new String[]{"妖族", "人族", "人族", "神仙", "猴子"};

    private Context mContext;

    public MyDatebaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mContext, "创建数据库成功", Toast.LENGTH_SHORT).show();
        insertData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 往classmate插入数据
    private void insertData(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        for(int i = 0; i < names.length; i++){
            values.put("name", names[i]);
            values.put("path", paths[i]);
            values.put("jiguan", jiguan[i]);
            db.insert("classmate", null, values);
            values.clear();
        }
    }
}
