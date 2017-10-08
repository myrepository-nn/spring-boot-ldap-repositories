package com.nishant.spring.boot.ldap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.spring.boot.ldap.entry.Groups;
import com.nishant.spring.boot.ldap.entry.People;
import com.nishant.spring.boot.ldap.service.Service;
import com.nishant.spring.boot.ldap.vo.GroupVO;
import com.nishant.spring.boot.ldap.vo.PeopleVO;

@RestController
public class Controller {

	@Autowired
	public Service service;
	
	@GetMapping("/peoples")
	public List<People> getAllPeoples() {
		return service.findAllPeople();
	}
	@GetMapping("/getuid/{uid}")
	public People getPeopleByuid(@PathVariable String uid) {
		return service.findPeople(uid);
	}
	@PostMapping("/add")
	public String add(@RequestBody PeopleVO p) {
		return service.save(p);
	}
	@PostMapping("/del")
	public String delete(@RequestBody PeopleVO p) {
		return service.delete(p);
	}
	@PostMapping("/deluid/{uid}")
	public String deleteByuid(@PathVariable String uid) {
		return service.deletebyuid(uid);
	}

	
	
	@GetMapping("/groups")
	public List<Groups> getAllGroups() {
		return service.findAllGroups();
	}
	@GetMapping("/getgroups/{name}")
	public Groups getGroupsByName(@PathVariable String name) {
		return service.findGroupByName(name);
	}
	@PostMapping("/addchildingroup/{groupname}/{child}")
	public String addChildInGroup(@PathVariable String groupname,@PathVariable String child) {
		return service.saveGroupChild(child, groupname);
	}
	@PostMapping("/addpeopleinchild/{groupname}/{child}/{membername}")
	public String addPeopleInChild(@PathVariable String groupname,@PathVariable String child,@PathVariable String membername) {
		return service.addPeopleInGroups(child, groupname,membername);
	}
	@PostMapping("/removepeopleinchild/{groupname}/{child}/{membername}")
	public String removePeopleInChild(@PathVariable String groupname,@PathVariable String child,@PathVariable String membername) {
		return service.removePeopleInGroups(child, groupname,membername);
	}
	@PostMapping("/delchildingroup/{child}")
	public String delChildInGroup(@PathVariable String child) {
		return service.deleteChild(child);
	}
	@PostMapping("/addgroup")
	public String addGroup(@RequestBody GroupVO g) {
		return service.saveGroups(g);
	}
	@PostMapping("/delgroup")
	public String deleteGroup(@RequestBody GroupVO g) {
		return service.deleteGroups(g);
	}
	@PostMapping("/delgroupbyname/{groupname}")
	public String deleteGroupByName(@PathVariable String groupname) {
		return service.deleteGroupsByName(groupname);
	}


}