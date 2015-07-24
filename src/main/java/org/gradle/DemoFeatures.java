package org.gradle;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;


public enum DemoFeatures implements Feature {
	@Label("Dummy feature flag, it only exists for testing purposes")
    DUMMY_FEATURE_FLAG;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
