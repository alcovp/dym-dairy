package com.hazardousdev.gym_dairy.wicket.page.move_declarations.panel;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.behavior.OnlineModelUpdateBehavior;
import com.hazardousdev.gym_dairy.wicket.listener.AjaxReadinessListener;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author alcovp
 */
public class MoveDeclarationEditPanel extends Panel {

    @Inject
    private IGymDairyService gymDairyService;

    public MoveDeclarationEditPanel(String id, MoveDeclaration declaration, final ModalWindow window,
                                    AjaxReadinessListener onEdit) {
        super(id);

        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        Form form = new Form("form");
        add(form);

        form.add(new TextField<>("name", new PropertyModel<>(declaration, "name"))
                .setRequired(true)
                .add(new OnlineModelUpdateBehavior()));
        form.add(new TextArea<>("description", new PropertyModel<>(declaration, "description"))
                .add(new OnlineModelUpdateBehavior()));

        form.add(new AjaxButton("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                gymDairyService.updateMoveDeclaration(declaration);
                target.add(feedbackPanel);
                window.close(target);
                onEdit.onReady(target);
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
