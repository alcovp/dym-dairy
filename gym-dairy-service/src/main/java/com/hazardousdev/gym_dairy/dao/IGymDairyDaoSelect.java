package com.hazardousdev.gym_dairy.dao;

import com.github.rkmk.container.FoldingList;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.model.User;
import com.hazardousdev.gym_dairy.model.Workout;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * @author alcovp
 */
interface IGymDairyDaoSelect {

    @SqlQuery("" +
            "select\n" +
            "   w.*,\n" +

            "   m.id AS move$id,\n" +
            "   m.comment AS move$comment,\n" +
            "   m.workout_id AS move$workout_id,\n" +
            "   md.id AS move$move_declaration_id,\n" +

            "   md.id AS declaration$id,\n" +
            "   md.name AS declaration$name,\n" +
            "   md.description AS declaration$description,\n" +

            "   s.id AS set$id,\n" +
            "   s.repeats AS set$repeats,\n" +
            "   s.weight AS set$weight,\n" +
            "   s.comment AS set$comment,\n" +
            "   s.move_id AS set$move_id,\n" +

            "   f.id AS feature$id,\n" +
            "   f.tooltip AS feature$tooltip,\n" +
            "   f.css_class AS feature$css_class,\n" +

            "   n.id AS note$id,\n" +
            "   n.name AS note$name,\n" +
            "   n.text AS note$text\n" +

            "from workouts w\n" +

            "left join moves m                        on w.id = m.workout_id\n" +
            "left join move_declarations md           on m.move_declaration_id = md.id\n" +
            "left join sets s                         on m.id = s.move_id\n" +
            "left join workout_feature_junction wfj   on w.id = wfj.workout_id\n" +
            "left join features f                     on wfj.feature_id = f.id\n" +
            "left join workout_note_junction wnj      on w.id = wnj.workout_id\n" +
            "left join notes n                        on wnj.note_id = n.id\n" +
            "where w.user_id = :user_id")
    FoldingList<Workout> selectWorkouts(@Bind("user_id") long userId);

    @SqlQuery("" +
            "select\n" +
            "   *\n" +
            "from move_declarations where user_id = :user_id\n" +
            "")
    FoldingList<MoveDeclaration> selectMoveDeclarations(@Bind("user_id") long userId);

    @SqlQuery("" +
            "select\n" +
            "   *\n" +
            "from users\n" +
            "where name = :name and password = :password\n" +
            "")
    FoldingList<User> selectUser(@Bind("name") String name, @Bind("password") String password);
}
