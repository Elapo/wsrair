/*
 * package com.realdolmen.service;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.junit.Assert.assertNotNull; import static org.junit.Assert.assertTrue;
 * 
 * import javax.ws.rs.client.Client; import javax.ws.rs.client.ClientBuilder;
 * import javax.ws.rs.client.Entity; import javax.ws.rs.client.WebTarget; import
 * javax.ws.rs.core.MediaType; import javax.ws.rs.core.Response;
 * 
 * import org.junit.Before; import org.junit.Test;
 * 
 * import com.realdolmen.domain.Owner;
 * 
 * public class OwnerRestServiceIntegrationTest { private static final String
 * REST_SERVICE_URL = "http://localhost:8080/jsftemplate/my-rest-api";
 * 
 * private WebTarget target;
 * 
 * @Before public void initializeClient() { Client client =
 * ClientBuilder.newClient(); target = client.target(REST_SERVICE_URL); }
 * 
 * @Test public void shouldFindOwner() { target = target.path("owner"); target =
 * target.path("8"); Owner owner =
 * target.request(MediaType.APPLICATION_JSON).method("get", Owner.class);
 * assertNotNull(owner); assertEquals("Jane", owner.getFirstName()); }
 * 
 * @Test public void shouldCreateOwner() { Owner owner = new Owner("Test",
 * "LastTtest", "birth"); target = target.path("owner"); Response response =
 * target.request(MediaType.APPLICATION_JSON) .post(Entity.entity(owner,
 * MediaType.APPLICATION_JSON)); assertEquals(201, response.getStatus());
 * assertNotNull(response.getHeaderString("Location"));
 * assertTrue(response.getHeaderString("Location")
 * .startsWith("http://localhost:8080/jsftemplate/my-rest-api/owner/")); } }
 */