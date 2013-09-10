package edu.usf.cims

import grails.plugins.springsecurity.Secured

class UsfCasTestController {
	def usfCasService

	//Index view lists all of the other views
    def index = { }
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
	def showAttributes = {}
	
	//<cas:*> taglib demo
	//CAS authentication is not required
	def showTagDemo = {}
	
	//force an error page
	@Secured(['ROLE_DOES_NOT_EXIST'])
	def showRoleError = {}
}
