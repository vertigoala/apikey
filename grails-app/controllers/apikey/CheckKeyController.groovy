package apikey

import au.org.ala.web.SSO
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

class CheckKeyController {

    def apiKeyService

    def index() {}

//    @Override
//    HttpServletRequest getRequest() {
//        return null
//    }

    @SSO(gateway = true)
    def checkKey() {
        def result = APIKey.findByApikey(params.apikey)
        [valid:result!=null && result.enabled, key:result]
    }

    def webserviceCheck(){
        final apikey = params['apikey']
        final remoteAddr = request.remoteAddr
        final result = apiKeyService.validateKey(apikey, remoteAddr)

        def jsonValue
        if (result) {
            if (result.enabled) {
                log.info('Valid apikey request apikey={} userId={} userEmail={} app={} remoteAddr={}', params.apikey, result.userId, result.userEmail, result.app.name, remoteAddr)
                jsonValue = [
                        valid:true,
                        userId:result.userId,
                        userEmail: result.userEmail,
                        app: result.app.name
                ]
            } else {
                log.info('Disabled apikey request apikey={} userId={} userEmail={} app={} remoteAddr={}', params.apikey, result.userId, result.userEmail, result.app.name, remoteAddr)
                jsonValue = [valid: false]
            }
        } else {
            log.warn("Invalid apikey request apikey={} remoteAddr={}", params.apikey, remoteAddr)
            jsonValue = [valid: false]
        }
        return render(jsonValue as JSON, contentType:'application/json', status: 200)
    }
}
