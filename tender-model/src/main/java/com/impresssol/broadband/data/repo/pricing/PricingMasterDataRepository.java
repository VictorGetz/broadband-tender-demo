package com.impresssol.broadband.data.repo.pricing;

import org.springframework.data.repository.CrudRepository;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.ProjectMasterData;
import com.impresssol.broadband.data.entities.pricing.PricingMasterData;

public interface PricingMasterDataRepository extends CrudRepository<PricingMasterData, Long> {
}
