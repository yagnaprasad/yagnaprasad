package com.abn.amro.repository;

import com.abn.amro.model.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepository
        extends JpaRepository<RecipeEntity, Long> {

}
