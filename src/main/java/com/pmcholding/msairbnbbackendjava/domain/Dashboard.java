package com.pmcholding.msairbnbbackendjava.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Dashboard {

    private Long totalProperties;
    private Long totalHosts;
    private Double averagePropertiesByHosts;
    private Long totalTypeProperty;
    private Double averagePriceAllPropertiesInDollars;
    private Long totalRatingsPerMonth;
    private List<Neighbourhood> neighbourhoods;
}
