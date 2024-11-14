package org.smart4j.framework.domain.hierarchy.organization;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		// 建立組織結構類型和時間段
		OrganizationStructureType type = new OrganizationStructureType("Corporate Structure");

		// 建立各個組織層級
		OperatingUnit operatingUnit = new OperatingUnit(type);
		Region region = new Region(type);
		Division division = new Division(type);
		SalesOffice salesOffice = new SalesOffice(type);

		// 設置層級關係
		operatingUnit.setSubsidiaries(Arrays.asList(region));
		region.setParent(operatingUnit);
		region.setSubsidiaries(Arrays.asList(division));
		division.setParent(region);
		division.setSubsidiaries(Arrays.asList(salesOffice));
		salesOffice.setParent(division);

		// 印出組織層級關係
		System.out.println("Operating Unit -> " + operatingUnit.getSubsidiaries().get(0).getClass().getSimpleName());
		System.out.println("Region -> " + region.getSubsidiaries().get(0).getClass().getSimpleName());
		System.out.println("Division -> " + division.getSubsidiaries().get(0).getClass().getSimpleName());
	}
}