package apikey

import grails.converters.JSON

class CheckKeyController {

    def index() {}

    def checkKey() {
        def result = APIKey.findByApikey(params.apikey)
        [valid:result!=null, key:result]
    }

    def webserviceCheck(){
        def result = APIKey.findByApikey(params.apikey)
        if(result){
            log.info "Valid apikey request apikey=" + params.apikey + " userId=" + result.userId + " userEmail=" + result.userEmail + " app=" + result.app.name
            response.setStatus(200)
            def jsonValue = [
                    valid:true,
                    userId:result.userId,
                    userEmail: result.userEmail,
                    app: result.app.name
            ]
            render(jsonValue as JSON, contentType:"application/json")
        } else {
            log.warn "Invalid apikey request apikey=" + params.apikey
            response.setStatus(200)
            render([valid:false] as JSON, contentType:"application/json")
        }
    }
}
