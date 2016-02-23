package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Rajat on 19-02-2016.
 */
public class CourseThreadObject implements Parcelable {
    public String description="",title="",created_at="",updated_at="";
    public int user_id=0,registration_course_id=0,id=0;

    public CourseThreadObject(String description, String title, String created_at, String updated_at, int user_id, int registration_course_id, int id) {
        this.description = description;
        this.title = title;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user_id = user_id;
        this.registration_course_id = registration_course_id;
        this.id = id;
    }

    protected CourseThreadObject(Parcel in) {
        description = in.readString();
        user_id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeInt(user_id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CourseThreadObject> CREATOR = new Parcelable.Creator<CourseThreadObject>() {
        @Override
        public CourseThreadObject createFromParcel(Parcel in) {
            return new CourseThreadObject(in);
        }

        @Override
        public CourseThreadObject[] newArray(int size) {
            return new CourseThreadObject[size];
        }
    };
}
