package wasdev.sample.rest;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;

@ApplicationPath("api")
@Path("/languages")
public class LangaugesAPI extends Application {

	
  /**
   * Gets all Languages.
   * REST API example:
   * <code>
   * GET http://localhost:8080/ChefGabrielReceipes/api/languages
   * </code>
   *
   * Response:
   * <code>
   * [ "es", "en" ]
   * </code>
   * @return A collection of all the Visitors
   */
    @GET
    // @Path("/") // This anotation works in Liberty but not with Tomcat/Jersey.
    @Produces({"application/json"})
    public String getLanguages() {

    	 Language[] listOfLanguages = Language.values();
    	 List<String> languages = new ArrayList<String>();
    	 for(Language language : listOfLanguages){
    		 languages.add(language.name());
    	 }
    	 Gson gson = new Gson();  
    	 String languagesJson = gson.toJson(languages); 
    	 System.out.println("languagesJson ="+ languagesJson);
		return languagesJson;
    }

}
