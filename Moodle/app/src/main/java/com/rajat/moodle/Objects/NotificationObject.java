package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 17-02-2016.
 */
public class NotificationObject {
   public String description="",created_at="";
   public int user_id =0,is_seen=0,id=0;
    //user_id,description,is_seen,created_at,id
    public NotificationObject(int user_id, String description, int is_seen, String created_at, int id){
        this.created_at=created_at;
        this.description=description;
        this.id=id;
        this.user_id=user_id;
        this.is_seen=is_seen;
    }
}
