package org.smart4j.framework.domain.hierarchy.accountability;

import java.util.List;

public class Company extends Party {

	private String name;
	private List<Person> contacts;

	public Company(String name, List<Person> contacts) {
		super(name);
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getContacts() {
		return contacts;
	}

	public void setContacts(List<Person> contacts) {
		this.contacts = contacts;
	}
}