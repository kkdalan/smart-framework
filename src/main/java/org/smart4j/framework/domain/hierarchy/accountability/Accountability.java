package org.smart4j.framework.domain.hierarchy.accountability;

import java.util.List;

public class Accountability {

	private String responsibility;
	private AccountabilityType accountabilityType;
	private List<TimePeriod> timePeriods;
	private List<Action> actions;

	public Accountability(String responsibility, AccountabilityType accountabilityType) {
		this.responsibility = responsibility;
		this.accountabilityType = accountabilityType;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public AccountabilityType getAccountabilityType() {
		return accountabilityType;
	}

	public void setAccountabilityType(AccountabilityType accountabilityType) {
		this.accountabilityType = accountabilityType;
	}

	public List<TimePeriod> getTimePeriods() {
		return timePeriods;
	}

	public void setTimePeriods(List<TimePeriod> timePeriods) {
		this.timePeriods = timePeriods;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

}