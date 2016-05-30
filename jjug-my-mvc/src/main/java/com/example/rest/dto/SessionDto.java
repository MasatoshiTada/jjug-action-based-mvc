package com.example.rest.dto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Random;

@Named
@SessionScoped
public class SessionDto implements Serializable {

    private String id;

    @PostConstruct
    public void init() {
        int i = new Random().nextInt(100);
        id = Integer.toString(i);
    }

    public String getId() {
        return id;
    }
}
