package com.MotherSon.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MotherSon.CRM.models.Mealspackage;



@Repository
public interface MealspackageRepository extends JpaRepository<Mealspackage, Long>{

}