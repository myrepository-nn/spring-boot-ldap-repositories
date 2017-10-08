package com.nishant.spring.boot.ldap.repo;

import java.util.HashSet;
import java.util.Set;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;
import com.nishant.spring.boot.ldap.entry.Child;
import com.nishant.spring.boot.ldap.entry.Groups;
import com.nishant.spring.boot.ldap.repo.GroupRepoExtra;

public class GroupRepoImpl implements GroupRepoExtra {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public String createGroup(Groups groups) {
		ldapTemplate.create(groups);
		return CONSTANTS.SUCCESS;
	}
	@Override
	public String createGroupChild(String child,String groupname) {
		Child cr=new Child();
		cr.setDn(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_GROUPS).add(CONSTANTS.OU, groupname).add(CONSTANTS.CN, child).build());
		cr.setName(child);
		Set<Name> st=new HashSet<>();
		st.add(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_PEOPLE_GROUP).add(CONSTANTS.CN, "ADMIN").build());
		cr.setMembers(st);
		ldapTemplate.create(cr);
		return CONSTANTS.SUCCESS;
	}

	@Override
	public String addPeopleToGroup(String child,String groupname,String membername) {
		DirContextOperations ctx = ldapTemplate.lookupContext(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_GROUPS).add(CONSTANTS.OU, groupname).add(CONSTANTS.CN, child).build());
		ctx.addAttributeValue(CONSTANTS.UNIQUEMEMBER, LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_PEOPLE_GROUP).add(CONSTANTS.CN, membername).build());
		ldapTemplate.modifyAttributes(ctx);
		return CONSTANTS.SUCCESS;
	}
	@Override
	public String removePeopleToGroup(String child,String groupname,String membername) {
		DirContextOperations ctx = ldapTemplate.lookupContext(LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_GROUPS).add(CONSTANTS.OU, groupname).add(CONSTANTS.CN, child).build());
		ctx.removeAttributeValue(CONSTANTS.UNIQUEMEMBER, LdapNameBuilder.newInstance().add(CONSTANTS.OU, CONSTANTS.BASE_PEOPLE_GROUP).add(CONSTANTS.CN, membername).build());
		ldapTemplate.modifyAttributes(ctx);
		return CONSTANTS.SUCCESS;
	}
}
