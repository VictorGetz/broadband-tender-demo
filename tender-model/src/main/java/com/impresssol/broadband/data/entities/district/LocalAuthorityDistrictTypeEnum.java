package com.impresssol.broadband.data.entities.district;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum LocalAuthorityDistrictTypeEnum {
	COMMUNITY("Gemeinde"),
	CITY("Stadt");

	//For Language Translation i would normally use a language pack not this
	private final String germanLabel;
}
