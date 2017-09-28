package com.hazardousdev.gym_dairy.model;

import com.github.rkmk.annotations.PrimaryKey;

import java.io.Serializable;

/**
 * @author alcovp
 */
public class Feature implements Serializable {

    @PrimaryKey
    private Long id;
    private String tooltip;
    private String cssClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
