
package com.splitcart.product.service;

import com.splitcart.product.api.model.Availability;
import com.splitcart.product.api.model.Product;
import com.splitcart.product.mapper.AvailabilityMapper;
import com.splitcart.product.mapper.ProductMapper;
import com.splitcart.product.repo.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    implementar los metodos list, getById y availability usando el repositorio ProductRepository
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> list(String category, BigDecimal maxPrice, int page, int size) {
        Flux<com.splitcart.product.model.Product> products = productRepository.findAll();

        if (category != null && !category.isBlank()) {
            products = products.filter(p -> category.equalsIgnoreCase(p.getCategory()));
        }
        if (maxPrice != null) {
            products =
                    products.filter(
                            p ->
                                    p.getPrice() != null
                                            && BigDecimal.valueOf(p.getPrice()).compareTo(maxPrice)
                                                    <= 0);
        }

        return products
                .skip((long) page * size)
                .take(size)
                .map(ProductMapper::toApi);
    }

    @Override
    public Mono<Product> getById(String id) {
        return null;
    }

    @Override
    public Flux<Availability> availability(List<String> skus) {
        return Flux.fromIterable(skus)
                .flatMap(sku -> productRepository.existsById(sku)
                        .map(exists -> com.splitcart.product.model.Availability.builder()
                                .sku(sku)
                                .available(exists ? 10 : 0)
                                .build())
                        .map(AvailabilityMapper::toApi)
                );
    }


}
