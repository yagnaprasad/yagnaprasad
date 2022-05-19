package com.abn.amro.dto;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class RecipeDTO {
    private Long id;


    @Length(min = 3, message = "The field must be at least 3 characters")
    @Length(max = 50, message = "The field must be less than 50 characters")
    private String name;
    private Float price;
    private String type;
    private String createdDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = dateTime.format(formatter);
        this.createdDate = createdDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Set<IngredientsDTO> ingridientEntities;

    public Long getId() {
        return id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    public Set<IngredientsDTO> getIngridientEntities() {
        return ingridientEntities;
    }

    public void setIngridientEntities(Set<IngredientsDTO> ingridientEntities) {
        this.ingridientEntities = ingridientEntities;
    }
}
