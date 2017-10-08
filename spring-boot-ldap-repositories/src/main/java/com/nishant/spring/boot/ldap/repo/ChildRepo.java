package com.nishant.spring.boot.ldap.repo;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.nishant.spring.boot.ldap.entry.Child;

@Repository
public interface ChildRepo extends LdapRepository<Child> {

}
