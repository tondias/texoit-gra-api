package com.texoit.wellington.gra.domain.enumeration;

public enum AwardClassification {

	PREVIOUS("Previous"), FOLLOWING("following");

	private String classification;

	AwardClassification(String classification) {
		this.classification = classification;
	}

	public String getDescription() {
		return this.classification;
	}
}
