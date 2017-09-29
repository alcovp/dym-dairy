package com.hazardousdev.gym_dairy.wicket.page.move_declarations;

import com.google.inject.Inject;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.application.GymDairyAuthenticatedWebSession;
import com.hazardousdev.gym_dairy.wicket.page.base.BasePage;
import com.hazardousdev.gym_dairy.wicket.page.move_declarations.panel.MoveDeclarationCreatePanel;
import com.hazardousdev.gym_dairy.wicket.page.move_declarations.panel.MoveDeclarationPanel;
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
public class MoveDeclarationsPage extends BasePage {

    @Inject
    private IGymDairyService gymDairyService;

    public MoveDeclarationsPage(PageParameters parameters) {
        super(parameters);

        final IModel<List<MoveDeclaration>> declarationsModel = new AbstractReadOnlyModel<List<MoveDeclaration>>() {
            @Override
            public List<MoveDeclaration> getObject() {
                return gymDairyService.getMoveDeclarations(GymDairyAuthenticatedWebSession.get().getCurrentUserId());
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
                window.setContent(new MoveDeclarationCreatePanel(
                        window.getContentId(),
                        window,
                        t -> t.add(listContainer)
                ));
                window.show(target);
            }
        };
        add(addLink);

        ListView<MoveDeclaration> declarations = new ListView<MoveDeclaration>("list", declarationsModel) {
            @Override
            protected void populateItem(ListItem<MoveDeclaration> item) {
                item.add(new MoveDeclarationPanel(
                        "move-declaration",
                        item.getModel(),
                        target -> target.add(listContainer)
                ));
            }
        };
        listContainer.add(declarations);
    }
}
