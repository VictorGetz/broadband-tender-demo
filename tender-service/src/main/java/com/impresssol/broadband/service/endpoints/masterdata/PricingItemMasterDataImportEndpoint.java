package com.impresssol.broadband.service.endpoints.masterdata;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.pricing.PricingItem;
import com.impresssol.broadband.data.entities.pricing.PricingItemTypeEnum;
import com.impresssol.broadband.data.repo.pricing.PricingItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/masterdata/pricing")
@RequiredArgsConstructor
@Slf4j
public class PricingItemMasterDataImportEndpoint {

	private final PricingItemRepository pricingItemRepository;

	@GetMapping
	public HashMap<PricingItemTypeEnum, BigDecimal> getPricingItemPrices() {
		HashMap<PricingItemTypeEnum, BigDecimal> result = new HashMap<>();
		pricingItemRepository.findAll().forEach(sa -> result.putIfAbsent(sa.getPricingItemType(), sa.getPrice()));
		return result;
	}

	@PostMapping
	public ResponseEntity<Object> updatePrices(@RequestBody HashMap<PricingItemTypeEnum, BigDecimal> newPrices) {
		List<PricingItem> currentPrices = pricingItemRepository.findAll();
		currentPrices.forEach(price -> updatePrice(newPrices, price));
		pricingItemRepository.saveAll(currentPrices);
		return ResponseEntity.ok().build();
	}

	private void updatePrice(HashMap<PricingItemTypeEnum, BigDecimal> newPrices, PricingItem price) {
		price.setPrice(newPrices.get(price.getPricingItemType()));
	}
}
