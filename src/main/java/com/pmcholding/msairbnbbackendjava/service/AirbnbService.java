package com.pmcholding.msairbnbbackendjava.service;

import com.pmcholding.msairbnbbackendjava.domain.Airbnb;
import com.pmcholding.msairbnbbackendjava.domain.Dashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirbnbService {

    Page<Airbnb> findAll(Pageable pageable);

    Dashboard getDashboard();
}
