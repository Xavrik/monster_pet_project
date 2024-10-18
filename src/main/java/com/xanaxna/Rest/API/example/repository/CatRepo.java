package com.xanaxna.Rest.API.example.repository;

import com.xanaxna.Rest.API.example.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {



}
