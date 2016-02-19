package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 19-02-2016.
 */
public class AssignmentObject {
    String name="",created_at="",deadline="",description="";
    Object file_=null;
    int registered_course_id=0,late_days_allowed=0,type_=0,id=0;

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
}
