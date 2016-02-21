package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 17-02-2016.
 */
public class CourseObject implements Parcelable {
    public String code="",name_course="",description_course="",l_t_p="";
    int credits=0,id_course=0;
    public CourseObject(String code,String name_course,String description_course,String l_t_p,
                        int credits,int id_course){
        this.code=code;
        this.credits=credits;
        this.description_course=description_course;
        this.id_course=id_course;
        this.l_t_p=l_t_p;
        this.name_course=name_course;
    }

    protected CourseObject(Parcel in) {
        code = in.readString();
        credits = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeInt(credits);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CourseObject> CREATOR = new Parcelable.Creator<CourseObject>() {
        @Override
        public CourseObject createFromParcel(Parcel in) {
            return new CourseObject(in);
        }

        @Override
        public CourseObject[] newArray(int size) {
            return new CourseObject[size];
        }
    };
}
