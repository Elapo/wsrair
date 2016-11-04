package com.realdolmen.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.realdolmen.domain.Owner;

@Path("owner")
@Stateless
public class OwnerRestService {
	@PersistenceContext
	EntityManager em;

	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ownerId}")
	public Owner findOwnerById(@PathParam("ownerId") Long ownerId) {
		return em.find(Owner.class, ownerId);
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response createOwner(Owner newOwner) {
		// Owner owner = new Owner(firstName, lastName, birthDate);
		em.persist(newOwner);
		UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
		uriBuilder.path("owner");
		uriBuilder.path("{ownerId}");
		URI uri = uriBuilder.build(newOwner.getId());
		System.out.println(uri.toString());
		Response.ResponseBuilder responseBuilder = Response.created(uri);
		return responseBuilder.build();
	}

	@Produces("image/jpeg")
	@GET
	@Path("cover")
	public Response getCover() throws IOException {
		File img = new File("C:/eru-illuvatar/workspace/jsftemplate/src/image.jpg");
		BufferedImage bufferedImage = ImageIO.read(img);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpeg", baos);
		byte[] imageData = baos.toByteArray();
		return Response.ok(imageData).build();
	}
}
