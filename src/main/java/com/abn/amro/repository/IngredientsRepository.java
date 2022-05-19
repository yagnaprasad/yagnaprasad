package com.abn.amro.repository;

import com.abn.amro.model.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientsRepository
        extends JpaRepository<IngredientsEntity, Long> {

}
