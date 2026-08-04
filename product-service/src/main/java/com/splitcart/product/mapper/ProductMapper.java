package com.splitcart.product.mapper;

import com.splitcart.product.api.model.Product;
import java.math.BigDecimal;

public class ProductMapper {

  private ProductMapper() {}

  public static Product toApi(com.splitcart.product.model.Product product) {
    if (product == null) {
      return null;
    }
    return new Product()
        .id(product.getId())
        .name(product.getName())
        .category(product.getCategory())
        .price(product.getPrice() == null ? null : BigDecimal.valueOf(product.getPrice()));
  }

  public static com.splitcart.product.model.Product toModel(Product product) {
    if (product == null) {
      return null;
    }
    return com.splitcart.product.model.Product.builder()
        .id(product.getId())
        .name(product.getName())
        .category(product.getCategory())
        .price(product.getPrice() == null ? null : product.getPrice().doubleValue())
        .build();
  }
}
