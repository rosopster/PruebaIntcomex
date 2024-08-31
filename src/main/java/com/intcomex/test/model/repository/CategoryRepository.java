package com.intcomex.test.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intcomex.test.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}


