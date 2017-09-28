package com.hazardousdev.gym_dairy.dao;

import com.hazardousdev.gym_dairy.model.*;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author alcovp
 */
public interface IGymDairyDaoInsert {

    @SqlUpdate("" +
            "insert into users" +
            "(name, password) values" +
            "(:name, :password)"
    )
    @GetGeneratedKeys
    long insertUser(@BindBean User user);

    @SqlUpdate("" +
            "insert into workouts" +
            "(user_id, date, comment) values" +
            "(:user_id, :date, :comment)"
    )
    @GetGeneratedKeys
    long insertWorkout(@BindBean Workout workout, @Bind("user_id") long userId);

    @SqlUpdate("" +
            "insert into move_declarations " +
            "(user_id, name, description) values" +
            "(:user_id, :name, :description)"
    )
    @GetGeneratedKeys
    long insertMoveDeclaration(@BindBean MoveDeclaration moveDeclaration, @Bind("user_id") long userId);

    @SqlUpdate("" +
            "insert into moves " +
            "(comment, workout_id, move_declaration_id) values" +
            "(:comment, :workoutId, :moveDeclarationId)"
    )
    @GetGeneratedKeys
    long insertMove(@BindBean Move move);

    @SqlUpdate("" +
            "insert into sets " +
            "(repeats, weight, comment, move_id) values" +
            "(:repeats, :weight, :comment, :moveId)"
    )
    @GetGeneratedKeys
    long insertSet(@BindBean Set set);

    @SqlUpdate("" +
            "insert into features " +
            "(tooltip, css_class) values" +
            "(:tooltip, :cssClass)"
    )
    @GetGeneratedKeys
    long insertFeature(@BindBean Feature feature);

    @SqlUpdate("" +
            "insert into notes " +
            "(name, text) values" +
            "(:name, :text)"
    )
    @GetGeneratedKeys
    long insertNote(@BindBean Note note);
}
