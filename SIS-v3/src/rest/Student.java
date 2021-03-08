package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.SisModel;

@Path("student")
public class Student {
	
	private SisModel model;

	public Student() throws ClassNotFoundException {
		model = SisModel.getInstance();
	}
	
	@GET
	@Path("/ping/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {	
		System.out.println("received: Ping");
		return "pong!";
	}
	
	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getStudent(@QueryParam("studentName") String name){
		System.out.println("Access getStudent: " + name);
		
		String result;
		try {
			result = this.model.restGET(name);
		} catch (Exception e) {
			result = "Error: " + e;
			e.printStackTrace();
		}
		
		return result;
	}
	
	@POST
	@Path("/create")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String createStudent(
			@QueryParam("sid") String sid,
			@QueryParam("givenName") String givenName,
			@QueryParam("surName") String surname,
			@QueryParam("creditTaken") String creditTaken,
			@QueryParam("creditGraduate") String creditGraduate
			) {
		System.out.println("Access createStudent: " + sid);
		String result;
		try {
			result = this.model.restCREATE(sid, givenName, surname, creditTaken, creditGraduate);
		} catch (Exception e) {
			result = "Error: " + e;
			e.printStackTrace();
		}
		
		return result;
	}
	
	@DELETE
	@Path("/delete/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String deleteStudent(@QueryParam("sid") String sid) {
		System.out.println("Access deleteStudent: " + sid);
		String result;
		try {
			result = this.model.restDELETE(sid);
		} catch (Exception e) {
			result = "Error: " + e;
			e.printStackTrace();
		}
		
		return result;
	}
	
	

}
