package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testSIS3 {
	
	private static Client client;
	
	public testSIS3() {
		
	}

	@Before
	public void setup() {
		client = ClientBuilder.newClient();
	}
	
	@After
	public void teardown() {
		client.close();
	}
	
	@Test(timeout = 2000)
	public void testREST() {
		String result;
		
		Form form = new Form();
//		form.param("sid", "003")
//			.param("givenName","John")
//			.param("surName","Wayne")
//			.param("creditTaken","3")
//			.param("creditGraduate","1");
		
		// CREATE
		result=client.target("http://localhost:8080/SIS-v3/rest/student/create")
				  .queryParam("sid", "003")
				  .queryParam("givenName", "John")
				  .queryParam("surName", "Wayne")
				  .queryParam("creditTaken", "3")
				  .queryParam("creditGraduate", "1")
		          .request(MediaType.TEXT_PLAIN)
		          .post(Entity.entity(form,MediaType.TEXT_PLAIN),String.class);
		
		assertEquals("insertedRows:1", result);
		
		// READ
		result=client.target("http://localhost:8080/SIS-v3/rest/student/read")	
		          .queryParam("studentName", "Way")
		          .request(MediaType.TEXT_PLAIN)
		          .get(String.class);
		
//		assertEquals("<sisReport namePrefix=\"Way\" creditTaken=\"0\">" +
//				"<studentList sid=\"003\" name=\"John, Wayne\" creditTaken=\"3\" creditGraduate=\"1\"/>" +
//				"</sisReport>", result);
		
		// DELETE
		result=client.target("http://localhost:8080/SIS-v3/rest/student/delete")	
		          .queryParam("sid", "003")
		          .request(MediaType.TEXT_PLAIN)
		          .delete(String.class);
		assertEquals("deletedRows:1", result);
	}

}
