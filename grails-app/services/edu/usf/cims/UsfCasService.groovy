package edu.usf.cims

import grails.plugins.springsecurity.SpringSecurityService
import org.springframework.web.context.request.RequestContextHolder

class UsfCasService extends SpringSecurityService {

  static transactional = false
  static scope = "singleton"
	
	def getUsername(){
		super.authentication.name
	}
    def getAttributes(){
		RequestContextHolder.requestAttributes.request.userPrincipal.assertion.principal.attributes
	}
	
	def getEppa() {
		RequestContextHolder.requestAttributes.request.userPrincipal.assertion.principal.attributes.eduPersonPrimaryAffiliation
    }
	
	def getAffiliation(){
		RequestContextHolder.requestAttributes.request.userPrincipal.assertion.principal.attributes.eduPersonAffiliation
	}
}
