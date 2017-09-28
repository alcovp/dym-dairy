package com.hazardousdev.gym_dairy.service;

import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.model.User;
import com.hazardousdev.gym_dairy.model.Workout;

import java.util.List;

/**
 * @author alcovp
 */
public interface IGymDairyService {

    User authenticate(String name, String password);

    List<Workout> getWorkouts(long userId);

    void createWorkout(Workout workout, long userId);

    void updateWorkout(Workout workout);

    void deleteWorkout(long workoutId);

    List<MoveDeclaration> getMoveDeclarations(long userId);

    void createMoveDeclaration(MoveDeclaration moveDeclaration, long userId);

    void updateMoveDeclaration(MoveDeclaration moveDeclaration);

    void deleteMoveDeclaration(long moveDeclarationId);

}
