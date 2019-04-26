package apikey

class GetKeyController {

    def index() { }

    LocalAuthService localAuthService

    def submit() {
        //check the permissions - only users with ADMIN can request a key
        if(localAuthService.isAdmin()){
            APIKey key = new APIKey()
            key.apikey = UUID.randomUUID().toString()
            key.userId = localAuthService.userDetails()[1]
            key.userEmail = localAuthService.userDetails()[0]
            App app = App.findByName(params.appName)
            key.app = app
            key.save(validate: true, flush: true)

            if(key.hasErrors()){
                log.error "Creating apikey failed due to an error userId=" + localAuthService.userDetails()[1] + " userEmail=" + localAuthService.userDetails()[0] + " app=" + params.appName
                key.errors.each { println it }
            } else {
                log.info "Created a new apikey userId=" + localAuthService.userDetails()[1] + " userEmail=" + localAuthService.userDetails()[0] + " app=" + params.appName
                render(view: "created", model: [key: key])
            }
        } else {
            log.warn "Authentication failed when attempting to generate new apikey userId=" + localAuthService.userDetails()[1] + " userEmail=" + localAuthService.userDetails()[0] + " app=" + params.appName
            render(view: "notCreated", model: [requiredRole: "ROLE_ADMIN"])
        }
    }
}
