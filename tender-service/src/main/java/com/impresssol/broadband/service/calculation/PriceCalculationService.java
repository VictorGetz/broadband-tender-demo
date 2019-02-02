package com.impresssol.broadband.service.calculation;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.pricing.PricingItem;
import com.impresssol.broadband.data.entities.pricing.PricingMasterData;
import com.impresssol.broadband.data.repo.ProjectRepository;
import com.impresssol.broadband.data.repo.pricing.PricingMasterDataRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PriceCalculationService {

	private final ProjectRepository projectRepository;
	private final PricingMasterDataRepository pricingMasterDataRepository;

	public PricingMasterData calculateAndUpdatePrice(long projectId) {
		Project project = findProject(projectId);
		PricingMasterData pricingMasterData = project.getPricingMasterData();
		double sum = calculateSum(pricingMasterData);
		pricingMasterData.setSumValue(new BigDecimal(sum));
		pricingMasterDataRepository.save(pricingMasterData);
		return pricingMasterData;
	}

	private double calculateSum(PricingMasterData pricingMasterData) {
		return pricingMasterData.getPricingDetails().stream()
				.flatMap(pricingDetail -> pricingDetail.getPricingItems().stream())
				.mapToDouble(this::getPricingItemValue)
				.sum();
	}

	private double getPricingItemValue(PricingItem pricingItem) {
		return Optional.ofNullable(pricingItem)
				.map(PricingItem::getPrice)
				.map(BigDecimal::doubleValue)
				.orElse(0d);
	}

	private Project findProject(long projectId) {
		return projectRepository.findById(projectId)
				.orElseThrow(() -> new UnsupportedOperationException("Project could not be found inside the database projectID=" + projectId));
	}
}
