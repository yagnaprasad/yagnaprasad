package com.abn.amro;

import com.abn.amro.dto.RecipeDTO;
import com.abn.amro.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RecipeService recipeService;

    @Autowired
    private ObjectMapper objectMapper;

    String getRequestJson() throws IOException {
        return Files.readString(Path.of("./src/test/resources/request.json"));
    }

    RecipeDTO getRecipeDTO() throws IOException {
        return objectMapper.readValue(ResourceUtils.getFile("./src/test/resources/request.json"), RecipeDTO.class);
    }

    @Test
    void addRecipeTest() throws Exception {
        //Given
        RecipeDTO recipeDTO = getRecipeDTO();
        //When
        Mockito.when(recipeService.createRecipe(recipeDTO)).thenReturn(getRecipeDTO());
        ResultActions resultActions = mockMvc.perform(
                post("/recipe/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson())
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    void updateRecipeTest() throws Exception {
        //Given
        RecipeDTO recipeDTO = getRecipeDTO();
        //When
        Mockito.when(recipeService.updateRecipe(recipeDTO)).thenReturn(getRecipeDTO());
        ResultActions resultActions = mockMvc.perform(
                put("/recipe/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson())
        );
        //Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void selectRecipeTest() throws Exception {
        //Given
        Long recipeId = 1L;
        //When
        Mockito.when(recipeService.getRecipeById(recipeId)).thenReturn(getRecipeDTO());
        ResultActions resultActions = mockMvc.perform(get("/recipe/getID/{id}",1l));

        //Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void selectListOfRecipesTest() throws Exception {
        //When
        Mockito.when(recipeService.getAllRecipes()).thenReturn(Stream.of(getRecipeDTO()).collect(Collectors.toList()));
        ResultActions resultActions = mockMvc.perform(get("/recipe/getAll"));
        //Then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void deleteRecipeTest() throws Exception {
        //Given
        Long recipeId = 1L;
        //When
        ResultActions resultActions = mockMvc.perform(delete("/recipe/delete/{id}", 1l)
        );
        //Then
        resultActions.andExpect(status().isOk());
    }

}
