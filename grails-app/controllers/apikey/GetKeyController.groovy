package apikey

class GetKeyController {

    def index() { }

    LocalAuthService localAuthService

    def submit() {
        //check the permissions - only users with ADMIN can request a key
        log.debug "generating a key"
        log.debug "user id: " + localAuthService.userDetails()[1]
        log.debug "user email: " + localAuthService.userDetails()[0]
        log.debug "params.appName: " + params.appName

        if(localAuthService.isAdmin()){
            APIKey key = new APIKey()
            key.apikey = UUID.randomUUID().toString()
            key.userId = localAuthService.userDetails()[1]
            key.userEmail = localAuthService.userDetails()[0]
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
