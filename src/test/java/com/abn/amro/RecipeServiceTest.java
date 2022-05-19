package com.abn.amro;

import com.abn.amro.dto.RecipeDTO;
import com.abn.amro.model.IngredientsEntity;
import com.abn.amro.model.RecipeEntity;
import com.abn.amro.repository.IngredientsRepository;
import com.abn.amro.repository.RecipeRepository;
import com.abn.amro.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeServiceTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RecipeService recipeService;

    @MockBean
    RecipeRepository repository;

    @MockBean
    IngredientsRepository inRepository;

    RecipeEntity entity;

    IngredientsEntity inEntity;
    RecipeDTO recipeDTO;

    @BeforeEach
    public void setUp() throws IOException {
        recipeDTO = getRecipeDTO();
        entity = new RecipeEntity();
        entity.setId(recipeDTO.getId());
        entity.setName(recipeDTO.getName());
        inEntity = new IngredientsEntity();
        inEntity.setName("Oil");
        inEntity.setId(recipeDTO.getId());
        Set<IngredientsEntity> ingridientEntities = new HashSet<>();
        ingridientEntities.add(inEntity);
        entity.setIngridientEntities(ingridientEntities);
    }


    RecipeDTO getRecipeDTO() throws IOException {
        return objectMapper.readValue(ResourceUtils.getFile("./src/test/resources/request.json"), RecipeDTO.class);
    }

    @Test
    void addRecipeTest() throws Exception {
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);
        Mockito.when(inRepository.save(Mockito.any())).thenReturn(inEntity);
        RecipeDTO responseDTO = recipeService.createRecipe(recipeDTO);
        assertEquals("Mutton", responseDTO.getName());
    }

    @Test
    void updateRecipeTest() throws Exception {

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);
        Mockito.when(inRepository.save(Mockito.any())).thenReturn(inEntity);
        Mockito.when(inRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(inEntity));
        RecipeDTO responseDTO = recipeService.updateRecipe(recipeDTO);
        assertEquals("Mutton", responseDTO.getName());
    }

    @Test
    void getRecipeByIDTest() throws Exception {

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
        RecipeDTO responseDTO = recipeService.getRecipeById(1l);
        assertEquals("Mutton", responseDTO.getName());
    }

    @Test
    void getRecipeAllTest() throws Exception {
        List<RecipeEntity> listRecipe = new ArrayList<>();
        listRecipe.add(entity);
        Mockito.when(repository.findAll()).thenReturn(listRecipe);
        List<RecipeDTO> listRecipeDTO = recipeService.getAllRecipes();
        assertTrue(listRecipeDTO.size() > 0);
    }

    @Test
    void getDeleteTest() throws Exception {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
        repository.deleteById(1l);
        recipeService.deleteRecipeById(1l);
        assertTrue(true);
    }

}
