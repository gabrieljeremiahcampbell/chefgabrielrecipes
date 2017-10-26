/*******************************************************************************
 * Copyright (c) 2017 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package wasdev.sample.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

import wasdev.sample.Recipe;
import wasdev.sample.store.RecipeStore;
import wasdev.sample.store.RecipeStoreFactory;

@ApplicationPath("api")
@Path("/recipes")
public class RecipeAPI extends Application {

	//Our database store
	RecipeStore store = RecipeStoreFactory.getInstance();

  /**
   * Gets all Recipes.
   * REST API example:
   * <code>
   * GET http://localhost:8080/ChefGabrielReceipes/api/recipes
   * </code>
   *
   * Response:
   * <code>
   * [ "ice cream", "jam" ]
   * </code>
   * @return A collection of all the Recipes
   */
    @GET
    // @Path("/") // This anotation works in Liberty but not with Tomcat/Jersey.
    @Produces({"application/json"})
    public String getRecipes() {

		if (store == null) {
			return "[]";
		}

		List<String> names = new ArrayList<String>();
		for (Recipe doc : store.getAll()) {
			String name = doc.getName();
			if (name != null){
				names.add(name);
			}
		}
		return new Gson().toJson(names);
    }
}
