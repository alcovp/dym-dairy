package com.hazardousdev.gym_dairy.wicket.listener;

import org.apache.wicket.ajax.AjaxRequestTarget;

import java.io.Serializable;
import java.util.EventListener;

/**
 * @author alcovp
 */
public interface AjaxReadinessListener extends EventListener, Serializable {
    void onReady(AjaxRequestTarget target);
}
