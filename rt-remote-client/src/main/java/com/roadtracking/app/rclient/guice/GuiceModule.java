package com.roadtracking.app.rclient.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.roadtracking.persistence.dao.api.IDao;
import com.roadtracking.persistence.dao.impl.Dao;
import com.roadtracking.util.guice.core.StageModule;
import com.roadtracking.util.guice.provider.logger.DefaultLoggerProvider;
import com.roadtracking.util.guice.provider.logger.Slf4JTypeListener;
import org.slf4j.Logger;

public class GuiceModule extends StageModule {

    @Override
    protected void generalConfigure() {
        // @formatter:off
		bindListener(Matchers.any(), new Slf4JTypeListener());
		bind(Logger.class).toProvider(DefaultLoggerProvider.class);

        bind(IDao.class).to(Dao.class);
        // @formatter:on
    }

    @Override
    protected void productionConfigure() {

    }

    @Override
    protected void developmentConfigure() {

    }

    @Override
    protected <T, G> TypeLiteral<T> getTypeLiteral(Class<T> type, Class<G> genericType) {
        return null;
    }
}
