package org.smart4j.framework.domain.hierarchy.organization;

// 各類組織結構具體實現
public class OperatingUnit extends Organization {

	public OperatingUnit(OrganizationStructureType type) {
		super(type);
	}

	@Override
	public void setParent(Organization parent) {
		// 增加 Constraint Rule
		throw new RuntimeException("cannot have a parent");
	}
}
