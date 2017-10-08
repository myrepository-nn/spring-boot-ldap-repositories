package com.nishant.spring.boot.ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
@Configuration
@EnableLdapRepositories
public class Config {

	@Bean
	public LdapContextSource ldapContextSource() {
		LdapContextSource lcs=new LdapContextSource();
		lcs.setUrl("ldap://localhost:10389");
		//lcs.setBase("o=sevenSeas");
		lcs.setBase("dc=nishant,dc=com");
		return lcs;
	}

	@Bean
	public LdapTemplate ldapTemplate() throws Exception{
		return new LdapTemplate(ldapContextSource());
	}

}