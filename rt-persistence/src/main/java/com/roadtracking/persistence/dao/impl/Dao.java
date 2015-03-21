package com.roadtracking.persistence.dao.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.roadtracking.persistence.dao.api.IDao;

import java.util.List;

public class Dao implements IDao {
    @Override
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> kindClass) {
        return null;
    }

    @Override
    public <ENTITY> ENTITY getEntity(Class<ENTITY> kindClass, long id) {
        return null;
    }

    @Override
    public <ENTITY> ENTITY getEntity(Class<ENTITY> kindClass, String name) {
        return null;
    }

    @Override
    public <ENTITY> Key<ENTITY> put(ENTITY entity) {
        return null;
    }

    @Override
    public <ENTITY> Ref<ENTITY> createRef(ENTITY entity) {
        return null;
    }

    @Override
    public <ENTITY> Ref<ENTITY> createRef(Class<ENTITY> kindClass, long id) {
        return null;
    }

    @Override
    public <ENTITY> void deleteEntitiesViaIds(Class<ENTITY> kindClass, List<Long> ids) {

    }
}