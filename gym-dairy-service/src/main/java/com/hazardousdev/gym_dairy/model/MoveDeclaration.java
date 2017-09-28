package com.hazardousdev.gym_dairy.model;

import com.github.rkmk.annotations.PrimaryKey;

import java.io.Serializable;

/**
 * @author alcovp
 */
public class MoveDeclaration implements Serializable {

    @PrimaryKey
    private Long id;
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
