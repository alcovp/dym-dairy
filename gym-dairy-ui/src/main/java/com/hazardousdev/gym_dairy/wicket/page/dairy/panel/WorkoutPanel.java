package com.hazardousdev.gym_dairy.wicket.page.dairy.panel;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.Move;
import com.hazardousdev.gym_dairy.model.Workout;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.listener.AjaxReadinessListener;
import com.hazardousdev.gym_dairy.wicket.model.GetterModel;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.describing.FeaturesPanel;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.describing.NotesPanel;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.move.MovePanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author alcovp
 */
public class WorkoutPanel extends Panel {

    @Inject
    private IGymDairyService gymDairyService;

    public WorkoutPanel(String id, IModel<Workout> workoutModel, AjaxReadinessListener onEdit) {
        super(id);

        WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        add(container);

        ModalWindow window = new ModalWindow("window");
        add(window);

        container.add(new Label("date", GetterModel.ofModel(workoutModel, Workout::getDateForDisplay)));

        ListView<Move> moves = new ListView<Move>("list", GetterModel.ofModel(workoutModel, Workout::getMoves)) {
            @Override
            protected void populateItem(ListItem<Move> item) {
                item.add(new MovePanel("move", item.getModel()));
            }
        };
        container.add(moves);

        container.add(new Label("comment", GetterModel.ofModel(workoutModel, Workout::getComment)));
        container.add(new FeaturesPanel("features", GetterModel.ofModel(workoutModel, Workout::getFeatures)));
        container.add(new NotesPanel("notes", GetterModel.ofModel(workoutModel, Workout::getNotes)));

        container.add(new AjaxLink<Void>("edit") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                window.setContent(new WorkoutEditPanel(
                        window.getContentId(),
                        workoutModel.getObject(),
                        window,
                        t -> t.add(container)
                ));
                window.show(target);
            }
        });

        container.add(new AjaxLink<Void>("delete") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                gymDairyService.deleteWorkout(workoutModel.getObject().getId());
                onEdit.onReady(target);
            }
        });
    }
}
