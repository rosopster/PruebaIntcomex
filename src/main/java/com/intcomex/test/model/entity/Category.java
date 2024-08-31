package com.intcomex.test.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Data // Lombok annotation to generate getters, setters, toString, etc.
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private Long categoryId;
    
    @Column(nullable = false, name = "categoryname")
    private String categoryName;
    
    private String description;
    
    @Lob
    private String picture;
}

