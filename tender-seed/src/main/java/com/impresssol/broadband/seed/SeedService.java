package com.impresssol.broadband.seed;

import static com.impresssol.broadband.data.entities.pricing.PricingDetailTypeEnum.FTTB_FTTH;
import static com.impresssol.broadband.data.entities.pricing.PricingDetailTypeEnum.FTTC;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.ProjectMasterData;
import com.impresssol.broadband.data.entities.district.ContactPerson;
import com.impresssol.broadband.data.entities.district.LocalAuthorityDistrict;
import com.impresssol.broadband.data.entities.district.LocalAuthorityDistrictTypeEnum;
import com.impresssol.broadband.data.entities.pricing.PricingDetail;
import com.impresssol.broadband.data.entities.pricing.PricingDetailTypeEnum;
import com.impresssol.broadband.data.entities.pricing.PricingMasterData;
import com.impresssol.broadband.data.repo.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/seed")
@Slf4j
public class SeedService {

	private static final double DUMMY_PRICE = 1000.50d;

	@Autowired
	private ProjectRepository projectRepository;

	@PostMapping
	public Project seedDummyData() {
		Project project = Project.builder()
				.localAuthorityDistrict(createLocalAuthorityDistrict())
				.pricingMasterData(createPricingMasterData())
				.projectMasterData(createProjectMasterData())
				.build();

		log.info("Creating Seed Project data=" + project.toString());
		projectRepository.save(project);
		return project;
	}

	private ProjectMasterData createProjectMasterData() {
		return ProjectMasterData.builder()
				.developmentAreaName("Gewerbegebiet Pessenbach")
				.networkOperator("Telekom Deutschland GmbH")
				.build();
	}

	private PricingMasterData createPricingMasterData() {
		PricingMasterData pricingMasterData = new PricingMasterData();
		pricingMasterData.getPricingDetails().addAll(Arrays.asList(createPricingDetail(FTTC), createPricingDetail(FTTB_FTTH)));
		return pricingMasterData;
	}

	private PricingDetail createPricingDetail(PricingDetailTypeEnum pricingDetailType) {
		return PricingDetail.builder()
				.price(new BigDecimal(DUMMY_PRICE))
				.pricingDetailType(pricingDetailType)
				.build();
	}

	private LocalAuthorityDistrict createLocalAuthorityDistrict() {
		return LocalAuthorityDistrict.builder()
				.contactPerson(createContactPerson())
				.name("Kochel a.See")
				.districtKey("09173133")
				.governmentDistrict("Oberbayern")
				.type(LocalAuthorityDistrictTypeEnum.CITY)
				.district("Bad Tölz-Wolfratshausen")
				.build();
	}

	private ContactPerson createContactPerson() {
		return ContactPerson.builder()
				.name("Frau Lutterer")
				.build();
	}

}
