package com.abn.amro.web;

import com.abn.amro.dto.RecipeDTO;
import com.abn.amro.exception.RecordNotCreatedException;
import com.abn.amro.exception.RecordNotFoundException;
import com.abn.amro.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  RecipeController class
 *  This class acts as controller and routes the
 *  requests to specific API calls.
 */

@RestController
@RequestMapping("/recipe")
public class RecipeController
{
    @Autowired
    RecipeService service;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    @GetMapping("/getAll")   /** Retrieves list of all the Recipes available in the Database */
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() throws RecordNotFoundException {
        List<RecipeDTO> list = service.getAllRecipes();
        logger.info("in controller class");
        return new ResponseEntity<List<RecipeDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * this method is useful to get the recipe details by
     *  taking Id as input. Hence it returns the data matching to specific Id provided by the user
     *
     * @param id ( takes Id as input for record retrieval from H2 Database.)
     * @return Recipe details from h2 in-memory database
     * @throws RecordNotFoundException
     */

    @GetMapping("/getID/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        RecipeDTO entity = service.getRecipeById(id);
 
        return new ResponseEntity<RecipeDTO>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * This method takes user inputs in the form of json object
     * and creates recipe in the H2 in-memory database.
     *
     * @param recipe takes Request DTO as input for recipe creation.
     * @return This method returns an entity after successful creation of recipe.
     * @throws RecordNotCreatedException
     */

    @PostMapping("/create")
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipe)
                                                    throws RecordNotCreatedException {
        RecipeDTO created = service.createRecipe(recipe);
        return new ResponseEntity<RecipeDTO>(created, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     *  takes updated recipe details as input and saves data in H2 in-memory Database.
     * @param recipe
     * @return returns updated recipe details.
     * @throws RecordNotFoundException
     */

    @PutMapping("/update")
    public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipe)
            throws RecordNotFoundException {
        RecipeDTO updated = service.updateRecipe(recipe);
        return new ResponseEntity<RecipeDTO>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * this method takes Recipe Id as input and deletes the data
     * from H2 Database.
     *
     *  @param id : takes Id as input for record deletion
     * @return return the success messages after record deletion.
     * @throws RecordNotFoundException
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRecipeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteRecipeById(id);
        return new ResponseEntity<String>("Record deleted", new HttpHeaders(), HttpStatus.OK);
    }
 
}