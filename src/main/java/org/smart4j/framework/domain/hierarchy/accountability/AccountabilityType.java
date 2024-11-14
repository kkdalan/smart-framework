package org.smart4j.framework.domain.hierarchy.accountability;

// AccountabilityType 類別
public class AccountabilityType {
	private String typeName;

	// Constructors, Getters and Setters
	public AccountabilityType(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}