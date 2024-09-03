package com.intcomex.test.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intcomex.test.model.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
