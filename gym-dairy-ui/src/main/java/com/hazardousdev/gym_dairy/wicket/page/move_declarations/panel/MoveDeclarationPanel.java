package com.hazardousdev.gym_dairy.wicket.page.move_declarations.panel;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.listener.AjaxReadinessListener;
import com.hazardousdev.gym_dairy.wicket.model.GetterModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author alcovp
 */
public class MoveDeclarationPanel extends Panel {

    @Inject
    private IGymDairyService gymDairyService;

    public MoveDeclarationPanel(String id, IModel<MoveDeclaration> moveDeclaration, AjaxReadinessListener onEdit) {
        super(id);

        WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        add(container);

        ModalWindow window = new ModalWindow("window");
        add(window);

        container.add(new Label("name", GetterModel.ofModel(moveDeclaration, MoveDeclaration::getName)));
        container.add(new Label("description", GetterModel.ofModel(moveDeclaration, MoveDeclaration::getDescription)));

        container.add(new AjaxLink<Void>("edit") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                window.setContent(new MoveDeclarationEditPanel(
                        window.getContentId(),
                        moveDeclaration.getObject(),
                        window,
                        t -> t.add(container)
                ));
                window.show(target);
            }
        });

        container.add(new AjaxLink<Void>("delete") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                gymDairyService.deleteMoveDeclaration(moveDeclaration.getObject().getId());
                onEdit.onReady(target);
            }
        });
    }
}
