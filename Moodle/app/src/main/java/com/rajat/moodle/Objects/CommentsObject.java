package com.rajat.moodle.Objects;

/**
 * Created by Rajat on 19-02-2016.
 */
public class CommentsObject {
    int user_id,thread_id,id;
    String description,created_at;

    public CommentsObject(int user_id, int thread_id, int id, String description, String created_at) {
        this.user_id = user_id;
        this.thread_id = thread_id;
        this.id = id;
        this.description = description;
        this.created_at = created_at;
    }
}
