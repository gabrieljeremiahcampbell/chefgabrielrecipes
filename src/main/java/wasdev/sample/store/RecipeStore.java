/*
 * Modified by Gabriel J. Campbell from IBM example.
 */
package wasdev.sample.store;

import java.util.Collection;

import com.cloudant.client.api.Database;

import wasdev.sample.Recipe;

/**
 * Defines the API for a ToDo store.
 *
 */
public interface RecipeStore {

  	/**
	 * Get the target db object.
	 * 
	 * @return Database.
  	 * @throws Exception 
	 */
  public Database getDB();
  
  	/**
	 * Gets all Recipes from the store.
	 * 
	 * @return All Recipes.
  	 * @throws Exception 
	 */
  public Collection<Recipe> getAll();

  /**
   * Gets an individual ToDo from the store.
   * @param id The ID of the ToDo to get.
   * @return The ToDo.
   */
  public Recipe get(String id);

  /**
   * Persists a Recipe to the store.
   * @param td The ToDo to persist.
   * @return The persisted ToDo.  The ToDo will not have a unique ID..
   */
  public Recipe persist(Recipe vi);

  /**
   * Updates a ToDo in the store.
   * @param id The ID of the Recipe to update.
   * @param td The Recipe with updated information.
   * @return The updated Recipe.
   */
  public Recipe update(String id, Recipe vi);

  /**
   * Deletes a ToDo from the store.
   * @param id The ID of the Recipe to delete.
   */
  public void delete(String id);
  
  /**
   * Counts the number of Recipes
   * @return The total number of Recipes.
 * @throws Exception 
   */
  public int count() throws Exception;
}
