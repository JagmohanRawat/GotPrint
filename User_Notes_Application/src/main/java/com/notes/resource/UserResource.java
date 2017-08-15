package com.notes.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.auth.UserCredential;
import com.notes.db.UserRepository;
import com.notes.entity.UserDetailsTo;
import com.notes.entity.UserTo;

import io.dropwizard.auth.Auth;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private UserRepository userRepo = new UserRepository();
	@RolesAllowed("USER")
	@Path("/user-create")
	@POST
	public Response create(@Valid  UserTo userTo,@Auth UserCredential user) {
		try {
			userRepo.create(userTo);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/user-update/{id}")
	@PUT
	public Response update(@Valid UserTo userTo, @Valid @Min(value = 1) @PathParam("id") int id,@Auth UserCredential user) {
		try {
			int rowsUpdated = userRepo.update(userTo, id);
			if (rowsUpdated <= 0) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/user-delete/{id}")
	@DELETE
	public Response delete(@Valid @Min(value = 1) @PathParam("id") int id,@Auth UserCredential use) {
		try {
			Object user = userRepo.delete(id);
			if (user == null) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/user-find/{id}")
	@GET
	public Response find(@Valid @Min(value = 1) @PathParam("id") int id,@Auth UserCredential user) {
		try {
			UserDetailsTo userDetails = userRepo.find(id);
			if (userDetails == null) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
			}
			return Response.ok(userDetails).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/user-find-all")
	@GET
	public Response findAll(@Auth UserCredential user) {
		try {
			List<UserDetailsTo> userDetails = userRepo.findAll();
			return Response.ok(userDetails).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
}
