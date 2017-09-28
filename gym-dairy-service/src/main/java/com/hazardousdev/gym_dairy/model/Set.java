package com.hazardousdev.gym_dairy.model;

import com.github.rkmk.annotations.PrimaryKey;

import java.io.Serializable;

/**
 * @author alcovp
 */
public class Set implements /*IDescribed, */Serializable {

    @PrimaryKey
    private Long id;
    private int repeats;
    private float weight;
    //    @OneToMany("feature")
//    private List<Feature> features;
//    @OneToMany("note")
//    private List<Note> notes;
    private String comment;
    private Long moveId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

//    @Override
//    public List<Feature> getFeatures() {
//        return features;
//    }
//
//    public void setFeatures(List<Feature> features) {
//        this.features = features;
//    }
//
//    @Override
//    public List<Note> getNotes() {
//        return notes;
//    }
//
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//    }

    //    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getMoveId() {
        return moveId;
    }

    public void setMoveId(Long moveId) {
        this.moveId = moveId;
    }
}
