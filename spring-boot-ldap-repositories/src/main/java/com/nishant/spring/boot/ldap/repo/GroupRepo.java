package com.nishant.spring.boot.ldap.repo;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.nishant.spring.boot.ldap.entry.Groups;

@Repository
public interface GroupRepo extends LdapRepository<Groups>,GroupRepoExtra {

}
