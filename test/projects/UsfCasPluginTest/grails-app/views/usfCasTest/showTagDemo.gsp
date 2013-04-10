<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Welcome to Grails</title>
        <style type="text/css" media="screen">
            #status {
                background-color: #eee;
                border: .2em solid #fff;
                margin: 2em 2em 1em;
                padding: 1em;
                width: 12em;
                float: left;
                -moz-box-shadow: 0px 0px 1.25em #ccc;
                -webkit-box-shadow: 0px 0px 1.25em #ccc;
                box-shadow: 0px 0px 1.25em #ccc;
                -moz-border-radius: 0.6em;
                -webkit-border-radius: 0.6em;
                border-radius: 0.6em;
            }

            .ie6 #status {
                display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
            }

            #status ul {
                font-size: 0.9em;
                list-style-type: none;
                margin-bottom: 0.6em;
                padding: 0;
            }
            
            #status li {
                line-height: 1.3;
            }

            #status h1 {
                text-transform: uppercase;
                font-size: 1.1em;
                margin: 0 0 0.3em;
            }

            #page-body {
                margin: 2em 1em 1.25em 18em;
            }

            h2 {
                margin-top: 1em;
                margin-bottom: 0.3em;
                font-size: 1em;
            }

            p {
                line-height: 1.5;
                margin: 0.25em 0;
            }

            #controller-list ul {
                list-style-position: inside;
            }

            #controller-list li {
                line-height: 1.3;
                list-style-position: inside;
                margin: 0.25em 0;
            }

            @media screen and (max-width: 480px) {
                #status {
                    display: none;
                }

                #page-body {
                    margin: 0 1em 1em;
                }

                #page-body h1 {
                    margin-top: 0;
                }
            }
        </style>
    </head>
    <body>
        <a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="status" role="complementary">
            <h1>Application Status</h1>
            <ul>
                <li>App version: <g:meta name="app.version"/></li>
                <li>Grails version: <g:meta name="app.grails.version"/></li>
                <li>Groovy version: ${groovy.lang.GroovySystem.getVersion()}</li>
                <li>JVM version: ${System.getProperty('java.version')}</li>
                <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
                <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
            </ul>
            <h1>Installed Plugins</h1>
            <ul>
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li>${plugin.name} - ${plugin.version}</li>
                </g:each>
            </ul>
        </div>
        <div id="page-body" role="main">
            <h1>CAS TagLib Demo</h1>
            <p>
                This is a demonstration of the &lt;cas:&gt; taglib library.  View this page before & after logging into CAS to view the difference 
            </p>
            <p>
                <hr />
                <h3>This text is visible to ALL users whether they are logged in or not</h3>
                <hr />
            </p>

		<cas:ifLoggedIn>
            <div id="controllerList" class="dialog">
            	<h2>&lt;cas:ifLoggedIn/&gt;</h2>
                <p>You are authenticated!
                This text is visible ONLY to people who are CAS authenticated</p>
                
                <h2>&lt;cas:username /&gt;</h2>
                <p><cas:username/></p>
                <h2>&lt;cas:eppa /&gt;</h2>
                <p><cas:eppa/></p>
                <h2>&lt;cas:attribute name="mail" /&gt;</h2>
                <p><cas:attribute name="mail"/></p>
                <h2>&lt;cas:attribute name="CommonName" /&gt;</h2>
                <p><cas:attribute name="CommonName"/></p>
                <h2>&lt;cas:ifEppa eppa=* &gt;</h2>
                <p>
					<cas:ifEppa eppa="faculty">Your primary affiliation is <b>Faculty</b></cas:ifEppa>
					<cas:ifEppa eppa="staff">Your primary affiliation is <b>Staff</b></cas:ifEppa>
					<cas:ifEppa eppa="student">Your primary affiliation is <b>Student</b></cas:ifEppa>
                    <cas:ifEppa eppa="alumni">Your primary affiliation is <b>Alumni</b></cas:ifEppa>
                    <cas:ifEppa eppa="affiliate">Your primary affiliation is <b>Affiliate</b></cas:ifEppa>
				</p>
				<h2>&lt;cas:ifNotEppa eppa=* &gt;</h2>
                <p>
                    <cas:ifNotEppa eppa="faculty">Your primary affiliation is NOT <b>Faculty</b><br /></cas:ifNotEppa>
                    <cas:ifNotEppa eppa="staff">Your primary affiliation is NOT <b>Staff</b><br /></cas:ifNotEppa>
                    <cas:ifNotEppa eppa="student">Your primary affiliation is NOT <b>Student</b><br /></cas:ifNotEppa>
                    <cas:ifNotEppa eppa="alumni">Your primary affiliation is NOT <b>Alumni</b><br /></cas:ifNotEppa>
                    <cas:ifNotEppa eppa="affiliate">Your primary affiliation is NOT <b>Affiliate</b><br /></cas:ifNotEppa>
				</p>				
            </div>
        </cas:ifLoggedIn>
        
        <cas:ifNotLoggedIn>
            <div id="controllerList" class="dialog">
            	<h2>&lt;cas:ifNotLoggedIn/&gt;</h2>
                <p>You are not authenticated! This text is visible ONLY to people who are <b>not</b> CAS authenticated</p>
            </div>
        </cas:ifNotLoggedIn>

        <br><br><br><hr>
        <g:link action="index"><h1><< Back</h1></g:link>
        </div>
        
    </body>
</html>