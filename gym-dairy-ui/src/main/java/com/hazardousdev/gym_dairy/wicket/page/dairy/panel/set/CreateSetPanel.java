package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.set;

import com.hazardousdev.gym_dairy.model.Set;
import com.hazardousdev.gym_dairy.wicket.behavior.OnlineModelUpdateBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author alcovp
 */
public class CreateSetPanel extends Panel {

    public CreateSetPanel(String id, IModel<Set> setModel) {
        super(id);

        add(new TextField<>("weight", new PropertyModel<Float>(setModel.getObject(), "weight"))
                .add(new OnlineModelUpdateBehavior()));
        add(new TextField<>("repeats", new PropertyModel<Integer>(setModel.getObject(), "repeats"))
                .setRequired(true)
                .add(new OnlineModelUpdateBehavior()));
    }
}
