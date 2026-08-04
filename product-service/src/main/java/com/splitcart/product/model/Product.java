
package com.splitcart.product.model;


import org.springframework.data.annotation.Id;


/*
Agregar las anotaciones necesarias para que esta clase sea un documento de MongoDB, uso de lombok para getters, setters y constructor sin argumentos
 */
public class Product {

  @Id private String id;
  private String name;
  private String category;
  private Double price;
}
