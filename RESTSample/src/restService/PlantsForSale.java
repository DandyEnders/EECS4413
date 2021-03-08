package restService;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//this class is a simple implementation of a REST service
//it is a Plant catalog, with initial plants

@Path("pfs")  //this is the path of the service
public class PlantsForSale {
     HashMap<String, String> catalog;
     
	public PlantsForSale() {
		catalog=new HashMap<String, String>();
		catalog.put("rose", "$7.0");
		catalog.put("tulip", "$5.0");
		System.out.println("reset");
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {	
		System.out.println("received: empty");
		return "hello for testing";
	}

	
	/* GET fps/plant?plantname=x     */
	//this is a READ method on the service
	//the resource name is plant, the operation is get, the parameters are passed as 
	//query parameters in the url
	//once you deploy this, you can access this method with
	//http://localhost:8080/RESTSample/rest/pfs/plant?plantName=rose
	//note the annotations
	
	
	@GET
    @Path("/plant/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPrice(@DefaultValue("rose") @QueryParam("plantName") String name) {	
		System.out.println("received:" +name);
		return (String) catalog.get(name);
	}
	
	/* GET fps/plant/add?plantname=x&price=y */
	//this is a CREATE method on the service
	//the resource name is plant, the operation is get, the parameters are passed as 
	//query parameters in the url
	//once you deploy this, you can access this method with
	//http://localhost:8080/RESTSample/rest/pfs/plant/add?plantName=y&price=$56
	//note the annotations
	
	@GET
    @Path("/plant/add/")
	@Consumes(MediaType.TEXT_PLAIN)
	public String addPlant(@QueryParam("plantName")String name, @QueryParam("price")String price)
	{
		System.out.println("received:" +name +" "+ price);
		catalog.put(name, price);
		return getCatalogAsString();
	}
	
	/* POST fps/plant/create       */
	//this is a CREATE method on the service
	//the resource name is plant, the operation is POST, the parameters are passed as 
	//parameters in a form
	//once you deploy this, you can access this method with
	//http://localhost:8080/RESTSample/rest/pfs/plant/create
	//you can invoke it at the above address but need to include the parameters
	//in the body..check the client samples of how to do it from Java
	

	@POST
    @Path("/plant/create/")
	@Consumes(MediaType.TEXT_PLAIN)
	public String createPlant(@QueryParam("plantName")String name, @QueryParam("price")String price)
	{
		System.out.println("received:" +name +" "+ price);
		catalog.put(name, price);
		return getCatalogAsString();
	}

	
	
	public String getCatalogAsString() {
		String result="Catalog</br>";
		
        Set ms = (Set) catalog.entrySet();
        //Create iterator on Set 
        Iterator mapIterator = ms.iterator();
        while (mapIterator.hasNext()) {
        	    Map.Entry me = (Map.Entry) mapIterator.next();
            // getKey 
            String key = (String) me.getKey();
            //getValue method returns corresponding key's value
            String value = (String) me.getValue();
            result+= key + "  " + value+"</br>";
        }
        return result;
	}
	
	@PUT
    @Path("/plant/update/")
	@Consumes(MediaType.TEXT_PLAIN)
    public String updatePlant(@QueryParam("plantName") String name, @QueryParam("price") String price) {
		System.out.println("received:" +name + " " + price);
		System.out.println("before: " + getCatalogAsString());
    	catalog.replace (name, price);
    	System.out.println("after: " + getCatalogAsString());
    	return getCatalogAsString();
    }
	
	@DELETE
    @Path("/plant/delete/")
	@Consumes(MediaType.TEXT_PLAIN)
	public String deletePlant(@QueryParam("plantName") String name) {
		System.out.println("received:" +name );
    	catalog.remove(name);
    	return getCatalogAsString();
	}
	



}
