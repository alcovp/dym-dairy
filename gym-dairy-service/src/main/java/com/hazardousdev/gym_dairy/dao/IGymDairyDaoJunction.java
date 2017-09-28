package com.hazardousdev.gym_dairy.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author alcovp
 */
public interface IGymDairyDaoJunction {

    @SqlUpdate("" +
            "insert into workout_feature_junction " +
            "(workout_id, feature_id) " +
            "select :workout_id, :feature_id " +
            "where not exists (select workout_id, feature_id from workout_feature_junction " +
            "where workout_id = :workout_id and feature_id = :feature_id)"
    )
    long connectWorkoutAndFeature(@Bind("workout_id") long workoutId, @Bind("feature_id") long featureId);

    @SqlUpdate("" +
            "insert into workout_note_junction " +
            "(workout_id, note_id) " +
            "select :workout_id, :note_id " +
            "where not exists (select workout_id, note_id from workout_note_junction " +
            "where workout_id = :workout_id and note_id = :note_id)"
    )
    long connectWorkoutAndNote(@Bind("workout_id") long workoutId, @Bind("note_id") long noteId);
}
