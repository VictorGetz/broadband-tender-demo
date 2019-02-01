package com.impresssol.broadband.data.repo.pricing;

import org.springframework.data.repository.CrudRepository;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.ProjectMasterData;
import com.impresssol.broadband.data.entities.pricing.PricingDetail;

public interface PricingDetailRepository extends CrudRepository<PricingDetail, Long> {
}
