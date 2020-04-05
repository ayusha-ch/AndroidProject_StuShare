package com.example.stu_share;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.stu_share.EventCoordinator;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.RotationRatingBar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.stu_share.EventList.user3;


public class EventAdapter extends ArrayAdapter<EventCoordinator.Event> {

    private Context mContext;
    public static List<EventCoordinator.Event> eventList = new ArrayList<>();
    public EventAdapter(@NonNull Context context, ArrayList<EventCoordinator.Event> list) {
        super(context, 0 , list);
        mContext = context;
        eventList = list;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        final EventCoordinator.Event currentEvent = eventList.get(position);
        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        PicassoClient.downloadImage(mContext,currentEvent.getmImageDrawable(), image);
        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentEvent.getEventTitle());
        ImageView likeImage=(ImageView)listItem.findViewById(R.id.imgLike);
        if(currentEvent.isLiked()==0){
            likeImage.setImageResource(R.drawable.ic_thumbs_up);
        }else{
            likeImage.setImageResource(R.drawable.ic_thumbs_up_red);
        }
        likeImage.setTag(position);
        TextView txtCount=(TextView) listItem.findViewById(R.id.likeCount);
        txtCount.setText(String.valueOf(currentEvent.likeCount));
        TextView detail = (TextView) listItem.findViewById(R.id.textView_release);
        detail.setText(currentEvent.getEventDetail());
        final RotationRatingBar rtBar=listItem.findViewById(R.id.ratingBar);
        Log.i("RATING",currentEvent.getRating()+"");
        rtBar.setRating(currentEvent.getRating());
        rtBar.setIsIndicator(true);
        rtBar.setClickable(false);
        rtBar.setFocusable(false);
        return listItem;
    }
    @Override
    public int getCount(){
        return eventList.size();
    }

}


