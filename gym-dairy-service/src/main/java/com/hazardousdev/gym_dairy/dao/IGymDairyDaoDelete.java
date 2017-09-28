package com.hazardousdev.gym_dairy.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author alcovp
 */
public interface IGymDairyDaoDelete {

    @SqlUpdate("" +
            "delete from move_declarations " +
            "where id = :move_id"
    )
    long deleteMoveDeclaration(@Bind("move_id") long moveId);

    @SqlUpdate("" +
            "delete from workouts " +
            "where id = :workout_id"
    )
    long deleteWorkout(@Bind("workout_id") long workoutId);
}
