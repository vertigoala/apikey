package apikey

class APIKey {

    String apikey
    String userId //system wide user identifier
    String userEmail //stored redundantly for debug purposes
    App app
    Date dateCreated

    static constraints = {
        apikey size: 6..36
        userEmail email: true
    }

    static mapping = {
        apikey index: true
        userId index: true
        autoTimestamp true
        version false
    }
}
