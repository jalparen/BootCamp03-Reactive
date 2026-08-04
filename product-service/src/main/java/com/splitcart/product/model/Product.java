
package com.splitcart.product.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*
Agregar las anotaciones necesarias para que esta clase sea un documento de MongoDB, uso de lombok para getters, setters y constructor sin argumentos
 */
@Document("products")
@Getter
@Setter
@Builder
public class Product {

  @Id private String id;
  private String name;
  private String category;
  private Double price;
}
