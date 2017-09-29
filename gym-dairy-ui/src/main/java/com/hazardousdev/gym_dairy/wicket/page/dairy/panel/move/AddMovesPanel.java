package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.move;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.Move;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.model.Workout;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.application.GymDairyAuthenticatedWebSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alcovp
 */
public class AddMovesPanel extends Panel {

    @Inject
    private IGymDairyService gymDairyService;

    public AddMovesPanel(String id, final IModel<Workout> workoutModel) {
        super(id);

        final IModel<? extends List<Move>> movesModel = new AbstractReadOnlyModel<List<Move>>() {
            @Override
            public List<Move> getObject() {
                if (workoutModel.getObject().getMoves() == null || workoutModel.getObject().getMoves().size() == 0) {
                    final ArrayList<Move> moves = new ArrayList<>();
                    workoutModel.getObject().setMoves(moves);
                }
                return workoutModel.getObject().getMoves();
            }
        };

        final IModel<List<MoveDeclaration>> moveDeclarationsModel = new LoadableDetachableModel<List<MoveDeclaration>>() {
            @Override
            protected List<MoveDeclaration> load() {
                return gymDairyService.getMoveDeclarations(GymDairyAuthenticatedWebSession.get().getCurrentUserId());
            }
        };

        final WebMarkupContainer moveListContainer = new WebMarkupContainer("list-container");
        moveListContainer.setOutputMarkupId(true);
        add(moveListContainer);

        ListView<Move> moveList = new ListView<Move>("list", movesModel) {
            @Override
            protected void populateItem(ListItem<Move> item) {
                item.add(new CreateMovePanel("move", moveDeclarationsModel, item.getModel()));
            }
        };
        moveListContainer.add(moveList);

        add(new AjaxLink<Void>("add") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                movesModel.getObject().add(new Move());
                target.add(moveListContainer);
            }
        });
    }
}
