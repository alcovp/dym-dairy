package com.hazardousdev.gym_dairy.wicket.model;

import java.io.Serializable;

@FunctionalInterface
public interface IPropertyGetter<E, P> extends Serializable {
    P getPropertyValue(E entity);
}