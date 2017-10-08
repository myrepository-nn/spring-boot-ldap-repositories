package com.nishant.spring.boot.ldap.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;
import com.nishant.spring.boot.ldap.entry.People;

public class PeopleRepoImpl implements PeopleRepoExtra {
	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public String createPeople(People people) {
		ldapTemplate.create(people);
		return CONSTANTS.SUCCESS;
	}

}
