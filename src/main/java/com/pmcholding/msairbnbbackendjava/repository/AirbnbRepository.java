package com.pmcholding.msairbnbbackendjava.repository;

import com.pmcholding.msairbnbbackendjava.domain.Airbnb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirbnbRepository extends JpaRepository<Airbnb, Long> {

    @Query(value = "SELECT COUNT(DISTINCT host_id) FROM pmcpowerbitest01.airbnb_nyc_2019", nativeQuery = true)
    Long countDistinctHostId();

    @Query(value = "SELECT COUNT(DISTINCT room_type) FROM pmcpowerbitest01.airbnb_nyc_2019", nativeQuery = true)
    Long countDistinctRoomType();

    @Query(value = "SELECT SUM(price) FROM pmcpowerbitest01.airbnb_nyc_2019", nativeQuery = true)
    Long sumPrice();

    @Query(value = "SELECT SUM(reviews_per_month) FROM pmcpowerbitest01.airbnb_nyc_2019", nativeQuery = true)
    Long sumReviewsPerMonth();

    @Query(value = "SELECT DISTINCT neighbourhood FROM pmcpowerbitest01.airbnb_nyc_2019", nativeQuery = true)
    List<String> distinctNeighbourhood();

    Long countIdByNeighbourhood(String neighbourhoodName);
}
