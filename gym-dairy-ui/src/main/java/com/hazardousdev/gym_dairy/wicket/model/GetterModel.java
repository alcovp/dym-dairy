package com.hazardousdev.gym_dairy.wicket.model;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import java.util.Objects;

public class GetterModel<E, P> extends AbstractReadOnlyModel<P> {
    private final E entity;
    private final IModel<E> entityModel;
    private final IPropertyGetter<E, P> getter;

    private GetterModel(E entity, IModel<E> entityModel, IPropertyGetter<E, P> getter) {
        this.entity = entity;
        this.entityModel = entityModel;
        this.getter = getter;
    }

    public static <E, P> GetterModel<E, P> ofObject(E entity, IPropertyGetter<E, P> getter) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        Objects.requireNonNull(getter, "Getter cannot be null");

        return new GetterModel<>(entity, null, getter);
    }

    public static <E, P> GetterModel<E, P> ofModel(IModel<E> entityModel, IPropertyGetter<E, P> getter) {
        Objects.requireNonNull(entityModel, "Entity model cannot be null");
        Objects.requireNonNull(getter, "Getter cannot be null");

        return new GetterModel<>(null, entityModel, getter);
    }

    @Override
    public P getObject() {
        final E effectiveEntity = getEntity();

        // mimicking PropertyModel behavior: if innermost object is null,
        // we return null to avoid NullPointerException
        if (effectiveEntity == null) {
            return null;
        }

        return getter.getPropertyValue(effectiveEntity);
    }

    private E getEntity() {
        return entityModel != null ? entityModel.getObject() : entity;
    }
}
