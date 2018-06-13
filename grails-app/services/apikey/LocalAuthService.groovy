package apikey

import org.springframework.web.context.request.RequestContextHolder

class LocalAuthService {

    def isAdmin() {
        def authorities = RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.authority
        if(authorities){
            if(authorities.contains("ROLE_ADMIN")){
                return true
            }
        }
        false
    }

    def userDetails() {
        return [(RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.email?.toString()?.toLowerCase()  ) ?: null,
                (RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.userid?.toString()?.toLowerCase()  ) ?: null]
    }
}
