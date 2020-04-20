package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

