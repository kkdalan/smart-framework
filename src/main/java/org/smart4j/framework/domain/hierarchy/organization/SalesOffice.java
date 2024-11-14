package org.smart4j.framework.domain.hierarchy.organization;

public class SalesOffice extends Organization {

	public SalesOffice(OrganizationStructureType type) {
		super(type);
	}

	@Override
	public void setParent(Organization parent) {
		super.checkConstraintRule(parent, Division.class);
		super.setParent(parent);
	}
}