package com.abn.amro.service;

import com.abn.amro.dto.RecipeDTO;
import com.abn.amro.exception.RecordNotCreatedException;
import com.abn.amro.repository.RecipeRepository;
import com.abn.amro.dto.IngredientsDTO;
import com.abn.amro.exception.RecordNotFoundException;
import com.abn.amro.model.IngredientsEntity;
import com.abn.amro.model.RecipeEntity;
import com.abn.amro.repository.IngredientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);
    @Autowired
    RecipeRepository repository;

    @Autowired
    IngredientsRepository inRepository;

    public List<RecipeDTO> getAllRecipes() throws RecordNotFoundException {
        logger.info("<<Inside getAllRecipes method>>");
        List<RecipeEntity> recipeList = repository.findAll();
        //logger.info("recipes list: Empty or not"+recipeList.isEmpty());
        if (!recipeList.isEmpty()) {
            logger.info("recipes List Size (Count) returned from Database:"+recipeList.size());
            return getAllRecipeDto(recipeList);
        } else {
            throw new RecordNotFoundException("No Recipe record exist in the database:"+new ArrayList<RecipeDTO>());
                    }
    }


    /* Get the single Record of the entity
       Input parameter Long Id
       Return  RecipeDTO Object
     */
    @Transactional
    public RecipeDTO getRecipeById(Long id) throws RecordNotFoundException {
        Optional<RecipeEntity> recipe = repository.findById(id);

        if (recipe.isPresent()) {
            return getRecipeDto(recipe.get());
        } else {
            throw new RecordNotFoundException("No Recipe record exist for given id");
        }
    }

    /* Updates Recipe entity object
       with data from RecipeDTO Object.
       and returns an Object with details like s
        name, price, Type and Ingredients details.
     */

    @Transactional
    public RecipeDTO updateRecipe(RecipeDTO recipeDto) throws RecordNotFoundException {
        Optional<RecipeEntity> recipe = repository.findById(recipeDto.getId());

        if (recipe.isPresent()) {
            RecipeEntity newEntity = recipe.get();
            newEntity.setType(recipeDto.getType());
            newEntity.setName(recipeDto.getName());
            newEntity.setPrice(recipeDto.getPrice());
            newEntity.setCreatedAt(recipeDto.getCreatedDate());
            RecipeEntity updateEntity =  repository.save(newEntity);
            Set<IngredientsEntity> set = new HashSet<>();
            for (IngredientsDTO ingredientsDto : recipeDto.getIngridientEntities()) {
                IngredientsEntity updateEntry = inRepository.findById(ingredientsDto.getId()).get();
                updateEntry.setName(ingredientsDto.getName());
                updateEntry.setQuantity(ingredientsDto.getQuantity());
                updateEntry.setRecipe(newEntity);
                set.add(inRepository.save(updateEntry));
            }
            return wrapRecipeDto(updateEntity, set);

        } else {
            throw new RecordNotFoundException("No Recipe record exist for given id");
        }
    }

    /* Create Recipe Record of the entity
       Input parameter RecipeDTO
       Return  RecipeDTO Object
     */

    @Transactional
    public RecipeDTO createRecipe(RecipeDTO recipeDto) throws RecordNotCreatedException {
        RecipeEntity entity = repository.save(new RecipeEntity(recipeDto.getName(), recipeDto.getPrice(), recipeDto.getType()));
        if(Objects.isNull(entity))
            throw new RecordNotCreatedException("Bad Request:"+ HttpStatus.BAD_REQUEST);
        Set<IngredientsEntity> set = new HashSet<>();
        for (IngredientsDTO ingredientsDto : recipeDto.getIngridientEntities()) {
            set.add(inRepository.save(new IngredientsEntity(ingredientsDto.getName(), ingredientsDto.getQuantity(), entity)));
        }
        return wrapRecipeDto(entity, set);
    }

    /**
     * Captures the data from RecipeEntity
     * and stores entity  in RecipeDTO object
     * */
    private RecipeDTO getRecipeDto(RecipeEntity entity) {
        RecipeDTO recipeDto = new RecipeDTO();
        Set<IngredientsDTO> setList = new HashSet<>();
        recipeDto.setId(entity.getId());
        recipeDto.setName(entity.getName());
        recipeDto.setPrice(entity.getPrice());
        recipeDto.setType(entity.getType());
        recipeDto.setCreatedDate(entity.getCreatedAt());
        for (IngredientsEntity ingredientsEntity : entity.getIngridientEntities()) {
            IngredientsDTO ingredientsDto = new IngredientsDTO();
            ingredientsDto.setId(ingredientsEntity.getId());
            ingredientsDto.setName(ingredientsEntity.getName());
            ingredientsDto.setQuantity(ingredientsEntity.getQuantity());
            setList.add(ingredientsDto);
        }
        recipeDto.setIngridientEntities(setList);
        return recipeDto;
    }

    /** Retires all the recipes in the database.
     * It contains details like Type of Recipe and its ingredients.
     *  time the recipe created.
     * */
    private List<RecipeDTO> getAllRecipeDto(List<RecipeEntity> entities) {
        List<RecipeDTO> recipeList = new ArrayList<>();
        for(RecipeEntity entity:entities) {
            RecipeDTO recipeDto = new RecipeDTO();
            Set<IngredientsDTO> setList = new HashSet<>();
            recipeDto.setId(entity.getId());
            recipeDto.setName(entity.getName());
            recipeDto.setPrice(entity.getPrice());
            recipeDto.setType(entity.getType());
            recipeDto.setCreatedDate(entity.getCreatedAt());
            for (IngredientsEntity ingredientsEntity : entity.getIngridientEntities()) {
                IngredientsDTO ingredientsDto = new IngredientsDTO();
                ingredientsDto.setId(ingredientsEntity.getId());
                ingredientsDto.setName(ingredientsEntity.getName());
                ingredientsDto.setQuantity(ingredientsEntity.getQuantity());
                setList.add(ingredientsDto);
            }
            recipeDto.setIngridientEntities(setList);
            recipeList.add(recipeDto);
        }

        return recipeList;
    }

    /** prepares Recipe object with recipe details like Name, price, Type and when it got last updated
     * and the ingredient details like name and its quantity for Recipe preparation
     * */

    private RecipeDTO wrapRecipeDto(RecipeEntity entity, Set<IngredientsEntity> set) {
        RecipeDTO recipeDto = new RecipeDTO();
        Set<IngredientsDTO> setList = new HashSet<>();
        recipeDto.setId(entity.getId());
        recipeDto.setName(entity.getName());
        recipeDto.setPrice(entity.getPrice());
        recipeDto.setType(entity.getType());
        recipeDto.setCreatedDate(entity.getCreatedAt());
        for (IngredientsEntity ingredientsEntity : set) {
            IngredientsDTO ingredientsDto = new IngredientsDTO();
            ingredientsDto.setId(ingredientsEntity.getId());
            ingredientsDto.setName(ingredientsEntity.getName());
            ingredientsDto.setQuantity(ingredientsEntity.getQuantity());
            setList.add(ingredientsDto);
        }
        recipeDto.setIngridientEntities(setList);
        return recipeDto;
    }

    /* Delete Recipe Record of the entity
       Input parameter id
     */
    public void deleteRecipeById(Long id) throws RecordNotFoundException {
        Optional<RecipeEntity> recipe = repository.findById(id);

        if (recipe.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Recipe record exist for given id");
        }
    }
}