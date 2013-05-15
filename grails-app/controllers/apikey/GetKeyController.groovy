package apikey

import org.springframework.web.context.request.RequestContextHolder

class GetKeyController {

    def index() { }

    def authService

    def submit() {
        //check the permissions - only users with ADMIN can request a key
        log.debug "generating a key"
        log.debug "user id: " + authService.userDetails()[1]
        log.debug "user email: " + authService.userDetails()[0]
        log.debug "params.appName: " + params.appName

        if(authService.isAdmin()){
            APIKey key = new APIKey()
            key.apikey = UUID.randomUUID().toString()
            key.userId = authService.userDetails()[1]
            key.userEmail = authService.userDetails()[0]
            App app = App.findByName(params.appName)
            key.app = app
            key.save(true)

            if(key.hasErrors()){
                key.errors.each { println it }
            } else {
                render(view: "created", model: [key: key])
            }
        } else {
            render(view: "notCreated", model: [requiredRole: "ROLE_ADMIN"])
        }
    }
}
