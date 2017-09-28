package com.hazardousdev.gym_dairy.wicket.page.dairy.panel.describing;

import com.hazardousdev.gym_dairy.model.Note;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * @author alcovp
 */
public class NotesPanel extends Panel {
    public NotesPanel(String id, IModel<List<Note>> model) {
        super(id);

        ListView<Note> notes = new ListView<Note>("list", model) {
            @Override
            protected void populateItem(ListItem<Note> item) {
                item.add(new Label("name", item.getModelObject().getName()));
                item.add(new Label("text", item.getModelObject().getText()));
            }
        };
        add(notes);
    }
}
