package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.describing;

import com.hazardousdev.gym_dairy.model.Feature;
import com.hazardousdev.gym_dairy.wicket.component.TitledLabel;
import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.List;
import java.util.Set;

/**
 * @author alcovp
 */
public class FeaturesPanel extends Panel {
    public FeaturesPanel(String id, IModel<List<Feature>> model) {
        super(id);

        ListView<Feature> features = new ListView<Feature>("list", model) {
            @Override
            protected void populateItem(ListItem<Feature> item) {
                final String cssClass = item.getModelObject().getCssClass();
                item.add(new TitledLabel("feature", "", item.getModelObject().getTooltip())
                        .add(new ClassAttributeModifier() {
                            @Override
                            protected Set<String> update(Set<String> oldClasses) {
                                oldClasses.add(cssClass);
                                return oldClasses;
                            }
                        }));
            }
        };
        add(features);
    }
}
