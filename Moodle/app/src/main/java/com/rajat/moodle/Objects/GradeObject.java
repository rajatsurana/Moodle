package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 17-02-2016.
 */
public class GradeObject {

    int weightage=0,user_id=0,out_of=0,registered_course_id=0,id=0;
    double score=0;
    String name="";
    public GradeObject(int weightage,int user_id,int out_of,int registered_course_id,double score,int id, String name){
        this.id=id;
        this.name=name;
        this.out_of=out_of;
        this.registered_course_id=registered_course_id;
        this.user_id=user_id;
        this.score=score;
        this.weightage=weightage;

    }
}
