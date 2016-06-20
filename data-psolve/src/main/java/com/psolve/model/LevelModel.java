package com.psolve.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LevelModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "value", unique = true)
	private Long value;

	@Column(name = "xp_needed")
	private Double xpNeeded;


	@Column(name = "capped")
	private Boolean capped;

	@OneToOne
	private LevelModel nextLevelModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public double getXpNeeded() {
		return xpNeeded;
	}

	public void setXpNeeded(double xpNeeded) {
		this.xpNeeded = xpNeeded;
	}


	public Boolean getCapped() {
		return capped;
	}

	public void setCapped(Boolean capped) {
		this.capped = capped;
	}

	public LevelModel getNextLevelModel() {
		return nextLevelModel;
	}

	public void setNextLevelModel(LevelModel nextLevelModel) {
		this.nextLevelModel = nextLevelModel;
	}
}
