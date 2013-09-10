package org.codehaus.groovy.grails.plugins.springsecurity.cas

import java.util.List;

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.cas.userdetails.GrantedAuthorityFromAssertionAttributesUserDetailsService;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import edu.usf.cims.UsfCasUser

class CasAuthenticationUserDetailsService extends AbstractCasAssertionUserDetailsService {
			
	 /**
	   * When using cas, the password attribute of the User object means nothing.
	   */
	private static final String NON_EXISTENT_PASSWORD_VALUE = "NO_PASSWORD";
	
	/**
	 * When using CAS attributes for GrantedAuthorities, you need to append the 'ROLE_'
	 * prefix for Spring Security
	 */
	private static final Boolean ADD_PREFIX = true
	private static final String PREFIX = "ROLE_"
		
	/**
	 * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least one role, so
	 * we give a user with no granted roles this one which gets past that restriction but
	 * doesn't grant anything.
	 */
	private static final List NO_ROLES = [new GrantedAuthorityImpl("CAS_AUTH")]
		
	private GrantedAuthorityFromAssertionAttributesUserDetailsService grantedAuthoritiesService
	private def authorityAttribNamesFromCas
	/** 
	 * Dependency injection for Getting Authorities from CAS
	 * Provide the attribute name/s that will contain role information from cas
	 */
	public void setAuthorityAttribNamesFromCas(authorityAttribNamesFromCas){
		this.authorityAttribNamesFromCas = authorityAttribNamesFromCas
			
		grantedAuthoritiesService = new GrantedAuthorityFromAssertionAttributesUserDetailsService(
			authorityAttribNamesFromCas.toString())
	}
		
	/** Dependency injection for creating and finding Users **/
	def userMapper
	
	@Override
	protected UserDetails loadUserDetails(Assertion casAssert) {

		//If you want to save the user in a local database, uncomment the following lines,
		//create a domain class that extends org.springframework.security.core.userdetails.User,
		//and update org.codehaus.groovy.grails.plugins.springsecurity.cas.DomainUserMapperService
		//to use your new class
		// 
		//look up user profile in database
		//def user = userMapper.findUserByUsername(casAssert.getPrincipal().getName())
		
		//Create the user profile if it does not already exist
		//if(!user){
			//user = userMapper.newUser(casAssert.getPrincipal().getName(), casAssert.principal)
		//}
		
		//authorities
		def casUser = grantedAuthoritiesService.loadUserDetails(casAssert)
		def casAuthorities = (casUser.authorities)?:NO_ROLES
		
		//Prefix all authorities with 'ROLE_'
		if(ADD_PREFIX){
			List Prefixed_Roles = []
			casAuthorities.each() { authority ->
				Prefixed_Roles.add(new GrantedAuthorityImpl(PREFIX + authority))
			}
			new GrailsUser(casUser.username, NON_EXISTENT_PASSWORD_VALUE, 
				true, true, true, true, Prefixed_Roles, casUser.username)
		} else {
			new GrailsUser(casUser.username, NON_EXISTENT_PASSWORD_VALUE, 
				true, true, true, true, casAuthorities, casUser.username)
		}
	}

}

