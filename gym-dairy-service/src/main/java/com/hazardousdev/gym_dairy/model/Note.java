package com.hazardousdev.gym_dairy.model;

import com.github.rkmk.annotations.PrimaryKey;

import java.io.Serializable;

/**
 * @author alcovp
 */
public class Note implements Serializable {

    @PrimaryKey
    private Long id;
    private String name;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
