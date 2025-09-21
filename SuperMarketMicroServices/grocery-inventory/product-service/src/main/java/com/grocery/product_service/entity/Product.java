package com.grocery.product_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Long id;

@NotBlank(message = "Name is mandatory")
@Column(nullable = false, unique = true)
private String name;

private String description;

@NotNull(message = "Price is mandatory")
@DecimalMin(value = "0.0" , inclusive = false, message = "Price must be greater than 0")
@Column(nullable = false)
private Double price;
@NotNull(message = "Category ID is mandatory")
@Column(name = "category_id", nullable = false)
private Long categoryId;

}
