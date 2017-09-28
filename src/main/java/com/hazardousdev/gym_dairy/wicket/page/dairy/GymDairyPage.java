package com.hazardousdev.gym_dairy.wicket.page.dairy;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.Workout;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.application.GymDairyAuthenticatedWebSession;
import com.hazardousdev.gym_dairy.wicket.page.base.BasePage;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.WorkoutCreatePanel;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.WorkoutPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;


/**
 * @author alcovp
 */
public class GymDairyPage extends BasePage {

    @Inject
    private IGymDairyService gymDairyService;

    public GymDairyPage(PageParameters parameters) {
        super(parameters);

        final IModel<List<Workout>> workoutsModel = new AbstractReadOnlyModel<List<Workout>>() {
            @Override
            public List<Workout> getObject() {
                return gymDairyService.getWorkouts(GymDairyAuthenticatedWebSession.get().getCurrentUserId());
            }
        };

        final WebMarkupContainer listContainer = new WebMarkupContainer("list-container");
        listContainer.setOutputMarkupId(true);
        add(listContainer);

        final ModalWindow window = new ModalWindow("add-window");
        add(window);

        AjaxLink<Void> addLink = new AjaxLink<Void>("add-link") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                window.setContent(new WorkoutCreatePanel(window.getContentId(), window, t -> t.add(listContainer)));
                window.show(target);
            }
        };
        add(addLink);

        ListView<Workout> workouts = new ListView<Workout>("list", workoutsModel) {
            @Override
            protected void populateItem(ListItem<Workout> item) {
                item.add(new WorkoutPanel(
                        "workout",
                        item.getModel(),
                        target -> target.add(listContainer)
                ));
            }
        };
        listContainer.add(workouts);
    }
}
