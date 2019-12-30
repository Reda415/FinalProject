package com.codingelab.tutorial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class StudentAdapter  extends BaseAdapter {

    public ArrayList<Student> personlist;
    Activity activity;

    public StudentAdapter(Activity activity, ArrayList<Student> personlist) {
        super();
        this.activity = activity;
        this.personlist = personlist;
    }
    @Override
    public int getCount() {
        return personlist.size();
    }

    @Override
    public Object getItem(int position) {
        return personlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView id;
        TextView name;
        TextView phone;
        TextView email;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_rows, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textName);
            holder.phone = (TextView) convertView
                    .findViewById(R.id.textPhone);
            holder.email = (TextView) convertView.findViewById(R.id.textEmail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student item = personlist.get(position);
        holder.id.setText(item.getId());
        holder.name.setText(item.getName().toString());
        holder.phone.setText(item.getPhone().toString());
        holder.email.setText(item.getEmail().toString());
        return convertView;
    }

}
