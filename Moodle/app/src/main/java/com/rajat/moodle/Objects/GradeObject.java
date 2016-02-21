package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 17-02-2016.
 */
public class GradeObject implements Parcelable {

    public int weightage=0,user_id=0,out_of=0,registered_course_id=0,id=0;
    public double score=0;
    public String name="";
    public GradeObject(int weightage,int user_id,int out_of,int registered_course_id,double score,int id, String name){
        this.id=id;
        this.name=name;
        this.out_of=out_of;
        this.registered_course_id=registered_course_id;
        this.user_id=user_id;
        this.score=score;
        this.weightage=weightage;

    }

    protected GradeObject(Parcel in) {
        weightage = in.readInt();
        score = in.readDouble();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(weightage);
        dest.writeDouble(score);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GradeObject> CREATOR = new Parcelable.Creator<GradeObject>() {
        @Override
        public GradeObject createFromParcel(Parcel in) {
            return new GradeObject(in);
        }

        @Override
        public GradeObject[] newArray(int size) {
            return new GradeObject[size];
        }
    };
}
