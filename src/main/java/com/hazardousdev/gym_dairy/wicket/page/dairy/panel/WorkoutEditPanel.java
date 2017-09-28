package com.hazardousdev.gym_dairy.wicket.page.dairy.panel;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.Workout;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.listener.AjaxReadinessListener;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.move.AddMovesPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * @author alcovp
 */
public class WorkoutEditPanel extends Panel {

    @Inject
    private IGymDairyService gymDairyService;

    public WorkoutEditPanel(String id, Workout workout, final ModalWindow window, final AjaxReadinessListener onSave) {
        super(id);

        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        Form form = new Form("form");
        add(form);

        DateTextField dateTextField = new DateTextField("date", new PropertyModel<>(workout, "date"), "dd.MM.yyyy");
        DatePicker datePicker = new DatePicker();
        datePicker.setShowOnFieldClick(true);
        dateTextField.add(datePicker);
        dateTextField.setRequired(true);
        form.add(dateTextField);

        form.add(new AddMovesPanel("moves", Model.of(workout)));

        form.add(new TextArea<>("comment", new PropertyModel<>(workout, "comment")));

        form.add(new AjaxButton("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                gymDairyService.updateWorkout(workout);
                target.add(feedbackPanel);
                window.close(target);
                onSave.onReady(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }
        });

        form.add(new AjaxLink<Void>("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                window.close(target);
            }
        });
    }
}
