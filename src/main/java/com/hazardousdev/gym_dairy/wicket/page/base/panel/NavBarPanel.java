package com.hazardousdev.gym_dairy.wicket.page.base.panel;

import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import java.util.List;
import java.util.Set;

/**
 * @author alcovp
 */
public class NavBarPanel extends Panel {

    private static final String SELECTED_ITEM_CLASS = "nav-bar-item-selected";

    public NavBarPanel(String id, IModel<List<NavBarItem>> itemsModel) {
        super(id);

        ListView<NavBarItem> navBarItems = new ListView<NavBarItem>("list", itemsModel) {
            @Override
            protected void populateItem(final ListItem<NavBarItem> listItem) {
                BookmarkablePageLink<Object> link = new BookmarkablePageLink<>("link", listItem.getModelObject().getPageClass());
                link.add(new Label("label", new ResourceModel(listItem.getModelObject().getLabelResourceId())));
                listItem.add(link);
                listItem.add(new ClassAttributeModifier() {
                    @Override
                    protected Set<String> update(Set<String> oldClasses) {
                        if (getPage().getClass().equals(listItem.getModelObject().getPageClass())) {
                            if (!oldClasses.contains(SELECTED_ITEM_CLASS)) {
                                oldClasses.add(SELECTED_ITEM_CLASS);
                            }
                        } else {
                            oldClasses.remove(SELECTED_ITEM_CLASS);
                        }
                        return oldClasses;
                    }
                });
            }
        };
        add(navBarItems);

        add(new Link("logout-link") {

            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
    }
}
