package com.auth;

import io.dropwizard.auth.Authorizer;

public class MyAppAuthorizer implements Authorizer<UserCredential> {

	@Override
	public boolean authorize(UserCredential user, String role) {
		return user.getRoles() != null && user.getRoles().contains(role);
	}
}