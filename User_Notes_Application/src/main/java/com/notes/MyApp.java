package com.notes;

import com.notes.resource.NoteResource;
import com.notes.resource.UserResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class MyApp extends Application<MyConfig> {

	public static void main(String[] args) throws Exception {

		new MyApp().run(args);
	}

	@Override
	public void run(MyConfig configuration, Environment environment) throws Exception {

		environment.jersey().register(UserResource.class);
		environment.jersey().register(NoteResource.class);
		
	}
}
