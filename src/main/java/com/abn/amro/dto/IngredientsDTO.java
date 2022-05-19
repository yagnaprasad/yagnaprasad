package com.abn.amro.dto;

import org.hibernate.validator.constraints.Length;

public class IngredientsDTO {

    private Long id;

    @Length(min = 3, message = "The field must be at least 3 characters")
    @Length(max = 50, message = "The field must be less than 50 characters")
    private String name;

    @Length(max = 10, message = "The field must be less than 10 characters")
    private String quantity;

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
