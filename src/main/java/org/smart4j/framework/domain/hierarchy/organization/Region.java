package org.smart4j.framework.domain.hierarchy.organization;

public class Region extends Organization {

	public Region(OrganizationStructureType type) {
		super(type);
	}

	@Override
	public void setParent(Organization parent) {
		super.checkConstraintRule(parent, OperatingUnit.class);
		super.setParent(parent);
	}

}
