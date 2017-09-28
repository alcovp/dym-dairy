package com.hazardousdev.gym_dairy.wicket.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author alcovp
 */
public class TitledLabel extends Label {
    private IModel<String> titleModel;

    public TitledLabel(String id, String label) {
        super(id, label);
        titleModel = Model.of(label);
    }

    public TitledLabel(String id, IModel<String> model) {
        super(id, model);
        titleModel = model;
    }

    public TitledLabel(final String id, String label, String title) {
        super(id, label);
        this.titleModel = Model.of(title);
    }

    public TitledLabel(final String id, IModel<String> model, IModel<String> titleModel) {
        super(id, model);
        this.titleModel = titleModel;
    }

    public TitledLabel(String id, IModel<String> model, String title) {
        this(id, model, Model.of(title));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        tag.put("title", titleModel.getObject());
    }
}
