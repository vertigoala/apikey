package apikey

import grails.testing.web.controllers.ControllerUnitTest
import org.grails.web.servlet.mvc.SynchronizerTokensHolder
import spock.lang.Specification

class ApiKeyControllerSpec extends Specification implements ControllerUnitTest<ApiKeyController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test index happy path"() {
        given:
        App app = new App(name: 'a', userEmail: 'a@.b.net', userId: '1234', dateCreated: new Date())
        List<APIKey> apiKeys = [
                new APIKey(apikey: '123456', app: app, dateCreated: new Date(), userId: '1234', userEmail: 'a@b.net', enabled: true)
        ]

        def apiKeyService = Mock(ApiKeyService)
        controller.apiKeyService = apiKeyService

        request.method = 'GET'
        request.addUserRole('ROLE_ADMIN')
        request.addHeader('Accept', 'text/html')
        params['max'] = 10
        params['offset'] = 10
        params['sort'] = 'userId'
        params['order'] = 'desc'

        when:
        controller.index()

        then: "response is successful"
        1 * apiKeyService.findAllKeys('userId','desc',10,10) >> apiKeys
        status == 200
        model.APIKeyList
        model.APIKeyList == apiKeys
    }

    void "test enableKey GET"() {
        given:
        request.method = 'GET'

        when:
        controller.enableKey()

        then:
        status == 405
    }

    void "test enableKey no token"() {
        given:
        request.method = 'POST'
        request.addUserRole('ROLE_ADMIN')

        def token = SynchronizerTokensHolder.store(session)
        params[SynchronizerTokensHolder.TOKEN_URI] = '/apiKey/index'
        def tokenKey = token.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])
        params[SynchronizerTokensHolder.TOKEN_KEY] = 'asdf'

        when:
        controller.enableKey()

        then:
        status == 403
    }

    void "test enableKey"() {
        given:
        def apiKeyService = Mock(ApiKeyService)
        controller.apiKeyService = apiKeyService

        App app = new App(name: 'a', userEmail: 'a@.b.net', userId: '1234', dateCreated: new Date())

        def token = SynchronizerTokensHolder.store(session)
        params[SynchronizerTokensHolder.TOKEN_URI] = '/apiKey/index'
        params[SynchronizerTokensHolder.TOKEN_KEY] = token.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])

        request.method = 'POST'
        request.remoteUser = 'a@b.net'
        request.addUserRole('ROLE_ADMIN')
        params['key'] = '123456'
        params['enabled'] = 'true'
        params['max'] = 10
        params['order'] = 'desc'

        when:
        controller.enableKey()

        then:
        1 * apiKeyService.enableKey('123456', true) >> new APIKey(apikey: '123456', enabled: true, app: app, dateCreated: new Date(), userId: '1234', userEmail: 'a@b.net',)
        status == 302
        response.redirectedUrl.startsWith('/apiKey/index')
        response.redirectedUrl.contains('max=10')
        response.redirectedUrl.contains('order=desc')
    }
}
