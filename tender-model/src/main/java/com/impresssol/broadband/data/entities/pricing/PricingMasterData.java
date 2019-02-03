package com.impresssol.broadband.data.entities.pricing;

import static javax.persistence.CascadeType.ALL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class PricingMasterData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToMany(orphanRemoval = true, cascade = ALL)
	private final List<PricingDetail> pricingDetails = new ArrayList<>();

	@Column
	@Setter
	private BigDecimal sumValue;

	@Column
	@Setter
	private BigDecimal fundingValue;

}
