package org.smart4j.framework.domain.hierarchy.organization;

//組織基礎類別
public abstract class Organization extends OrganizationStructure<Organization> {

	public Organization(OrganizationStructureType type) {
		super(type);
	}
	
	protected void checkConstraintRule(Organization parent, Class<?> targetType) {
		// 增加 Constraint Rule
		if (!(parent.getClass().equals(targetType))) {
			throw new RuntimeException("parent target type must be " + targetType.getSimpleName());
		}
	}

}
