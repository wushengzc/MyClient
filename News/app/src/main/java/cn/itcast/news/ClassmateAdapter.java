package cn.itcast.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClassmateAdapter extends ArrayAdapter {

    private int resourceId;

    public ClassmateAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClassmateListActivity.Classmate classmate = (ClassmateListActivity.Classmate) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.clm_image);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView jiguanView = (TextView) view.findViewById(R.id.jiguan);
        imageView.setImageResource(classmate.getPath());
        nameView.setText("姓名：" + classmate.getName());
        jiguanView.setText("籍贯：" + classmate.getJiguan());
        return view;
    }
}
