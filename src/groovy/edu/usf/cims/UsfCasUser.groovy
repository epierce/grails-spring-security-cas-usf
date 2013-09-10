package edu.usf.cims

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class UsfCasUser extends User {

	private final HashMap _attributes

	UsfCasUser(String username, 
				String password, 
				boolean enabled, 
				boolean accountNonExpired,
				boolean credentialsNonExpired, 
				boolean accountNonLocked,
				Collection<GrantedAuthority> authorities, 
				HashMap attributes) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities)
		_attributes = attributes
	}
	
	Map getAttributes() { _attributes }
	
	String findByUsername(username){
		def user = null
		user
	}
}
