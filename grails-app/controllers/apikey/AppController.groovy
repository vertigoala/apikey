package apikey

import au.org.ala.web.AlaSecured
import au.org.ala.web.CASRoles
import au.org.ala.web.SSO

class AppController {

    LocalAuthService localAuthService

    def index() {}

    @SSO
    @AlaSecured(value = CASRoles.ROLE_ADMIN, statusCode = 403)
    def addAnApp() {
        def result = App.findByName(params.name)
        if (!result) {
            App app = new App([name:params.name])
            def userDetails = localAuthService.userDetails()
            app.userId =  userDetails[0]
            app.userEmail = userDetails[0]
            app.save(validate: true, flush: true)
            if (app.hasErrors()) {
                ["success":false, authorised:true]
            } else {
                ["success":true, app:app, authorised:true]
            }
        } else {
            ["success":false]
        }
    }
}
