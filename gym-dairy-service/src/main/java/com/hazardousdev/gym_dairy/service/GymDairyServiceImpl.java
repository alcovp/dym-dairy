package com.hazardousdev.gym_dairy.service;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.dao.IGymDairyDao;
import com.hazardousdev.gym_dairy.model.*;

import java.util.Date;
import java.util.List;

;

/**
 * @author alcovp
 */
public class GymDairyServiceImpl implements IGymDairyService {

    private IGymDairyDao dao;

    @Inject
    public GymDairyServiceImpl(IGymDairyDao gymDairyDao) {

        dao = gymDairyDao;

        dao.createUserTable();
        dao.createFeatureTable();
        dao.createNoteTable();
        dao.createSetTable();
        dao.createMoveTable();
        dao.createWorkoutTable();
        dao.createMoveDeclarationTable();
        dao.createWorkoutFeatureJunctionTable();
        dao.createWorkoutNoteJunctionTable();

        insertUsersTest();
        insertFeaturesNotesDeclarationsTest(1);
        insertWorkoutTest(1);
        insertFeaturesNotesDeclarationsTest(2);
        insertWorkoutTest(2);
    }

    public void createWorkout(Workout workout, long userId) {
        long workoutId = dao.insertWorkout(workout, userId);

        if (workout.getFeatures() != null) {
            workout.getFeatures().forEach(feature -> dao.connectWorkoutAndFeature(workoutId, feature.getId()));
        }
        if (workout.getNotes() != null) {
            workout.getNotes().forEach(note -> dao.connectWorkoutAndNote(workoutId, note.getId()));
        }
        if (workout.getMoves() != null) {
            workout.getMoves().forEach(move -> {
                move.setWorkoutId(workoutId);
                move.setMoveDeclarationId(move.getMoveDeclaration().getId());
                long moveId = dao.insertMove(move);
                if (move.getSets() != null) {
                    move.getSets().forEach(set -> {
                        set.setMoveId(moveId);
                        dao.insertSet(set);
                    });
                }
            });
        }
    }

    @Override
    public void updateWorkout(Workout workout) {
        dao.updateWorkout(workout);

        if (workout.getFeatures() != null) {
            workout.getFeatures().forEach(feature -> dao.connectWorkoutAndFeature(workout.getId(), feature.getId()));
        }
        if (workout.getNotes() != null) {
            workout.getNotes().forEach(note -> dao.connectWorkoutAndNote(workout.getId(), note.getId()));
        }
        if (workout.getMoves() != null) {
            workout.getMoves().forEach(move -> {
                long moveId;
                if (move.getId() == null) {
                    move.setWorkoutId(workout.getId());
                    move.setMoveDeclarationId(move.getMoveDeclaration().getId());
                    moveId = dao.insertMove(move);
                } else {
                    moveId = move.getId();
                    move.setMoveDeclarationId(move.getMoveDeclaration().getId());
                    dao.updateMove(move);
                }
                if (move.getSets() != null) {
                    move.getSets().forEach(set -> {
                        if (set.getId() == null) {
                            set.setMoveId(moveId);
                            dao.insertSet(set);
                        } else {
                            dao.updateSet(set);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void deleteWorkout(long workoutId) {
        dao.deleteWorkout(workoutId);
    }

    public void deleteMoveDeclaration(long moveDeclarationId) {
        dao.deleteMoveDeclaration(moveDeclarationId);
    }

    @Override
    public List<MoveDeclaration> getMoveDeclarations(long userId) {
        return dao.selectMoveDeclarations(userId).getValues();
    }

    public void createMoveDeclaration(MoveDeclaration moveDeclaration, long userId) {
        dao.insertMoveDeclaration(moveDeclaration, userId);
    }

    @Override
    public void updateMoveDeclaration(MoveDeclaration moveDeclaration) {
        dao.updateMoveDeclaration(moveDeclaration);
    }

    private void insertUsersTest() {
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");

        User user = new User();
        user.setName("user");
        user.setPassword("user");

        dao.insertUser(admin);
        dao.insertUser(user);
    }

    private void insertFeaturesNotesDeclarationsTest(long userId) {
        Note note = new Note();
        note.setText("text");
        note.setName("note");
        dao.insertNote(note);

        Feature feature = new Feature();
        feature.setTooltip("tooltip");
        feature.setCssClass("feature-common");
        dao.insertFeature(feature);

        Feature feature2 = new Feature();
        feature2.setTooltip("tooltip 2");
        feature2.setCssClass("feature-common2");
        dao.insertFeature(feature2);

        MoveDeclaration moveDeclaration = new MoveDeclaration();
        moveDeclaration.setName("move");
        moveDeclaration.setDescription("description");
        dao.insertMoveDeclaration(moveDeclaration, userId);
    }

    private void insertWorkoutTest(long userId) {
        Workout workout = new Workout();
        workout.setDate(new Date());
        workout.setComment("comment");

        long id = dao.insertWorkout(workout, userId);

        MoveDeclaration declaration = new MoveDeclaration();
        declaration.setName("move 1");
        declaration.setDescription("description");
        long declarationId1 = dao.insertMoveDeclaration(declaration, userId);

        MoveDeclaration declaration2 = new MoveDeclaration();
        declaration2.setName("move 2");
        declaration2.setDescription("description 2");
        long declarationId2 = dao.insertMoveDeclaration(declaration2, userId);

        Move move = new Move();
        move.setWorkoutId(id);
        move.setMoveDeclarationId(declarationId1);
        move.setComment("comment");

        Move move2 = new Move();
        move2.setWorkoutId(id);
        move2.setMoveDeclarationId(declarationId2);
        move2.setComment("comment");

        long moveId = dao.insertMove(move);
        long move2Id = dao.insertMove(move2);

        Set set = new Set();
        set.setWeight(90);
        set.setRepeats(8);
        set.setMoveId(moveId);

        Set set2 = new Set();
        set2.setWeight(100);
        set2.setRepeats(8);
        set2.setMoveId(move2Id);

        dao.insertSet(set);
        dao.insertSet(set);
        dao.insertSet(set);
        dao.insertSet(set2);
        dao.insertSet(set2);
        dao.insertSet(set2);

        dao.connectWorkoutAndFeature(id, 1);

        dao.connectWorkoutAndNote(id, 1);
    }

    @Override
    public User authenticate(String name, String password) {
        return dao.selectUser(name, password).getValues().stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Workout> getWorkouts(long userId) {
        return dao.selectWorkouts(userId).getValues();
    }


}
