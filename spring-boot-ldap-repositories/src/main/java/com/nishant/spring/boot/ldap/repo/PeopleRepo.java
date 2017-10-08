package com.nishant.spring.boot.ldap.repo;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.nishant.spring.boot.ldap.entry.People;

@Repository
public interface PeopleRepo extends LdapRepository<People>,PeopleRepoExtra {
	
}
