
package com.splitcart.product.api;

import com.splitcart.product.api.model.Availability;
import com.splitcart.product.api.model.Product;
import com.splitcart.product.service.ProductService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
  implementar los metodos listProducts, getProductById y getAvailability usando el servicio ProductService
 */
@Component
@RequiredArgsConstructor
public class ProductsApiDelegateImpl implements ProductsApiDelegate {

  private final ProductService service;

  @Override
  public Mono<ResponseEntity<Flux<Product>>> listProducts(
      String category, BigDecimal maxPrice, Integer page, Integer size, ServerWebExchange exchange) {
    int pageNumber = page == null ? 0 : page;
    int pageSize = size == null ? 10 : size;
    return Mono.just(
        ResponseEntity.status(HttpStatus.OK)
            .body(service.list(category, maxPrice, pageNumber, pageSize)));
  }

  @Override
  public Mono<ResponseEntity<Product>> getProductById(String id, ServerWebExchange exchange) {
    return service.getById(id).map(product -> ResponseEntity.status(HttpStatus.OK).body(product));
  }

  @Override
  public Mono<ResponseEntity<Flux<Availability>>> getAvailability(
      String ids, ServerWebExchange exchange) {
    return null;
  }
}
