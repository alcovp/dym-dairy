package com.hazardousdev.gym_dairy.dao;

import com.hazardousdev.gym_dairy.model.Move;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.model.Set;
import com.hazardousdev.gym_dairy.model.Workout;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author alcovp
 */
public interface IGymDairyDaoUpdate {

    @SqlUpdate("" +
            "update move_declarations " +
            "set name = :name, description = :description " +
            "where id = :id"
    )
    @GetGeneratedKeys
    long updateMoveDeclaration(@BindBean MoveDeclaration moveDeclaration);

    @SqlUpdate("" +
            "update workouts " +
            "set date = :date, comment = :comment " +
            "where id = :id"
    )
    @GetGeneratedKeys
    long updateWorkout(@BindBean Workout workout);

    @SqlUpdate("" +
            "update moves " +
            "set comment = :comment, move_declaration_id = :moveDeclarationId, workout_id = :workoutId " +
            "where id = :id"
    )
    @GetGeneratedKeys
    long updateMove(@BindBean Move move);

    @SqlUpdate("" +
            "update sets " +
            "set repeats = :repeats, weight = :weight, comment = :comment, move_id = :moveId " +
            "where id = :id"
    )
    @GetGeneratedKeys
    long updateSet(@BindBean Set set);
}
