/**
 * This file is merged (at runtime) with the Grails "main" config by the ala-web-theme plugin.
 *
 * Edit this file to suit your app's dev/test/prod environments
 * OR override these values in your app's Config.groovy file and comment-out in this file
 */

security.cas.casServerName = 'https://auth.ala.org.au'
security.cas.uriFilterPattern = "/testAuth.*, /upload.*, /getKey/submit, /app/addAnApp" // pattern for pages that require authentication
security.cas.uriExclusionFilterPattern = '/images.*,/css.*,/js.*,/less.*'
security.cas.authenticateOnlyIfLoggedInPattern = "" // pattern for pages that can optionally display info about the logged-in user
security.cas.loginUrl = 'https://auth.ala.org.au/cas/login'
security.cas.logoutUrl = 'https://auth.ala.org.au/cas/logout'
security.cas.casServerUrlPrefix = 'https://auth.ala.org.au/cas'
security.cas.bypass = false

appContext = 'apikey'
headerAndFooter.baseURL = 'http://www2.ala.org.au/commonui'
ala.baseURL = "http://www.ala.org.au"
bie.baseURL = "http://bie.ala.org.au"
bie.searchPath = "/search"
grails.project.groupId = "au.org.ala" // change this to alter the default package name and Maven publishing destination

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.host = "http://localhost"
        grails.serverURL = "${grails.host}:8080/${appContext}"
        security.cas.appServerName = "${grails.host}:8080"
        security.cas.contextPath = "/${appContext}"
        grails.resources.debug = true // cached-resources plugin - keeps original filenames but adds cache-busting params
    }
    test {
        grails.logging.jul.usebridge = false
        grails.host = "http://localhost"
        grails.serverURL = "${grails.host}:8080/${appContext}"
        security.cas.appServerName = grails.serverURL
        security.cas.contextPath = "/apikey"
        log4j.appender.'errors.File'="/var/log/tomcat/apikey-stacktrace.log"
    }
    production {
        grails.logging.jul.usebridge = false
//        grails.host = "http://moyesyside.ala.org.au"
//        grails.serverURL = "${grails.host}:8080/${appContext}"
        grails.host = "auth.ala.org.au"
        grails.serverURL = "https://${grails.host}"
        security.cas.appServerName = grails.serverURL
        security.cas.contextPath = "/apikey"
        log4j.appender.'errors.File'="/var/log/tomcat6/apikey-stacktrace.log"
    }
}