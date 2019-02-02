package com.impresssol.broadband.seed;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Java6Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.impresssol.broadband.data.entities.pricing.PricingItemTypeEnum;

@RunWith(SpringRunner.class)
public class SeedDataFactoryTest {

	@InjectMocks
	private SeedDataFactory sut;

	@Value(value = "classpath:com/impresssol/broadband/seed/expected_project_result.json")
	private Resource expectedProjectResult;

	@Test
	public void validateProjectResult() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		String result = objectMapper.writeValueAsString(sut.createProject());

		assertThat(result).isEqualTo(readJsonInputFile(expectedProjectResult));
	}

	@Test
	public void validatePricesResult() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		String result = objectMapper.writeValueAsString(sut.createPrices());

		List<String> expectedPriceTypes = Arrays.stream(PricingItemTypeEnum.values())
				.map(Enum::name)
				.collect(Collectors.toList());
		assertThat(result).contains(expectedPriceTypes);
	}


	private String readJsonInputFile(Resource jsonFile) throws IOException {
		return FileUtils.readFileToString(jsonFile.getFile(), UTF_8);
	}
}