package com.impresssol.broadband.data.entities.pricing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PricingItemTypeEnum {

	ACCESS_EMPTY_PIPES("Zugang zu Leerrohren"),
	DEBUNDLED_ACCESS("entbündelter Zugang"),
	BITSTREAM_ACCESS("Bitstromzugang"),
	ACCESSDARKFIBER("accessDarkFiber"),
	SHARED_USE("gemeinsame Nutzung der physischen Masten"),
	BACKHAULNET_WORKACCESS("Zugang zu Backhaulnetzen");

	private final String germanLabel;

}
