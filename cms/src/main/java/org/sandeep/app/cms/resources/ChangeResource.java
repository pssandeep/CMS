package org.sandeep.app.cms.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sandeep.app.cms.model.Change;
import org.sandeep.app.cms.service.ChangeService;

@Path("/changes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChangeResource {

	private ChangeService changeService = new ChangeService();

	@GET
	public List<Change> getChanges() {
		return changeService.getAllChanges();
	}

	@GET
	@Path("/{changeid}")
	public List<Change> getChange(@PathParam("changeid") String id) {
		return changeService.getChange(id);
	}

	@POST
	public Change addChange(Change change) {

		return changeService.addChange(change);
	}

	@PUT
	@Path("/{messageid}")
	public Change updateChange(@PathParam("messageid") String id, Change change) {
		return changeService.updateChange(id, change);
	}

	@DELETE
	@Path("/{messageid}")
	public void deleteChange(@PathParam("messageid") String id) {

		changeService.deleteChange(id);
	}

}
