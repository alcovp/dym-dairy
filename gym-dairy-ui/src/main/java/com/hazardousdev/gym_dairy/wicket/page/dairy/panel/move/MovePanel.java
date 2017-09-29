package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.move;

import com.hazardousdev.gym_dairy.model.Move;
import com.hazardousdev.gym_dairy.model.Set;
import com.hazardousdev.gym_dairy.wicket.model.GetterModel;
import com.hazardousdev.gym_dairy.wicket.page.dairy.panel.set.SetPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author alcovp
 */
public class MovePanel extends Panel {
    public MovePanel(String id, IModel<Move> moveModel) {
        super(id);

        add(new Label("name", GetterModel.ofModel(moveModel, entity -> entity.getMoveDeclaration().getName())));

        ListView<Set> moves = new ListView<Set>("list", GetterModel.ofModel(moveModel, Move::getSets)) {
            @Override
            protected void populateItem(ListItem<Set> item) {
                item.add(new SetPanel("set", item.getModel()));
            }
        };
        add(moves);
    }
}
