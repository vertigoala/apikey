package apikey

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
            render(contentType:"text/json") {
                [
                        valid:true,
                        userId:result.userId,
                        userEmail: result.userEmail,
                        app: result.app.name
                ]
            }
        } else {
            response.setStatus(200)
            render(contentType:"text/json") {
                [valid:false]
            }
        }
    }
}
