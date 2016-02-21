package com.rajat.moodle.Objects;

import java.io.Serializable;

/**
 * Created by Rajat on 19-02-2016.
 */
public class CourseThreadObject implements Serializable {
    String description,title,created_at,updated_at;
    int user_id,registration_course_id,id;

    public CourseThreadObject(String description, String title, String created_at, String updated_at, int user_id, int registration_course_id, int id) {
        this.description = description;
        this.title = title;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user_id = user_id;
        this.registration_course_id = registration_course_id;
        this.id = id;
    }
}
