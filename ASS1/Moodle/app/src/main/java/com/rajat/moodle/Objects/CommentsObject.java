package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 19-02-2016.
 */
public class CommentsObject implements Parcelable {
    public int user_id=0,thread_id=0,id=0;
    public String description=null,created_at=null;

    public CommentsObject(int user_id, int thread_id, int id, String description, String created_at) {
        this.user_id = user_id;
        this.thread_id = thread_id;
        this.id = id;
        this.description = description;
        this.created_at = created_at;
    }

    protected CommentsObject(Parcel in) {
        user_id = in.readInt();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CommentsObject> CREATOR = new Parcelable.Creator<CommentsObject>() {
        @Override
        public CommentsObject createFromParcel(Parcel in) {
            return new CommentsObject(in);
        }

        @Override
        public CommentsObject[] newArray(int size) {
            return new CommentsObject[size];
        }
    };
}
