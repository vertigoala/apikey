package apikey

class BootStrap {

    def init = { servletContext ->
        preloadQueries()
    }
    def destroy = {
    }

    private def preloadQueries() {
        def appNames = []
        App.findAll().each {appNames << it.name}
        if(!appNames.contains("collectory")){ new App([name:"collectory"]).save() }
        if(!appNames.contains("biocache")){ new App([name:"biocache"]).save() }
        if(!appNames.contains("spatialportal")){ new App([name:"spatialportal"]).save() }
        if(!appNames.contains("bdrs")){ new App([name:"bdrs"]).save() }
        if(!appNames.contains("fieldcapture")){ new App([name:"fieldcapture"]).save() }
        if(!appNames.contains("soils2sat")){ new App([name:"soils2sat"]).save() }
        if(!appNames.contains("alerts")){ new App([name:"alerts"]).save() }
        if(!appNames.contains("avh")){ new App([name:"avh"]).save() }
        if(!appNames.contains("ozcam")){ new App([name:"ozcam"]).save() }
        if(!appNames.contains("regions")){ new App([name:"regions"]).save() }
        if(!appNames.contains("specieslists")){ new App([name:"specieslists"]).save() }
        if(!appNames.contains("fielddata")){ new App([name:"fielddata"]).save() }
        if(!appNames.contains("sightings")){ new App([name:"sightings"]).save() }
        if(!appNames.contains("bowerbird")){ new App([name:"bowerbird"]).save() }
        if(!appNames.contains("said")){ new App([name:"said"]).save() }
        if(!appNames.contains("emmet")){ new App([name:"emmet"]).save() }
        if(!appNames.contains("danno")){ new App([name:"danno"]).save() }
        if(!appNames.contains("amrin")){ new App([name:"amrin"]).save() }
        if(!appNames.contains("fishmap")){ new App([name:"fishmap"]).save() }
        if(!appNames.contains("biolink")){ new App([name:"biolink"]).save() }
        if(!appNames.contains("edgar")){ new App([name:"edgar"]).save() }
        if(!appNames.contains("volunteerportal")){ new App([name:"volunteerportal"]).save() }
        if(!appNames.contains("m.ala.org.au")){ new App([name:"m.ala.org.au"]).save() }
    }
}
