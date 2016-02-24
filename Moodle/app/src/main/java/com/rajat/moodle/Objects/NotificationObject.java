package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 17-02-2016.
 */
public class NotificationObject implements Parcelable {
    public String description="",created_at="",user_id="";
    public int is_seen=0,id=0;
    //user_id,description,is_seen,created_at,id
    public NotificationObject(String user_id, String description, int is_seen, String created_at, int id){
        this.created_at=created_at;
        this.description=description;
        this.id=id;
        this.user_id=user_id;
        this.is_seen=is_seen;
    }

    protected NotificationObject(Parcel in) {
        description = in.readString();
        is_seen = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeInt(is_seen);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NotificationObject> CREATOR = new Parcelable.Creator<NotificationObject>() {
        @Override
        public NotificationObject createFromParcel(Parcel in) {
            return new NotificationObject(in);
        }

        @Override
        public NotificationObject[] newArray(int size) {
            return new NotificationObject[size];
        }
    };
}