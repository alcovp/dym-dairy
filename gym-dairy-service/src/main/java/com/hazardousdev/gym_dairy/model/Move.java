package com.hazardousdev.gym_dairy.model;

import com.github.rkmk.annotations.OneToMany;
import com.github.rkmk.annotations.OneToOne;
import com.github.rkmk.annotations.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * @author alcovp
 */
public class Move implements /*IDescribed,*/ Serializable {

    @PrimaryKey
    private Long id;
    @OneToOne("declaration")
    private MoveDeclaration moveDeclaration;
    private Long moveDeclarationId;
    @OneToMany("set")
    private List<Set> sets;
    //    @OneToMany("feature")
//    private List<Feature> features;
//    @OneToMany("note")
//    private List<Note> notes;
    private String comment;
    private Long workoutId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public MoveDeclaration getMoveDeclaration() {
        return moveDeclaration;
    }

    public void setMoveDeclaration(MoveDeclaration moveDeclaration) {
        this.moveDeclaration = moveDeclaration;
    }

    public Long getMoveDeclarationId() {
        return moveDeclarationId;
    }

    public void setMoveDeclarationId(Long moveDeclarationId) {
        this.moveDeclarationId = moveDeclarationId;
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
//
//    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }
}
