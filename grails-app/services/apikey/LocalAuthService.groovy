package apikey

import org.springframework.web.context.request.RequestContextHolder

class LocalAuthService {

    def userDetails() {
        return [(RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.email?.toString()?.toLowerCase()  ) ?: null,
                (RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.userid?.toString()?.toLowerCase()  ) ?: null]
    }
}
