package com.example.stu_share;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EventCoordinator {

    public static final List<Event> EVENTS = new ArrayList<Event>();

    private static Random random = new Random(System.currentTimeMillis());
    public static final Map<String, Event> EVENT_MAP = new HashMap<String, Event>();

    public static void addEventList(Event event) {
        EVENTS.add(event);
        EVENT_MAP.put(event.id, event);
    }
    /*public static Event createEvent( String orgID, String startDate, String startTime, String endDate, String endTime, String eventTitle, String eventDetail) {
        return new Event(String.valueOf(random.nextLong()),orgID, "active",startDate, startTime, endDate, endTime, eventTitle, eventDetail);
    }*/
    public static class Event implements Serializable {
        public String id;
        public  String orgID;
        public  String status;
        public  String startDate;
        public  String startTime;
        public  String endDate;
        public  String endTime;
        public  String eventTitle;
        public  String eventDetail;



        public Float rating;
        public String mImageDrawable;

        public Event() {

        }
        public Float getRating() {
            return rating;
        }

        public void setRating(Float rating) {
            this.rating = rating;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOrgID(String orgID) {
            this.orgID = orgID;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public void setEventDetail(String eventDetail) {
            this.eventDetail = eventDetail;
        }

        public String getId() {
            return id;
        }

        public String getOrgID() {
            return orgID;
        }

        public String getStatus() {
            return status;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public String getEventDetail() {
            return eventDetail;
        }



        public Event( String id, String orgID, String status, String startDate,
                     String startTime, String endDate, String endTime,
                      String eventTitle, String eventDetail, String mImageDrawable,Float rating) {
            this.id=id;
            this.orgID = orgID;
            this.status = status;
            this.startDate = startDate;
            this.startTime = startTime;
            this.endDate = endDate;
            this.endTime = endTime;
            this.eventTitle = eventTitle;
            this.eventDetail = eventDetail;
            this.mImageDrawable = mImageDrawable;
            this.rating=rating;
        }
        public String getmImageDrawable() {
            return mImageDrawable;
        }

        public void setmImageDrawable(String mImageDrawable) {
            this.mImageDrawable = mImageDrawable;
        }



        public String toString(){
            return "Event:"+eventTitle+"\nDetails about this event:"+eventDetail
                    +"\nStart date & time:"+getStartDate()+" "+getStartTime()
                    +"\nEnd date & time:"+getEndDate()+" "+getEndTime();
        }
    }
}
