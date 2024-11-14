package org.smart4j.framework.domain.hierarchy.accountability;

import java.util.List;

import org.apache.tools.ant.taskdefs.email.EmailAddress;

public class Party {

	private String partyName;
	private Accountability accountability;
	private List<TelephoneNumber> telephoneNumbers;
	private List<Address> addresses;
	private List<EmailAddress> emailAddresses;

	public Party(String partyName) {
		this(partyName, null, null, null, null);
	}

	public Party(String partyName, Accountability accountability) {
		this(partyName, accountability, null, null, null);
	}

	public Party(String partyName, Accountability accountability, List<TelephoneNumber> telephoneNumbers,
			List<Address> addresses, List<EmailAddress> emailAddresses) {
		this.partyName = partyName;
		this.accountability = accountability;
		this.telephoneNumbers = telephoneNumbers;
		this.addresses = addresses;
		this.emailAddresses = emailAddresses;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Accountability getAccountability() {
		return accountability;
	}

	public void setAccountability(Accountability accountability) {
		this.accountability = accountability;
	}

	public List<TelephoneNumber> getTelephoneNumbers() {
		return telephoneNumbers;
	}

	public void setTelephoneNumbers(List<TelephoneNumber> telephoneNumbers) {
		this.telephoneNumbers = telephoneNumbers;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
}