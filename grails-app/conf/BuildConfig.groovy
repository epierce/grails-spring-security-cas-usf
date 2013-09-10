grails.servlet.version = "3.0"
grails.project.class.dir = 'target/classes'
grails.project.test.class.dir = 'target/test-classes'
grails.project.test.reports.dir	= 'target/test-reports'
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
	inherits('global') {
		excludes 'commons-codec' // Grails ships with 1.3, need 1.4
	}
  legacyResolve true
  checksums true
	log 'error'

  repositories {
      inherits true // Whether to inherit repository definitions from plugins

      grailsPlugins()
      grailsHome()
      grailsCentral()
      mavenLocal()
      mavenCentral()
      mavenRepo "http://download.java.net/maven/2"
      mavenRepo "http://developer.ja-sig.org/maven2"
  }

	dependencies {
		compile('org.springframework.security:org.springframework.security.cas:3.0.4.RELEASE') {
			transitive = false
		}
		compile('org.jasig.cas:com.springsource.org.jasig.cas.client:3.1.8') {
			transitive = false
		}
		compile('org.opensaml:opensaml:1.1') {
			transitive = false
		}
		compile('xml-security:xmlsec:1.3.0') {
			transitive = false
		}
	}

  plugins {
    compile ":spring-security-core:1.2.7.3"
    }
}
