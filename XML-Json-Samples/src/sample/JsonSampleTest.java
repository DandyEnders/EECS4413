package sample;


import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


public class JsonSampleTest {
	public static void main(String[] args) {


		//we want something like this generated
		/*
		 * { "firstName": "John", "lastName": "Smith", "address" : { "streetAddress":
		 * "21 2nd Street"}, "phoneNumber": [ { "type": "home", "number":
		 * "212 555-1234" }, { "type": "mobile", "number": "646 555-4567" } ] }
		 */
		// 1. create a json builder and add objects to it
		JsonObjectBuilder doc = Json.createObjectBuilder();
		doc.add("firstName", "John").add("lastName", "Smith");
	
		//2. create an array for phones	
		JsonArrayBuilder phones=Json.createArrayBuilder();
		phones.add(Json.createObjectBuilder().add("type", "home").add("number", "212 555-1234"))
		.add(Json.createObjectBuilder().add("type", "mobile").add("number", "646 555-4567"));

		//3. now we can add the address
		doc.add("address", Json.createObjectBuilder().add("streetAddress", "21 2nd Street"))
		.add("phoneNumber",phones);
		
		//4. build the json object
		
		JsonObject value=doc.build();
		/*you can also create the object in one try, like this:
		JsonObject value = Json.createObjectBuilder().add("firstName", "John").add("lastName", "Smith")
				.add("address", Json.createObjectBuilder().add("streetAddress", "21 2nd Street"))
				.add("phoneNumber",
						Json.createArrayBuilder()
								.add(Json.createObjectBuilder().add("type", "home").add("number", "212 555-1234"))
								.add(Json.createObjectBuilder().add("type", "mobile").add("number", "646 555-4567")))
				.build();
		*/
		
		// 2. serialize it in a string
        String serializedJson=value.toString();
	
		//3.  serialize it to console..
		System.out.println(serializedJson);
		
		//4. Add code to serialize it to a File..
		

	}
}
