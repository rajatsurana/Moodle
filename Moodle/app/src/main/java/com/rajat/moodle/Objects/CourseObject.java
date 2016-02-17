package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 17-02-2016.
 */
public class CourseObject {
    String code="",name_course="",description_course="",l_t_p="";
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
}
