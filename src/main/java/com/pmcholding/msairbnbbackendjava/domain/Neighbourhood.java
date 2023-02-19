package com.pmcholding.msairbnbbackendjava.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Neighbourhood {

    private String name;
    private Double percentageOfTotalProperties;
    private Long totalPropertiesContainingThisNeighbourhood;
    private Long totalHostsContainingThisNeighbourhood;
    private Double averagePropertiesByNeighbourhoodHosts;
    private Double averagePricePropertiesInThisNeighbourhood;
}
