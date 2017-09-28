package com.hazardousdev.gym_dairy.wicket.behavior;

import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

/**
 * @author alcovp
 */
public abstract class ReliableOnChangeAjaxBehavior extends AjaxFormComponentUpdatingBehavior {
    private static final long serialVersionUID = 1L;

    /**
     * 'inputchange' event delegates to 'input', 'keyup', 'cut' and 'paste' events
     * for text input form component depending on the browser.
     * 'change' is used as a fallback for all other form component types.
     */
    public static final String EVENT_NAME = "inputchange change";

    /**
     * Constructor.
     */
    public ReliableOnChangeAjaxBehavior() {
        super(EVENT_NAME);
    }

}