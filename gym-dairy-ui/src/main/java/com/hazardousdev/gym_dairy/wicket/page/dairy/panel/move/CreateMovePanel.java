package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.move;

import com.hazardousdev.gym_dairy.model.Move;
import com.hazardousdev.gym_dairy.model.MoveDeclaration;
import com.hazardousdev.gym_dairy.model.Set;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.set.CreateSetPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author alcovp
 */
public class CreateMovePanel extends Panel {

    private final IModel<MoveDeclaration> moveDeclarationModel;

    public CreateMovePanel(String id, final IModel<? extends List<MoveDeclaration>> moveDeclarationsModel, final IModel<Move> moveModel) {
        super(id);

        final IModel<? extends List<Set>> setsModel = new AbstractReadOnlyModel<List<Set>>() {
            @Override
            public List<Set> getObject() {
                if (moveModel.getObject().getSets() == null || moveModel.getObject().getSets().size() == 0) {
                    final ArrayList<Set> sets = new ArrayList<>();
                    moveModel.getObject().setSets(sets);
                }
                return moveModel.getObject().getSets();
            }
        };

        moveDeclarationModel = new PropertyModel<>(moveModel.getObject(), "moveDeclaration");

        DropDownChoice<MoveDeclaration> declarationChoice = new DropDownChoice<MoveDeclaration>(
                "declarations",
                moveDeclarationModel,
                moveDeclarationsModel,
                new IChoiceRenderer<MoveDeclaration>() {
                    @Override
                    public Object getDisplayValue(MoveDeclaration object) {
                        return object.getName();
                    }

                    @Override
                    public String getIdValue(MoveDeclaration object, int index) {
                        return String.valueOf(object.getId());
                    }

                    @Override
                    public MoveDeclaration getObject(String id, IModel<? extends List<? extends MoveDeclaration>> choices) {
                        return choices.getObject().stream()
                                .filter(c -> Objects.equals(String.valueOf(c.getId()), id))
                                .findFirst()
                                .orElse(null);
                    }
                }
        );
        declarationChoice.setRequired(true);
        declarationChoice.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                moveDeclarationModel.setObject(declarationChoice.getModelObject());
            }
        });
        add(declarationChoice);

        final WebMarkupContainer setListContainer = new WebMarkupContainer("list-container");
        setListContainer.setOutputMarkupId(true);
        add(setListContainer);

        ListView<Set> setList = new ListView<Set>("list", setsModel) {
            @Override
            protected void populateItem(ListItem<Set> item) {
                item.add(new CreateSetPanel("set", item.getModel()));
            }
        };
        setListContainer.add(setList);

        setListContainer.add(new AjaxLink<Void>("add") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                setsModel.getObject().add(new Set());
                target.add(setListContainer);
            }
        });
    }
}
