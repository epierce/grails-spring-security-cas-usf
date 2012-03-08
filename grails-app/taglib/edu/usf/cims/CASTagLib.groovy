package edu.usf.cims

class CASTagLib {
	static namespace = 'cas'
	
	/** Dependency injection for springSecurityService. */
	def usfCasService
	
	/**
	* Renders the user's username if logged in.
	*/
   def username = { attrs ->
	   if (usfCasService.isLoggedIn()) {
		   out << usfCasService.username.encodeAsHTML()
	   }
   }
   
   /**
    * Renders the given attribute if the user is logged in
    * @attr field REQUIRED the attribute name
    */
   def attribute = { attrs ->
	   
	   String field = assertAttribute('name', attrs, 'attribute')
	   
	   if (usfCasService.isLoggedIn()) {
		   Map attributes = usfCasService.attributes
		   if (attributes.containsKey(field)){
			   out <<  attributes.get(field).encodeAsHTML()
		   }
	   }
   }
   
   /**
   * Renders the user's eduPersonPrimaryAffiliation if the user is logged in
   */
  def eppa = { attrs ->
	  if (usfCasService.isLoggedIn()) {
		  out << usfCasService.getEppa().encodeAsHTML()	  
	  }

  }
  
   /**
	 * Renders the body if the specified EPPA matches
	 * @attr eppa REQUIRED the EPPA
    */
   def ifEppa = { attrs, body ->
	      
	   String eppa = assertAttribute('eppa', attrs, 'ifEPPA')
	   
	   if (usfCasService.isLoggedIn()) {
		   
		   if(eppa.compareToIgnoreCase(usfCasService.eppa) == 0){
			   out << body()
		   }
	   }
   } 
  
	  /**
	  * Renders the body if the specified EPPA does NOT match
	  * @attr eppa REQUIRED the EPPA
	  */
	 def ifNotEppa = { attrs, body ->
			
		 String eppa = assertAttribute('eppa', attrs, 'ifNotEPPA')
		 
		 if (usfCasService.isLoggedIn()) {	 
			 if(eppa.compareToIgnoreCase(usfCasService.eppa) != 0){
				 out << body()
			 }
		 }
	 }
 
  /**
   * Renders the body if the user is authenticated.
   */
  def ifLoggedIn = { attrs, body ->
	  if (usfCasService.isLoggedIn()) {
		  out << body()
	  }
  }
  
  /**
  * Renders the body if the user is not authenticated.
  */
	 def ifNotLoggedIn = { attrs, body ->
		 if (!usfCasService.isLoggedIn()) {
			 out << body()
		 }
	 }
   
   protected assertAttribute(String name, attrs, String tag) {
	   if (!attrs.containsKey(name)) {
		   throwTagError "Tag [$tag] is missing required attribute [$name]"
	   }
	   attrs.remove name
   }
   
}
