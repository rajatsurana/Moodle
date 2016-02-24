package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 17-02-2016.
 */
public class SubmissionObject {
    String name_submission="",created_at_submission="",file_submission="";
    int event_id=0,user_id=0,id_submission=0;
    public SubmissionObject(String name_submission,String created_at_submission,String file_submission,
            int event_id,int user_id,int id_submission){
        this.created_at_submission=created_at_submission;
        this.event_id=event_id;
        this.file_submission=file_submission;
        this.id_submission=id_submission;
        this.name_submission=name_submission;
        this.user_id=user_id;

    }
}
