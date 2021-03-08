package restClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class SISV3Client {

	public static void main(String[] argv){
		//create a client
		Client client = ClientBuilder.newClient();
		String result;
		
		Form form = new Form();
		form.param("sid", "003")
			.param("givenName","John")
			.param("surName","Wayne")
			.param("creditTake","3")
			.param("creditGraduate","1");
		
		// CREATE
		result=client.target("'http://localhost:8080/SISv3/rest/student/create")
		          .request(MediaType.TEXT_PLAIN)
		          .post(Entity.entity(form,MediaType.TEXT_PLAIN),String.class);
		System.out.println(result);         
		
		// READ
		result=client.target("‘http://localhost:8080/SISv3/rest/student/read")	
		          .queryParam("studentName", "Way")
		          .request(MediaType.TEXT_PLAIN)
		          .get(String.class);
		System.out.println(result); 
		
		// DELETE
		result=client.target("http://localhost:8080/RESTSample/rest/pfs/plant/delete/")	
		          .queryParam("sid", "003")
		          .request(MediaType.TEXT_PLAIN)
		          .delete(String.class);
		System.out.println(result); 
		
		client.close();	
	}

}
