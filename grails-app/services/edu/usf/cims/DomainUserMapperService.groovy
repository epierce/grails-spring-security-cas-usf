package edu.usf.cims

import org.codehaus.groovy.grails.plugins.springsecurity.cas.DomainUserMapper;
import org.jasig.cas.client.authentication.AttributePrincipal;

class DomainUserMapperService implements DomainUserMapper{

  static transactional = false

	Object newUser(String username, AttributePrincipal principal){
		def user = new UsfCasUser( username:	username, attributes: principal.getAttributes() )
				
		try{
			user.save()
		}catch(Exception e){
			throw new Exception("Unable to create and save user to the database", e)
		}
		
		user
	}
	
	Object findUserByUsername(String username){
		UsfCasUser.findByUsername(username)
	}
}
