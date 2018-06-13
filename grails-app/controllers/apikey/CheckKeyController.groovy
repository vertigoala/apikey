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
            response.setStatus(200)
            def jsonValue = [
                    valid:true,
                    userId:result.userId,
                    userEmail: result.userEmail,
                    app: result.app.name
            ]
            render(jsonValue as JSON, contentType:"application/json")
        } else {
            response.setStatus(200)
            render([valid:false] as JSON, contentType:"application/json")
        }
    }
}
