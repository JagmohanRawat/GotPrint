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

import com.notes.db.NoteRepository;
import com.notes.entity.NoteDetailsTo;
import com.notes.entity.NoteTo;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class NoteResource {

	private NoteRepository noteRepo = new NoteRepository();
	@RolesAllowed("ADMIN")
	@Path("/note-create/{userId}")
	@POST
	public Response create(@Valid NoteTo noteTo, @Valid @Min(value=1) @PathParam("userId")int id) {
		try {
			noteRepo.create(noteTo, id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/note-update/{id}")
	@PUT
	public Response update(@Valid NoteTo noteTo, @Valid @Min(value = 1) @PathParam("id") int id) {
		try {
			int rowsUpdated = noteRepo.update(noteTo, id);
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
	@Path("/note-delete/{id}")
	@DELETE
	public Response delete(@Valid @Min(value = 1) @PathParam("id") int id) {
		try {
			Object user = noteRepo.delete(id);
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
	@Path("/note-delete-by-user/{id}")
	@DELETE
	public Response deleteByUserId(@Valid @Min(value = 1) @PathParam("id") int id) {
		try {
			Object user = noteRepo.deleteAllByUserId(id);
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
	@Path("/note-find/{id}")
	@GET
	public Response find(@Valid @Min(value = 1) @PathParam("id") int id) {
		try {
			NoteDetailsTo noteDetails = noteRepo.find(id);
			if (noteDetails == null) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
			}
			return Response.ok(noteDetails).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/note-find-all")
	@GET
	public Response findAll() {
		try {
			List<NoteDetailsTo> noteDetails = noteRepo.findAll();
			return Response.ok(noteDetails).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}
	@RolesAllowed("ADMIN")
	@Path("/note-find-all/{id}")
	@GET
	public Response findAllByUserId(@Valid @Min(value = 1) @PathParam("id") int id) {
		try {
			List<NoteDetailsTo> noteDetails = noteRepo.findAllByUserId(id);
			return Response.ok(noteDetails).build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.serverError().type(MediaType.APPLICATION_JSON).build();

	}

}
