package wasdev.sample.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

import wasdev.sample.Recipe;
import wasdev.sample.RecipeRequest;
import wasdev.sample.store.RecipeStore;
import wasdev.sample.store.RecipeStoreFactory;

@ApplicationPath("api")
@Path("/reciperequestapi")
public class RecipeRequestAPI  extends Application {
	
	//Our database store
		RecipeStore store = RecipeStoreFactory.getInstance();
	/**
     * Requests a Recipe in a specified language.
     *
     * REST API example:
     * <code>
     * POST http://localhost:8080/ChefGabrielReceipes/api/recipes
     * <code>
     * POST Body:
     * <code>
     * {
     *   "name":"ice cream"
     * }
     * </code>
     * Response:
     * <code>
     * {
     *   "id":"123",
     *   "name":"ice cream"
     *   "recipe":"Mix milk and cream add four eggs. Freeze."
     * }
     * </code>
     * @param recipe The new Recipe to create.
     * @return The Recipe after it has been stored.  This will include a unique ID for the Recipe.
     */
    @POST
    @Produces("application/text")
    @Consumes("application/json")
    public String newToDo(RecipeRequest recipeRequest) {
    	Recipe recipe = null;
		for (Recipe doc : store.getAll()) {
			if (doc.getName().equals(recipeRequest.getRecipeName())){
				recipe = doc;
			}
		}
    	return translateToLanguage(recipe.getReceipe(), recipeRequest.getTranslatedLanguage());
    }
    
    /*
     * Translates the recipe into the requested language.
     * Note that the Username and Password of the Watson service would not be stored here
     * in production code. They would be properties that can be configured on the server or in a properties file or a properties database.
     *  @param text
     *  			The recipe that was retreived that will be translated.
     *  @param langauge
     *  			The requested language that the recipe must be translated to.
     *  @return The translated recipe.
     */
    private String translateToLanguage(String text, String language){
    	LanguageTranslator service = new LanguageTranslator();
    	service.setUsernameAndPassword("7455df26-7127-40bb-894b-9c867f9d809f", "XSYntbFaD0IX");
    	String textreturned = null;
    	try {
    		if(!"ENGLISH".equals(language)){
    		 TranslationResult translationResult = service.translate(text, Language.ENGLISH, Language.valueOf(language)).execute();
    		 textreturned = translationResult.getFirstTranslation();
    		}else{
    			textreturned = text;	
    		}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
    	return textreturned;
    	}
}
