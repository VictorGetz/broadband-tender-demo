package com.impresssol.broadband.seed;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.pricing.PricingItem;
import com.impresssol.broadband.data.entities.pricing.PricingItemTypeEnum;
import com.impresssol.broadband.data.repo.ProjectRepository;
import com.impresssol.broadband.data.repo.pricing.PricingItemRepository;
import com.impresssol.broadband.service.endpoints.masterdata.PricingItemMasterDataImportEndpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/seed")
@Slf4j
@RequiredArgsConstructor
public class SeedServiceEndpoint {

	private final ProjectRepository projectRepository;
	private final PricingItemMasterDataImportEndpoint pricingItemMasterDataImportEndpoint;
	private final SeedDataFactory seedDataFactory;
	private final PricingItemRepository pricingItemRepository;

	@GetMapping("projects")
	public Project seedDummyData() {
		Project project = seedDataFactory.createProject();
		log.info("Creating Seed Project data=" + project.toString());
		projectRepository.save(project);
		return project;
	}

	@GetMapping("prices")
	public ResponseEntity<Object> seedPrices() {
		Map<PricingItemTypeEnum, BigDecimal> prices = seedDataFactory.createPrices();
		log.info("Create Dummy Price Items");
		prices.entrySet().forEach(priceImportData -> pricingItemRepository.save(createPricingItem(priceImportData)));
		log.info("Creating Seed Prices data=" + prices.toString());
		pricingItemMasterDataImportEndpoint.updatePrices(prices);
		return ResponseEntity.accepted().body(prices.toString());
	}

	private PricingItem createPricingItem(Map.Entry<PricingItemTypeEnum, BigDecimal> priceImportData) {
		return PricingItem.builder()
				.pricingItemType(priceImportData.getKey())
				.price(priceImportData.getValue())
				.build();
	}

}
