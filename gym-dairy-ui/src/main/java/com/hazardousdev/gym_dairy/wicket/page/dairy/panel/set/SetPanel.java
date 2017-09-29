package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.set;

import com.hazardousdev.gym_dairy.model.Set;
import com.hazardousdev.gym_dairy.wicket.model.GetterModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author alcovp
 */
public class SetPanel extends Panel {
    public SetPanel(String id, IModel<Set> setModel) {
        super(id);

        add(new Label("weight", GetterModel.ofModel(setModel, Set::getWeight)));
        add(new Label("repeats", GetterModel.ofModel(setModel, Set::getRepeats)));
    }
}
