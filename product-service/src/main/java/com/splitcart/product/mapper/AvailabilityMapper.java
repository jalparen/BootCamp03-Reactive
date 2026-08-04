package com.splitcart.product.mapper;

import com.splitcart.product.api.model.Availability;

public class AvailabilityMapper {

  private AvailabilityMapper() {}

  public static Availability toApi(com.splitcart.product.model.Availability availability) {
    if (availability == null) {
      return null;
    }
    return new Availability().sku(availability.getSku()).available(availability.getAvailable());
  }

  public static com.splitcart.product.model.Availability toModel(Availability availability) {
    if (availability == null) {
      return null;
    }
    return com.splitcart.product.model.Availability.builder()
        .sku(availability.getSku())
        .available(availability.getAvailable())
        .build();
  }
}
