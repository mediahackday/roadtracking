package com.roadtracking.persistence.dao.api;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import java.util.List;

public interface IDao {

	<ENTITY> List<ENTITY> getAll(Class<ENTITY> kindClass);

	/**
	 * Retrieve entity instance from datastore via id.
	 *
	 * @param kindClass
	 *            kind class
	 * @param id
	 *            id
	 * @return entity
	 */
	<ENTITY> ENTITY getEntity(Class<ENTITY> kindClass, long id);

	/**
	 * Retrieve entity instance from datastore via name.
	 *
	 * @param kindClass
	 *            kind class
	 * @param name
	 *            name
	 * @return entity
	 */
	<ENTITY> ENTITY getEntity(Class<ENTITY> kindClass, String name);

	<ENTITY> Key<ENTITY> put(ENTITY entity);

	<ENTITY> Ref<ENTITY> createRef(ENTITY entity);

	<ENTITY> Ref<ENTITY> createRef(Class<ENTITY> kindClass, final long id);

    <ENTITY> Ref<ENTITY> createRef(Class<ENTITY> kindClass, final String name);

	<ENTITY> void deleteEntitiesViaIds(Class<ENTITY> kindClass, List<Long> ids);
}
