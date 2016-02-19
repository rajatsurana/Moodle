package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 19-02-2016.
 */
public class UsersObject {
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
}
