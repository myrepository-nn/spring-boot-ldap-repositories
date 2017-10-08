package com.nishant.spring.boot.ldap.service;

import java.util.List;

import com.nishant.spring.boot.ldap.entry.Groups;
import com.nishant.spring.boot.ldap.entry.People;
import com.nishant.spring.boot.ldap.vo.GroupVO;
import com.nishant.spring.boot.ldap.vo.PeopleVO;

public interface Service {
    
	//People methods
	String save(PeopleVO people);
	List<People> findAllPeople();
	People findPeople(String uid);
	String deletebyuid(String uid);
	String delete(PeopleVO peoplevo);

	//Group Methods
	String saveGroups(GroupVO groups);
	List<Groups> findAllGroups();
	Groups findGroupByName(String name);
	String deleteGroups(GroupVO groups);
	String addPeopleInGroups(String child,String groupname,String membername);
	String removePeopleInGroups(String child, String groupname, String membername);
	String saveGroupChild(String child, String groupname);
	String deleteChild(String childname);
	String deleteGroupsByName(String groupname);
	
}
