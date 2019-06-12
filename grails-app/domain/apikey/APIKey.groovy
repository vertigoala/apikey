package apikey

class APIKey {

    String apikey
    String userId //system wide user identifier
    String userEmail //stored redundantly for debug purposes
    boolean enabled
    App app
    Date dateCreated

    Date lastUsed
    String lastRemoteAddr

    static constraints = {
        apikey size: 6..36
        userEmail email: true
        lastUsed nullable: true
        lastRemoteAddr nullable: true
    }

    static mapping = {
        apikey index: true
        userId index: true
        autoTimestamp true
        version false
    }
}
