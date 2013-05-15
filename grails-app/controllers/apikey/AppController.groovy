package apikey

class AppController {

    def authService

    def index() {}

    def addAnApp(){
        def result = App.findByName(params.name)
        if(!result){
            if(authService.isAdmin()){
                App app = new App([name:params.name])
                def userDetails = authService.userDetails()
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
