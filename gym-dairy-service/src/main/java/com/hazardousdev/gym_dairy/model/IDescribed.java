package com.hazardousdev.gym_dairy.model;

import java.util.List;

/**
 * @author alcovp
 */
public interface IDescribed {

    List<Feature> getFeatures();

    List<Note> getNotes();

    String getComment();
}
