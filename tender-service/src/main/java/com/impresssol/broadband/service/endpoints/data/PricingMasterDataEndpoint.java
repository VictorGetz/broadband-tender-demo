package com.impresssol.broadband.service.endpoints.data;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impresssol.broadband.data.entities.pricing.PricingMasterData;
import com.impresssol.broadband.service.calculation.PriceCalculationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PricingMasterDataEndpoint {

	private final PriceCalculationService priceCalculationService;

	@PostMapping("pricingMasterData/calculateAndUpdateSum/{projectId}")
	public double calculateAndUpdateSum(@PathVariable long projectId) {
		PricingMasterData pricingMasterData = priceCalculationService.calculateAndUpdatePrice(projectId);
		return pricingMasterData.getSumValue().doubleValue();
	}

}
