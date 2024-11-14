package org.smart4j.framework.domain.hierarchy.accountability;

// TimePeriod 類別
public class TimePeriod {
	
	private String startDate;
	private String endDate;

	// Constructors, Getters and Setters
	public TimePeriod(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}