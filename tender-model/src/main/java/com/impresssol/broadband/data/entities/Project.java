package com.impresssol.broadband.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.impresssol.broadband.data.entities.district.LocalAuthorityDistrict;
import com.impresssol.broadband.data.entities.pricing.PricingMasterData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@EqualsAndHashCode
@ToString
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private ProjectMasterData projectMasterData;

	@OneToOne(cascade = CascadeType.PERSIST)
	private LocalAuthorityDistrict localAuthorityDistrict;

	@OneToOne(cascade = CascadeType.ALL)
	private PricingMasterData pricingMasterData;
}
