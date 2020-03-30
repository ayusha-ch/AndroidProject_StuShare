package com.example.stu_share;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
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
    private List<EventCoordinator.Event> eventList = new ArrayList<>();
    final String urlRate="https://w0044421.gblearn.com/stu_share/Event_Rate.php";
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

        EventCoordinator.Event currentEvent = eventList.get(position);
        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        PicassoClient.downloadImage(mContext,currentEvent.getmImageDrawable(), image);
        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentEvent.getEventTitle());
        ImageView likeImage=(ImageView)listItem.findViewById(R.id.imgLike);
        likeImage.setImageResource(R.drawable.ic_thumbs_up);
        TextView detail = (TextView) listItem.findViewById(R.id.textView_release);
        detail.setText(currentEvent.getEventDetail());
        Button btnRating=listItem.findViewById(R.id.buttonRate);
        RatingBar rtBar=listItem.findViewById(R.id.ratingBar);
        rtBar.setRating(currentEvent.getRating());
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateRating(urlRate,eventList.get(position),user3,2);
            }
        });
        return listItem;
    }

    public void updateRating(final String url, final EventCoordinator.Event event,final User user,final float rating) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("event_id", event.id);
                    jsonParam.put("user_id", user3.id);
                    jsonParam.put("rating", rating);
                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    os.writeBytes(jsonParam.toString());
                    os.flush();
                    os.close();
                    conn.connect();
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    DataInputStream is=new DataInputStream(conn.getInputStream());
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null)
                    {
                        total.append(line).append('\n');
                    }
                    Log.d("TAG", "Server Response is: " + total.toString() + ": " );
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}


