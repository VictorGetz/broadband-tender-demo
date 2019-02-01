package com.impresssol.broadband.data.entities.pricing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@Getter
@ToString
public enum PricingDetailTypeEnum {
	FTTC("FTTC"),
	FTTB_FTTH("FTTB/FTTH"),
	CABLENETWORK("Kable Netzwerk"),
	PASSIVE_NETWORK_INFRASTRUCTURE("Passive Netzinfrastruktur (nur bei FFTX/Kabel -Ausbau)"),
	MOBILE_NETWORK("Mobile/Drahtlose Netze"),
	OTHER("Sonstige Kosten");

	//For Language Translation i would normally use a language pack not this
	private final String germanLabel;
}
