package com.example.stu_share;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarCoordinator {

    public final static List<Car> CAR_LIST=new ArrayList<Car>();
    public final static List<Car> CAR_PAST_LIST=new ArrayList<Car>();

    public static class Car implements Serializable {
        public String id,ownerId,title,availableDate,brand,model,year,mileage,location,detail,price,status,created;


        public Car(String id, String ownerId, String title, String availableDate, String brand, String model, String year, String mileage, String location, String detail, String price, String status,String created) {
            this.id = id;
            this.ownerId = ownerId;
            this.title = title;
            this.availableDate = availableDate;
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.mileage = mileage;
            this.location = location;
            this.detail = detail;
            this.price = price;
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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
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

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return title  ;
        }
    }
}
