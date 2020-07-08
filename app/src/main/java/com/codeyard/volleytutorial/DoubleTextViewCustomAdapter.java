package com.codeyard.volleytutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class DoubleTextViewCustomAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final ArrayList<UserModel> userModels;

    public DoubleTextViewCustomAdapter(Context context, ArrayList<UserModel> users) {
        layoutInflater = LayoutInflater.from(context);
        userModels = users;
    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int i) {
        return userModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.double_text_view, null);
            viewHolder.nameTextView = view.findViewById(R.id.name_text_view);
            viewHolder.emailTextView = view.findViewById(R.id.email_text_view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameTextView.setText(userModels.get(i).getName());
        viewHolder.emailTextView.setText(userModels.get(i).getEmail());
        return view;
    }

    private class ViewHolder {
        TextView nameTextView, emailTextView;
    }

}
