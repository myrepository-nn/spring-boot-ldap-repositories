package com.nishant.spring.boot.ldap.repo;

import com.nishant.spring.boot.ldap.entry.Groups;

public interface GroupRepoExtra {
    String createGroup(Groups groups);
	String addPeopleToGroup(String child,String groupname,String membername);
	String removePeopleToGroup(String child, String groupname, String membername);
	String createGroupChild(String child,String groupname);
}
