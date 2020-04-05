package com.example.stu_share;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import java.io.Serializable;
import java.util.ArrayList;
public class MessageCoordinator implements Serializable {
    public final static ArrayList<Message> MESSAGE_LIST=new ArrayList<Message>();
    public static class Message implements Serializable {
        public int id;
        public String title, detail, receiver_email, sender_email, type, status;


    public Message(int id, String title, String detail, String receiver_email, String sender_email, String type,  String status){



            this.id = id;
            this.title = title;
            this.detail = detail;
            this.receiver_email = receiver_email;
            this.sender_email = sender_email;
            this.type = type;
            this.status = status;

        }
        public Message(){
        }
        public Message(int id, String title){
            this.id = id;
            this.title = title;
        }

       
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getDetail() {
            return detail;
        }
        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getReceiver_email() {
            return receiver_email;
        }

        public void setReceiver_email(String receiver_email) {
            this.receiver_email = receiver_email;
        }

        public String getSender_email() {
            return sender_email;
        }

        public void setSender_email(String sender_email) {
            this.sender_email = sender_email;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return  "id=" + id +
                    "\ntitle: " + title +
                    "\ndetail: " + detail +
                    "\n";
        }
         // or use DEFAULT
        // generate random color

    }
}
