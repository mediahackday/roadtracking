package com.roadtracking.util.guice.core;

import com.google.inject.AbstractModule;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;

public abstract class StageModule extends AbstractModule {

	@Override
	protected void configure() {
		generalConfigure();

		Stage stage = currentStage();
		if (Stage.PRODUCTION.equals(stage)) {
			productionConfigure();
		} else if (Stage.DEVELOPMENT.equals(stage)) {
			developmentConfigure();
		}
	}

	protected abstract void generalConfigure();

	protected abstract void productionConfigure();

	protected abstract void developmentConfigure();

	protected abstract <T, G> TypeLiteral<T> getTypeLiteral(Class<T> type, Class<G> genericType);
}