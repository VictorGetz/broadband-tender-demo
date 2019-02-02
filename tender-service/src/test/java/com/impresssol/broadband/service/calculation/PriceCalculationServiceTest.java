package com.impresssol.broadband.service.calculation;

import static com.impresssol.broadband.data.entities.pricing.PricingDetailTypeEnum.FTTB_FTTH;
import static com.impresssol.broadband.data.entities.pricing.PricingDetailTypeEnum.FTTC;
import static com.impresssol.broadband.data.entities.pricing.PricingItemTypeEnum.ACCESS_EMPTY_PIPES;
import static com.impresssol.broadband.data.entities.pricing.PricingItemTypeEnum.BACKHAULNET_WORKACCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.impresssol.broadband.data.entities.Project;
import com.impresssol.broadband.data.entities.ProjectMasterData;
import com.impresssol.broadband.data.entities.pricing.PricingDetail;
import com.impresssol.broadband.data.entities.pricing.PricingDetailTypeEnum;
import com.impresssol.broadband.data.entities.pricing.PricingItem;
import com.impresssol.broadband.data.entities.pricing.PricingMasterData;
import com.impresssol.broadband.data.repo.ProjectRepository;
import com.impresssol.broadband.data.repo.pricing.PricingMasterDataRepository;

@RunWith(MockitoJUnitRunner.class)
public class PriceCalculationServiceTest {

	private static final long PROJECT_ID = 15L;

	@Mock
	private ProjectRepository projectRepository;
	@Mock
	private PricingMasterDataRepository pricingMasterDataRepository;
	@InjectMocks
	private PriceCalculationService sut;

	@Test
	public void createSum_valid_values() {
		Project project = createProject();
		setFirstPrice(project, new BigDecimal(6000d));
		setSecondPrice(project, new BigDecimal(5000d));
		when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

		PricingMasterData result = sut.calculateAndUpdatePrice(PROJECT_ID);

		double expectedResult = 11000d;
		assertThat(result.getSumValue()).isEqualTo(new BigDecimal(expectedResult));
	}


	@Test
	public void createSum_null_values() {
		Project project = createProject();
		setFirstPrice(project, null);
		setSecondPrice(project, null);
		when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

		PricingMasterData result = sut.calculateAndUpdatePrice(PROJECT_ID);

		double expectedResult = 0d;
		assertThat(result.getSumValue()).isEqualTo(new BigDecimal(expectedResult));
	}


	@Test
	public void createSum_negative_values() {
		Project project = createProject();
		setFirstPrice(project, new BigDecimal(-6000d));
		setSecondPrice(project, new BigDecimal(-5000d));
		when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

		PricingMasterData result = sut.calculateAndUpdatePrice(PROJECT_ID);

		double expectedResult = -11000d;
		assertThat(result.getSumValue()).isEqualTo(new BigDecimal(expectedResult));
	}

	private void setFirstPrice(Project project, BigDecimal price) {
		project.getPricingMasterData().getPricingDetails().get(0).getPricingItems().get(0).setPrice(price);
	}

	private void setSecondPrice(Project project, BigDecimal price) {
		project.getPricingMasterData().getPricingDetails().get(0).getPricingItems().get(1).setPrice(price);
	}


	private Project createProject() {
		return Project.builder()
				.id(PROJECT_ID)
				.pricingMasterData(createPricingMasterData())
				.projectMasterData(createProjectMasterData())
				.build();
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
		PricingDetail pricingDetail = PricingDetail.builder()
				.pricingDetailType(pricingDetailType)
				.build();

		PricingItem accessEmptyPipes = PricingItem.builder()
				.pricingItemType(ACCESS_EMPTY_PIPES)
				.build();

		PricingItem backHoulNetWorkAccess = PricingItem.builder()
				.pricingItemType(BACKHAULNET_WORKACCESS)
				.build();
		pricingDetail.getPricingItems().add(backHoulNetWorkAccess);
		pricingDetail.getPricingItems().add(accessEmptyPipes);
		return pricingDetail;
	}
}