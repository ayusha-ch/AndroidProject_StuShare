package com.example.stu_share;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomCoordinator {
    public final static List<RoomCoordinator.Room> ROOM_LIST=new ArrayList<RoomCoordinator.Room>();
    public final static List<RoomCoordinator.Room> ROOM_PAST_LIST=new ArrayList<RoomCoordinator.Room>();

    public static class Room implements Serializable {
        public String id,ownerId,title,availableDate,rooms,lease,category,type,petFriendly,location,detail,rent,status,created;


        public Room(String id, String ownerId, String title, String availableDate, String rooms, String lease, String category,String type, String petFriendly, String location, String detail, String price, String status,String created) {
            this.id = id;
            this.ownerId = ownerId;
            this.title = title;
            this.availableDate = availableDate;
            this.rooms = rooms;
            this.lease = lease;//how many months
            this.category = category;// offering or wanted
            this.type = type;
            this.petFriendly = petFriendly; //yes or no
            this.location = location;
            this.detail = detail;
            this.rent = rent;
            this.status = status;
            this.created=created;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAvailableDate() {
            return availableDate;
        }

        public void setAvailableDate(String availableDate) {
            this.availableDate = availableDate;
        }

        public String getRooms() {
            return rooms;
        }

        public void setRooms(String rooms) {
            this.rooms = rooms;
        }

        public String getLease() {
            return lease;
        }

        public void setLease(String lease) {
            this.lease = lease;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPetFriendly() {
            return petFriendly;
        }

        public void setPetFriendly(String petFriendly) {
            this.petFriendly = petFriendly;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getRent() {
            return rent;
        }

        public void setRent(String rent) {
            this.rent = rent;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        @Override
        public String toString() {
            return title  ;
        }
    }
}
