package com.example.volley;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class CustomAdapter extends BaseAdapter {

    ArrayList<pojo> arrayList;
    LayoutInflater layoutInflater;
    Context context;


    public CustomAdapter(Context context, ArrayList<pojo> arrayList) {

        super();
        this.arrayList = arrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder = null;
        viewHolder = new ViewHolderItem();
        // inflate the layout
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.get_listview_items, null);
            // well set up the ViewHolder
            viewHolder.IdTxtView = (TextView) convertView.findViewById(R.id.IdTxtView);
            viewHolder.EmailTxtView = (TextView) convertView.findViewById(R.id.EmailTxtView);

            // store the holder with the view.
            convertView.setTag(viewHolder);

        }
//        else {
//            viewHolder= (ViewHolderItem) convertView.getTag();
//        }

        viewHolder.IdTxtView.setText(arrayList.get(position).getId());

        viewHolder.EmailTxtView.setText(arrayList.get(position).getEmail());


        return convertView;

    }

    public static class ViewHolderItem {

        TextView IdTxtView, EmailTxtView;


    }
}
