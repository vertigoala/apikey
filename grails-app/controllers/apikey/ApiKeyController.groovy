package apikey

import au.org.ala.web.AlaSecured
import au.org.ala.web.CASRoles
import au.org.ala.web.NoSSO
import au.org.ala.web.SSO

@SSO
@AlaSecured(value = CASRoles.ROLE_ADMIN, statusCode = 403, view='../magicword')
class ApiKeyController {

    static allowedMethods = [enableKey: 'POST']

    def apiKeyService

    def index() {
        def max = params.int('max', 100)
        def offset = params.int('offset', 0)
        def sort = params.get('sort', 'app.name')
        def order = params.get('order', 'asc')
        def keys = apiKeyService.findAllKeys(sort, order, max, offset)
        respond keys
    }

    @NoSSO
    def enableKey() {
        withForm {
            def key = params['key']
            def enabled = params.boolean('enabled', true)

            def max = params.int('max')
            def offset = params.int('offset')
            def sort = params['sort']
            def order = params['order']
            if (key) {
                def result = apiKeyService.enableKey(key, enabled)
                if (!result) {
                    return render(status: 404)
                }
                log.info("Key {} key={} user={}", result.enabled ? 'enabled' : 'disabled', result.apikey, request.remoteUser)
            } else {
                flash.message = 'No key provided'
            }
            return redirect(action: 'index', params: [max: max, offset: offset, sort: sort, order: order].findAll { it.value })
        }.invalidToken {
            return render(status: 403, view: '../magicword')
        }
    }
}
