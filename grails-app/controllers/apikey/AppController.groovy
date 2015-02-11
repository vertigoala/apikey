package apikey

class AppController {

    LocalAuthService localAuthService

    def index() {}

    def addAnApp(){
        def result = App.findByName(params.name)
        if(!result){
            if(localAuthService.isAdmin()){
                App app = new App([name:params.name])
                def userDetails = localAuthService.userDetails()
                app.userId =  userDetails[0]
                app.userEmail = userDetails[0]
                app.save(true)
                if(app.hasErrors()){
                    ["success":false, authorised:true]
                } else {
                    ["success":true, app:app, authorised:true]
                }
            } else {
                ["success":false, authorised:false]
            }
        } else {
            ["success":false]
        }
    }
}
