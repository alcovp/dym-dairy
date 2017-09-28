package com.hazardousdev.gym_dairy.dao;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author alcovp
 */
interface IGymDairyDaoDDL {

    @SqlUpdate("create table IF NOT EXISTS notes (" +
            "id int auto_increment primary key," +
            "name varchar(128)," +
            "text varchar(128)," +
            ")")
    void createNoteTable();

    @SqlUpdate("create table IF NOT EXISTS workout_note_junction (" +
            "workout_id int," +
            "note_id int," +
            "PRIMARY KEY (workout_id, note_id)" +
            ")")
    void createWorkoutNoteJunctionTable();

    @SqlUpdate("create table IF NOT EXISTS features (" +
            "id int auto_increment primary key," +
            "tooltip varchar(128)," +
            "css_class varchar(128)" +
            ")")
    void createFeatureTable();

    @SqlUpdate("create table IF NOT EXISTS workout_feature_junction (" +
            "workout_id int," +
            "feature_id int," +
            "PRIMARY KEY (workout_id, feature_id)" +
            ")")
    void createWorkoutFeatureJunctionTable();

    @SqlUpdate("create table IF NOT EXISTS sets (" +
            "id int auto_increment primary key," +
            "repeats int," +
            "weight decimal," +
            "comment varchar(512)," +
            "move_id int" +
            ")")
    void createSetTable();

    @SqlUpdate("create table IF NOT EXISTS move_declarations (" +
            "id int auto_increment primary key," +
            "user_id int," +
            "name varchar(128)," +
            "description varchar(256)" +
            ")")
    void createMoveDeclarationTable();

    @SqlUpdate("create table IF NOT EXISTS moves (" +
            "id int auto_increment primary key," +
            "comment varchar(512)," +
            "move_declaration_id int," +
            "workout_id int" +
            ")")
    void createMoveTable();

    @SqlUpdate("create table IF NOT EXISTS workouts (" +
            "id int auto_increment primary key," +
            "user_id int," +
            "date date," +
            "comment varchar(512)" +
            ")")
    void createWorkoutTable();

    @SqlUpdate("create table IF NOT EXISTS users (" +
            "id int auto_increment," +
            "name varchar(32) primary key," +
            "password varchar(32)" +
            ")")
    void createUserTable();
}
