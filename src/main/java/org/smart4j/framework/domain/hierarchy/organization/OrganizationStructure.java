package org.smart4j.framework.domain.hierarchy.organization;

import java.util.List;

public class OrganizationStructure<T> {

	private OrganizationStructureType type;
	private List<T> subsidiaries;
	private T parent;

	public OrganizationStructure(OrganizationStructureType type) {
		this.type = type;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public void setSubsidiaries(List<T> subsidiaries) {
		this.subsidiaries = subsidiaries;
	}

	public T getParent() {
		return parent;
	}

	public List<T> getSubsidiaries() {
		return subsidiaries;
	}

	public void addSubsidiary(T subsidiary) {
		this.subsidiaries.add(subsidiary);
	}
}
