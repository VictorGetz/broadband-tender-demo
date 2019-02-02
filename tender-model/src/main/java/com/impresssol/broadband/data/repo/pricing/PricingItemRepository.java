package com.impresssol.broadband.data.repo.pricing;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.pricing.PricingItem;
import com.impresssol.broadband.data.entities.pricing.PricingMasterData;

public interface PricingItemRepository extends CrudRepository<PricingItem, Long> {
	@Override
	List<PricingItem> findAll();
}
