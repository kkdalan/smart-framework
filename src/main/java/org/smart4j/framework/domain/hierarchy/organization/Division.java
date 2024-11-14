package org.smart4j.framework.domain.hierarchy.organization;

public class Division extends Organization {

	public Division(OrganizationStructureType type) {
		super(type);
	}

	@Override
	public void setParent(Organization parent) {
		super.checkConstraintRule(parent, Region.class);
		super.setParent(parent);
	}
}
