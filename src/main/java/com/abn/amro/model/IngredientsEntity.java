package com.abn.amro.model;

import javax.persistence.*;

/* Ingredient Entity class */

@Entity
@Table(name = "INGRIDIENT")
public class IngredientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")

    private String quantity;

    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    RecipeEntity recipe;

    public IngredientsEntity() {
    }

    public IngredientsEntity(String name, String quantity, RecipeEntity recipe) {
        this.name = name;
        this.quantity = quantity;
        this.recipe = recipe;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}