package apikey

class App {
    String name
    String userId
    String userEmail
    Date dateCreated

    static constraints = {
        userId nullable: true
        userEmail nullable: true
    }

    static mapping = {
        id generator:'assigned', name:'name'
        autoTimestamp true
        version false
    }
}
