package wasdev.sample;

/*
 * This class will represent a request for a recipe. The class also associates a recipe with a language for translation.
 */
public class RecipeRequest {

	/*
	 * Name of the recipe that is requested
	 */
	private String recipeName;
	/*
	 * the desired language that the recipe must be received in.
	 */
	private String translatedLanguage;

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getTranslatedLanguage() {
		return translatedLanguage;
	}

	public void setTranslatedLanguage(String translatedLanguage) {
		this.translatedLanguage = translatedLanguage;
	}
	
}
