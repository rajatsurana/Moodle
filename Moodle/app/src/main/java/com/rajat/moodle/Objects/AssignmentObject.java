package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 19-02-2016.
 */
public class AssignmentObject implements Parcelable {
    public String name="",created_at="",deadline="",description="";
    public Object file_=null;
    public int registered_course_id=0,late_days_allowed=0,type_=0,id=0;

    public AssignmentObject(String name, String created_at, String deadline, String description, Object file_, int registered_course_id, int late_days_allowed, int type_, int id) {
        this.name = name;
        this.created_at = created_at;
        this.deadline = deadline;
        this.description = description;
        this.file_ = file_;
        this.registered_course_id = registered_course_id;
        this.late_days_allowed = late_days_allowed;
        this.type_ = type_;
        this.id = id;
    }

    protected AssignmentObject(Parcel in) {
        name = in.readString();
        file_ = (Object) in.readValue(Object.class.getClassLoader());
        registered_course_id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(file_);
        dest.writeInt(registered_course_id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AssignmentObject> CREATOR = new Parcelable.Creator<AssignmentObject>() {
        @Override
        public AssignmentObject createFromParcel(Parcel in) {
            return new AssignmentObject(in);
        }

        @Override
        public AssignmentObject[] newArray(int size) {
            return new AssignmentObject[size];
        }
    };
}
