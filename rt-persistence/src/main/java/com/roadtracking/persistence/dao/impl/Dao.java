package com.roadtracking.persistence.dao.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.roadtracking.persistence.dao.api.IDao;
import com.roadtracking.persistence.entity.AuthSecret;
import com.roadtracking.persistence.entity.Businessman;
import com.roadtracking.persistence.entity.Customer;
import com.roadtracking.persistence.entity.Driver;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static com.googlecode.objectify.ObjectifyService.register;

public class Dao implements IDao {
    static {
        register(Customer.class);
        register(Driver.class);
        register(Businessman.class);
        register(AuthSecret.class);
    }

    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> kindClass) {
        return ofy().load().type(kindClass).list();
    }

    public <ENTITY> List<ENTITY> getEntities(Class<ENTITY> kindClass, List<Long> ids) {
        List<ENTITY> entities = new ArrayList<>();
        List<Key<ENTITY>> keys = new ArrayList<>();
        for (Long id : ids) {
            keys.add(Key.create(kindClass, id));
        }
        entities.addAll(ofy().load().keys(keys).values());
        return entities;
    }

    public <ENTITY> ENTITY getEntity(Class<ENTITY> kindClass, long id) {
        return ofy().load().key(Key.create(kindClass, id)).now();
    }

    public <ENTITY> ENTITY getEntity(Class<ENTITY> kindClass, String name) {
        return ofy().load().key(Key.create(kindClass, name)).now();
    }

    public <ENTITY> Key<ENTITY> put(ENTITY entity) {
        return ofy().save().entity(entity).now();
    }

    public <ENTITY> Ref<ENTITY> createRef(ENTITY entity) {
        return Ref.create(entity);
    }

    public <ENTITY> Ref<ENTITY> createRef(Class<ENTITY> kindClass, final long id) {
        return Ref.create(Key.create(kindClass, id));
    }

    public <ENTITY> void deleteEntitiesViaIds(final Class<ENTITY> kindClass, final List<Long> ids) {
        final List<Key<?>> keys = new ArrayList<>();
        for (final long id : ids) {
            keys.add(Key.create(kindClass, id));
        }
        ofy().delete().keys(keys);
    }
}