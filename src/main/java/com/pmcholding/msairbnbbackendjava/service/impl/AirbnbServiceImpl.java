package com.pmcholding.msairbnbbackendjava.service.impl;

import com.pmcholding.msairbnbbackendjava.domain.Airbnb;
import com.pmcholding.msairbnbbackendjava.domain.Dashboard;
import com.pmcholding.msairbnbbackendjava.domain.Neighbourhood;
import com.pmcholding.msairbnbbackendjava.repository.AirbnbRepository;
import com.pmcholding.msairbnbbackendjava.service.AirbnbService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AirbnbServiceImpl implements AirbnbService {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private final AirbnbRepository airbnbRepository;

    public Page<Airbnb> findAll(Pageable pageable) {
        return airbnbRepository.findAll(pageable);
    }

    public Dashboard getDashboard() {
        var totalProperties = getTotalProperties();
        var totalHosts = getTotalHosts();
        var averagePropertiesByHosts = getAveragePropertiesByHosts(totalProperties, totalHosts);
        var totalTypeProperty = getTotalTypeProperty();
        var averagePriceAllPropertiesInDollars = getAveragePriceAllPropertiesInDollars(totalProperties);
        var totalRatingsPerMonth = getTotalRatingsPerMonth();
        var neighbourhoods = getNeighbourhoods(totalProperties);

        return Dashboard.builder()
                .totalProperties(totalProperties)
                .totalHosts(totalHosts)
                .averagePropertiesByHosts(averagePropertiesByHosts)
                .totalTypeProperty(totalTypeProperty)
                .averagePriceAllPropertiesInDollars(averagePriceAllPropertiesInDollars)
                .totalRatingsPerMonth(totalRatingsPerMonth)
                .neighbourhoods(neighbourhoods)
                .build();
    }

    private Long getTotalProperties() {
        return airbnbRepository.count();
    }

    private Long getTotalHosts() {
        return airbnbRepository.countDistinctHostId();
    }

    private Double getAveragePropertiesByHosts(Long totalProperties, Long totalHosts) {
        var totalPropertiesBigDecimal = BigDecimal.valueOf(totalProperties);
        var totalHostsBigDecimal = BigDecimal.valueOf(totalHosts);
        return totalPropertiesBigDecimal.divide(totalHostsBigDecimal, 2, RoundingMode.HALF_UP).doubleValue();
    }

    private Long getTotalTypeProperty() {
        return airbnbRepository.countDistinctRoomType();
    }

    private Double getAveragePriceAllPropertiesInDollars(Long totalProperties) {
        var sumPriceAllProperties = BigDecimal.valueOf(airbnbRepository.sumPrice());
        var totalPropertiesBigDecimal = BigDecimal.valueOf(totalProperties);
        return sumPriceAllProperties.divide(totalPropertiesBigDecimal, 2, RoundingMode.HALF_UP).doubleValue();
    }

    private Long getTotalRatingsPerMonth() {
        return airbnbRepository.sumReviewsPerMonth();
    }

    private List<Neighbourhood> getNeighbourhoods(Long totalProperties) {
        var neighbourhoods = new ArrayList<Neighbourhood>();
        var totalPropertiesBigDecimal = BigDecimal.valueOf(totalProperties);
        var neighbourhoodNames = airbnbRepository.distinctNeighbourhood();
        for (var neighbourhoodName : neighbourhoodNames) {
            var percentageOfTotalProperties = getPercentageOfTotalProperties(neighbourhoodName, totalPropertiesBigDecimal);
            neighbourhoods.add(
                    Neighbourhood.builder()
                            .name(neighbourhoodName)
                            .percentageOfTotalProperties(percentageOfTotalProperties)
                            .build()
            );
        }
        return neighbourhoods;
    }

    private Double getPercentageOfTotalProperties(String neighbourhoodName, BigDecimal totalProperties) {
        var totalPropertiesByNeighbourhood = BigDecimal.valueOf(airbnbRepository.countIdByNeighbourhood(neighbourhoodName));
        return totalPropertiesByNeighbourhood
                .divide(totalProperties, 4, RoundingMode.HALF_UP)
                .multiply(ONE_HUNDRED)
                .doubleValue();
    }
}
