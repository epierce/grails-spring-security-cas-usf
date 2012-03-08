import java.security.MessageDigest

includeTargets << grailsScript("Init")

target(main: "Creates artifacts for the Spring Security CAS (USF) plugin") {
    def appDir = "$basedir/grails-app"
	def configFile = new File(appDir, 'conf/Config.groovy')
	def appName = Ant.project.properties.'base.name'
	
	
	MessageDigest md5 = MessageDigest.getInstance("MD5");
	String var_dt = new Date().getTime() / 1000
	md5.update(var_dt.getBytes());
	BigInteger hash = new BigInteger(1, md5.digest());
	String uniqKey = hash.toString(16);
	
	if (configFile.exists()) {
		configFile.withWriterAppend {
			it.writeLine '\n// Added by the Spring Security CAS (USF) plugin:'
			it.writeLine "grails.plugins.springsecurity.userLookup.userDomainClassName = 'edu.usf.cims.UsfCasUser'"
			it.writeLine "grails.plugins.springsecurity.cas.active = true"
			it.writeLine "grails.plugins.springsecurity.cas.sendRenew = false"
			it.writeLine "grails.plugins.springsecurity.cas.key = '${uniqKey}' //unique value for each app"
			it.writeLine "grails.plugins.springsecurity.cas.artifactParameter = 'ticket'"
			it.writeLine "grails.plugins.springsecurity.cas.serviceParameter = 'service'"
			it.writeLine "grails.plugins.springsecurity.cas.filterProcessesUrl = '/j_spring_cas_security_check'"
			it.writeLine "grails.plugins.springsecurity.cas.proxyCallbackUrl = 'http://localhost:8080/${appName}/secure/receptor' "
			it.writeLine "grails.plugins.springsecurity.cas.proxyReceptorUrl = '/secure/receptor'"
			it.writeLine "grails.plugins.springsecurity.cas.useSingleSignout = false"
			it.writeLine "grails.plugins.springsecurity.cas.driftTolerance = 120000"
			it.writeLine "grails.plugins.springsecurity.cas.loginUri = '/login'"
			it.writeLine "grails.plugins.springsecurity.cas.useSamlValidator = true"
			it.writeLine "grails.plugins.springsecurity.cas.authorityAttribute = 'eduPersonEntitlement'"
			it.writeLine "grails.plugins.springsecurity.cas.serverUrlPrefix = 'https://authtest.it.usf.edu'"
			it.writeLine "grails.plugins.springsecurity.cas.serviceUrl = 'http://localhost:8080/${appName}/j_spring_cas_security_check'"
		}
	}
	
	ant.echo """
	********************************************************
	* Your grails-app/conf/Config.groovy has been updated. *
	*                                                      *
	* Please verify that the values are correct.           *
	********************************************************
	"""
}

setDefaultTarget(main)