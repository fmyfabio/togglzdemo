package org.gradle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import org.togglz.mongodb.MongoStateRepository;

import com.mongodb.MongoClient;

@Component
public class DemoTogglzConfig implements TogglzConfig {
	@Autowired
	private MongoClient mongoClient;
	@Override
	public Class<? extends Feature> getFeatureClass() {
		return DemoFeatures.class;
	}

	@Override
	public StateRepository getStateRepository() {
		return MongoStateRepository.newBuilder(mongoClient, "togglz").build();
	}

	@Override
	public UserProvider getUserProvider() {
		return new UserProvider() {

            @Override
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser(null, true);
            }
        };
	}
  // code
}