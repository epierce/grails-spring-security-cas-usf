package org.codehaus.groovy.grails.plugins.springsecurity.cas

import org.codehaus.groovy.grails.plugins.springsecurity.cas.DomainUserMapper;
import org.jasig.cas.client.authentication.AttributePrincipal;
import edu.usf.cims.UsfCasUser;

class DomainUserMapperService implements DomainUserMapper{

    static transactional = true

	Object newUser(String username, AttributePrincipal principal){
		def user = new UsfCasUser( username:	username,
									attributes: principal.getAttributes() )
				
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
