package com.rajat.moodle.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat on 19-02-2016.
 */
public class UsersObject implements Parcelable {
    String last_name="",reset_password_key="",registration_key="",
            first_name="",entry_no="",email="",username="",registration_id="",password="";
    int id =0;
    int type_=0;

    public UsersObject(String last_name, String reset_password_key, String registration_key, String first_name, String entry_no, String email, String username, String registration_id, String password, int id, int type_) {
        this.last_name = last_name;
        this.reset_password_key = reset_password_key;
        this.registration_key = registration_key;
        this.first_name = first_name;
        this.entry_no = entry_no;
        this.email = email;
        this.username = username;
        this.registration_id = registration_id;
        this.password = password;
        this.id = id;
        this.type_ = type_;
    }


    public String getLast_name() {
        return last_name;
    }

    public String getReset_password_key() {
        return reset_password_key;
    }

    public String getRegistration_key() {
        return registration_key;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEntry_no() {
        return entry_no;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public int getType_() {
        return type_;
    }

    protected UsersObject(Parcel in) {
        last_name = in.readString();
        id = in.readInt();
        type_ = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(last_name);
        dest.writeInt(id);
        dest.writeInt(type_);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UsersObject> CREATOR = new Parcelable.Creator<UsersObject>() {
        @Override
        public UsersObject createFromParcel(Parcel in) {
            return new UsersObject(in);
        }

        @Override
        public UsersObject[] newArray(int size) {
            return new UsersObject[size];
        }
    };
}
