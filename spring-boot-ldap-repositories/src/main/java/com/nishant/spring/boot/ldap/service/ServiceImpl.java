package com.nishant.spring.boot.ldap.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import com.nishant.spring.boot.ldap.constants.CONSTANTS;
import com.nishant.spring.boot.ldap.entry.Child;
import com.nishant.spring.boot.ldap.entry.Groups;
import com.nishant.spring.boot.ldap.entry.People;
import com.nishant.spring.boot.ldap.repo.ChildRepo;
import com.nishant.spring.boot.ldap.repo.GroupRepo;
import com.nishant.spring.boot.ldap.repo.PeopleRepo;
import com.nishant.spring.boot.ldap.vo.GroupVO;
import com.nishant.spring.boot.ldap.vo.PeopleVO;

@Component
public class ServiceImpl implements Service {

	@Autowired
	private PeopleRepo peopleRepo;
	@Autowired
	private GroupRepo groupRepo;
	@Autowired
	private ChildRepo childRepo;


	/// Methods for peopleRepo 
	@Override
	public String save(PeopleVO peoplevo) {
		People pep=new People();
		pep.setDn(LdapNameBuilder.newInstance(peoplevo.getDn()).build());
		pep.setDescription(peoplevo.getDescription());
		pep.setFullname(peoplevo.getFullname());
		pep.setGivenname(peoplevo.getGivenname());
		pep.setLastname(peoplevo.getLastname());
		pep.setMail(peoplevo.getMail());
		pep.setManager(peoplevo.getManager());
		pep.setUid(peoplevo.getUid());
		pep.setUserpassword(peoplevo.getUserpassword());
		peopleRepo.createPeople(pep);
		return CONSTANTS.SUCCESS;
	}

	@Override
	public List<People> findAllPeople() {
		List<People> plst=new ArrayList<>();
		Iterable<People> itr=peopleRepo.findAll();
		for(People p:itr) {
			plst.add(p);
		}
		return plst;
	}

	@Override
	public People findPeople(String uid) {
		Optional<People> opt= peopleRepo.findOne(query().where(CONSTANTS.UID).is(uid));
		return opt.isPresent()?opt.get():new People();
	}

	@Override
	public String deletebyuid(String uid) {
		Optional<People> opt= peopleRepo.findOne(query().where(CONSTANTS.UID).is(uid));
		People people= opt.isPresent()?opt.get():new People();
		peopleRepo.delete(people);
		return CONSTANTS.SUCCESS;
	}

	@Override
	public String delete(PeopleVO peoplevo) {
		People pep=new People();
		pep.setDn(LdapNameBuilder.newInstance(peoplevo.getDn()).build());
		pep.setDescription(peoplevo.getDescription());
		pep.setFullname(peoplevo.getFullname());
		pep.setGivenname(peoplevo.getGivenname());
		pep.setLastname(peoplevo.getLastname());
		pep.setMail(peoplevo.getMail());
		pep.setManager(peoplevo.getManager());
		pep.setUid(peoplevo.getUid());
		pep.setUserpassword(peoplevo.getUserpassword());
		peopleRepo.delete(pep);
		return CONSTANTS.SUCCESS;
	}



	/// Methods for groupRepo 
	@Override
	public String saveGroups(GroupVO groups) {
		Groups gps=new Groups();
		gps.setDn(LdapNameBuilder.newInstance(groups.getDn()).build());
		gps.setGroupname(groups.getGroupname());
		gps.setDescription(groups.getDescription());
		groupRepo.createGroup(gps);
		return CONSTANTS.SUCCESS;
	}	
	@Override
	public String saveGroupChild(String child,String groupname) {
		return groupRepo.createGroupChild(child,groupname);
	}

	@Override
	public String addPeopleInGroups(String child,String groupname,String membername) {
		return groupRepo.addPeopleToGroup(child, groupname,membername);
	}

	@Override
	public String removePeopleInGroups(String child,String groupname,String membername) {
		return groupRepo.removePeopleToGroup(child, groupname,membername);
	}

	@Override
	public List<Groups> findAllGroups() {
		List<Groups> glst=new ArrayList<>();
		Iterable<Groups> itr=groupRepo.findAll();
		for(Groups g:itr) {
			glst.add(g);
		}
		return glst;
	}

	@Override
	public Groups findGroupByName(String name) {
		Optional<Groups> opt= groupRepo.findOne(query().where(CONSTANTS.OU).is(name));
		return opt.isPresent()?opt.get():new Groups();
	}

	@Override
	public String deleteGroups(GroupVO groups) {
		Groups gps=new Groups();

		gps.setDn(LdapNameBuilder.newInstance(groups.getDn()).build());
		gps.setGroupname(groups.getGroupname());
		gps.setDescription(groups.getDescription());
		groupRepo.delete(gps);
		return CONSTANTS.SUCCESS;
	}
	@Override
	public String deleteGroupsByName(String groupname) {
		Optional<Groups> opt= groupRepo.findOne(query().where(CONSTANTS.OU).is(groupname));
		Groups gps= opt.isPresent()?opt.get():new Groups();
		groupRepo.delete(gps);
		return CONSTANTS.SUCCESS;
	}

	@Override
	public String deleteChild(String childname) {
		Optional<Child> opt= childRepo.findOne(query().where(CONSTANTS.OBJECTCLASS).is(CONSTANTS.GROUP_OF_UNIQUE_NAMES)
				.and(CONSTANTS.CN).whitespaceWildcardsLike(childname));
		Child chd=opt.isPresent()?opt.get():new Child();
		childRepo.delete(chd);
		return CONSTANTS.SUCCESS;
	}

}
