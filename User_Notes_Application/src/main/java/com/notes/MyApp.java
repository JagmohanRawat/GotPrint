package com.notes;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.auth.MyAppAuthenticator;
import com.auth.MyAppAuthorizer;
import com.auth.UserCredential;
import com.notes.resource.NoteResource;
import com.notes.resource.UserResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Environment;

public class MyApp extends Application<MyConfig> {

	public static void main(String[] args) throws Exception {

		new MyApp().run(args);
	}

	@Override
	public void run(MyConfig configuration, Environment environment) throws Exception {

		environment.jersey().register(UserResource.class);
		environment.jersey().register(NoteResource.class);
		environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<UserCredential>()
                .setAuthenticator(new MyAppAuthenticator())
                .setAuthorizer(new MyAppAuthorizer())
                .setRealm("App Security")
                .buildAuthFilter()));
		environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserCredential.class));
		environment.jersey().register(RolesAllowedDynamicFeature.class);
	}
}
