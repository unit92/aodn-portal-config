/*
 * Copyright 2012 IMOS
 *
 * The AODN/IMOS Portal is distributed under the terms of the GNU General Public
 *  License
 *
 * An example production configuration file for the AODN portal, you could also 
 * configure test and development environments here and point portal to this 
 * config accordingly. Currently portal examines its context information for
 * an Environment setting named aodn.configuration that has the path to an
 * external configuration file such as this as its value.
 * 
 * AODN Portal Source: https://github.com/aodn/aodn-portal
 * AODN Portal: http://portal.aodn.org.au
 *              http://imos.aodn.org.au (An example of this config)
 */

environments {

    production {
        // URL of your application
        grails.serverURL = "http://imos.aodn.org.au/webportal"

        // The URL of a spatial search instance you want to use, note you may 
        // need to deploy your own instance of spatial search if you want to use 
        // it with your geonetwork data, see the portal wiki for more 
        // information on collaborating applications. This value below is used 
        // by IMOS and is linked with the IMOS geonetwork instance. If you do not
        // want the fine grained spatial results that spatial search offers you 
        // can put a geonetwork URL in here and it should just work
        spatialsearch.url = "http://imossearch.emii.org.au/search/search/index"

        // WMS Scanner scans Geoserver and NcWMS servers defined in portal and
        // will add their layers to the available layers in your portal, the
        // address below is an instance of scanner we make available to the
        // public. You can continue to use this URL or if you want to deploy
        // your own scanner instance we can help with that too, just get in
        // touch with us info at imos dot org dot au
        // It's probable that you won't be able to use this scanner instance in
        // your development environment. You can either add layers manually or
        // you can go here https://svn.emii.org.au/public/wmsscanner-dist/tags
        // and grab a build (preferably the latest) and deploy it somewhere your
        // development environment will have access
        wmsScanner.url = "http://wmsscannerpublic.aodn.org.au/wmsscanner/"

        // URL for an OpenID authentication service for your portal to use.
        // Note we have a custom implementation and that is all that we have
        // tested portal with, in most cases all OpenID authentication services
        // should work but your mileage may vary. Contributions to improve
        // authentication services are willingly accepted
        openIdProvider.url = "not disclosed"

        // Configure your data source here
        dataSource {
            jndiName = "java:comp/env/jdbc/imosportal"
        }
    }
}


portal {
    
    instance { 
        // The name is used to load instance specific data migrations, in most
        // cases this should not be an issue for you so feel free to put any
        // suitable name for your instance here. The application will default
        // to AODN if no name is set
        name = "IMOS"

        // URL to serve your site CSS from, this does not have to be from within
        // portal's context e.g. http://me.example.com/static/css/portal.css
        css = "/webportal/css/IMOS.css"
        splash { 
            // A URL for the home page content, this is requested via an 
            // internal proxy so can be served from anywhere, see the images
            // directory here for the splash.png image to see what part of
            // the screen this setting controls
            index = "http://imos.aodn.org.au/webportal/splash"

            // URL requested via internal proxy for the links section of the
            // home page, see images/links.png for an example
            links = "http://imos.aodn.org.au/webportal/links"

            // URL for the social media panel, again requested via an internal
            // proxy, see images/community.png
            community = "http://imos.aodn.org.au/webportal/community"
        }
    }

    header {
        // This is the logo that appears in the top left hand corner
        logo = "IMOS-wide-logo-white.png"
        // Builds the link that appears in the top right hand corner next to 
        // Help link
        organisationLink {
            linkText = "IMOS"
            tooltipText = "Integrated Marine Observing System"
            url = "http://imos.org.au/"
        }
    }

    systemEmail {
        fromAddress = "not disclosed"
    }
}

// You can use the Grails logging DSL to configure your application's logging
log4j = {
    appenders {
        console name:'stdout', layout: pattern(conversionPattern: "%-5p %d [%-12t] %30.30c %X{userInfoForFile}- %m%n")
    }

    root {
        error 'stdout'
    }

    error   'org.codehaus.groovy.grails.web.servlet',
            'org.codehaus.groovy.grails.web.pages',
            'org.codehaus.groovy.grails.web.sitemesh',
            'org.codehaus.groovy.grails.web.mapping.filter',
            'org.codehaus.groovy.grails.web.mapping',
            'org.codehaus.groovy.grails.commons',
            'org.codehaus.groovy.grails.plugins',
            'org.codehaus.groovy.grails.orm.hibernate',
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate',
            'org.grails.plugin.resource.ResourceMeta'

    warn    'org.mortbay.log'

    info    'grails.app.tagLib.au.org.emii.portal.UserTagLib',
            'grails.app.filters.shiro.SecurityFilters',
            'grails.app.controller.au.org.emii.portal.LayerController',
            'grails.app.controller.au.org.emii.portal.AuthController',
            'grails.app.service.au.org.emii.portal.LayerService',
            'grails.app.service.au.org.emii.portal.AodaacAggregatorService',
            'au.org.emii.portal.display.MenuJsonCache',
            'org.apache.shiro',
            'grails.app.controller'

    debug   'grails.app.job',
            'grails.app.tagLib',
            'grails.app.domain',
            'grails.app.realms'
}
