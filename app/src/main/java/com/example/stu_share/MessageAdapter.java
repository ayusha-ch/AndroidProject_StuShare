package com.example.stu_share;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageCoordinator.Message> {
    private Context mContext;
    private List<MessageCoordinator.Message> messageList = new ArrayList<>();

    public MessageAdapter(@NonNull Context context, ArrayList<MessageCoordinator.Message> list) {
        super(context, 0 , list);
        mContext = context;
        messageList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.messagelist,parent,false);

        MessageCoordinator.Message currentMessage = messageList.get(position);

        // ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        //\
        //PicassoClient.downloadImage(mContext,currentMessage.getmImageDrawable(), image);
        image.setImageDrawable(currentMessage.getmImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentMessage.getTitle());

        TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        release.setText(currentMessage.getDetail());

        return listItem;
    }

}
