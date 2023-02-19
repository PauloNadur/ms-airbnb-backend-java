package com.pmcholding.msairbnbbackendjava.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "airbnb_nyc_2019")
public class Airbnb {

    @Id
    private Long id;

    private String name;

    private Long hostId;

    private String hostName;

    private String neighbourhoodGroup;

    private String neighbourhood;

    private String latitude;

    private String longitude;

    private String roomType;

    private BigDecimal price;

    private Integer minimumNights;

    private Integer numberOfReviews;

    private String lastReview;

    private String reviewsPerMonth;

    private Integer calculatedHostListingsCount;

    @Column(name = "availability_365")
    private Integer availability365;
}
