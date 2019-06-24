package apikey

import au.org.ala.web.AlaSecured
import au.org.ala.web.CASRoles

class GetKeyController {

    def index() { }

    LocalAuthService localAuthService

    @AlaSecured(value = CASRoles.ROLE_ADMIN, statusCode = 403, view = '/getKey/notCreated')
    def submit() {
        final userDetails = localAuthService.userDetails()
        final userId = userDetails[1]
        final userEmail = userDetails[0]
        //check the permissions - only users with ADMIN can request a key
//        if (request.isUserInRole(CASRoles.ROLE_ADMIN)) {
            APIKey key = new APIKey()
            key.apikey = UUID.randomUUID().toString()
            key.userId = userId
            key.userEmail = userEmail
            key.enabled = true
            App app = App.findByName(params.appName)
            key.app = app
            key.save(validate: true, flush: true)

            if (key.hasErrors()) {
                log.error("Creating apikey failed due to an error userId={} userEmail={} app={}", userId, userEmail, params.appName)
                key.errors.each { log.error(it) }
            } else {
                log.info("Created a new apikey apikey={} userId={} userEmail={} app={}", key.apikey, userId, userEmail, params.appName)
                render(view: "created", model: [key: key])
            }
//        } else {
//            log.warn("Authentication failed when attempting to generate new apikey userId={} userEmail={} app={}", userId, userEmail, params.appName)
//            render(view: "notCreated", model: [requiredRole: "ROLE_ADMIN"])
//        }
    }
}
