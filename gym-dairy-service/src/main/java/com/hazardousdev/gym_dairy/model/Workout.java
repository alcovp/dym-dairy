package com.hazardousdev.gym_dairy.model;

import com.github.rkmk.annotations.OneToMany;
import com.github.rkmk.annotations.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author alcovp
 */
public class Workout implements IDescribed, Serializable {

    @PrimaryKey
    private Long id;
    private Date date;
    @OneToMany("move")
    private List<Move> moves;
    @OneToMany("feature")
    private List<Feature> features;
    @OneToMany("note")
    private List<Note> notes;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public String getDateForDisplay() {
        if (getDate() != null) {
            return new SimpleDateFormat("dd.MM.yyyy").format(getDate());
        }
        return null;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    @Override
    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
