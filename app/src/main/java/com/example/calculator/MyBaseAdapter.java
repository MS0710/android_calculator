package com.example.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyBaseAdapter extends BaseAdapter {

    private List<Item> mList;
    private Context mContext;

    public MyBaseAdapter(Context context,List<Item> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_view,null);

            viewHolder.title = (TextView)view.findViewById(R.id.tv_title);
            viewHolder.content = (TextView)view.findViewById(R.id.tv_content);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        Item item = mList.get(position);
        viewHolder.title.setText(item.itemTitle);
        viewHolder.content.setText(item.itemContent);

        return view;
    }

    class ViewHolder{
        public TextView title;
        public TextView content;
    }
}
