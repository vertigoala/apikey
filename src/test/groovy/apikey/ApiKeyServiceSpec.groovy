package apikey

import grails.test.hibernate.HibernateSpec
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ApiKeyService)
class ApiKeyServiceSpec extends HibernateSpec {

    def setup() {
        service.transactionManager = transactionManager
    }

    def cleanup() {
    }

    List<Class> getDomainClasses() { [APIKey, App] }

    void "test enable key"() {
        when:
        def app = new App(name: 'a', userEmail: 'a@b.net', userId: '1234', dateCreated: new Date())
        App.saveAll(app)
//        app.save()
        def key = new APIKey(apikey: '123456', userEmail: 'a@b.net', userId: '1234', app: app, enabled: false, dateCreated: new Date())
        APIKey.saveAll(key)
//        key.save()

        then:
        App.count == 1
        APIKey.count == 1

        when:
        def enabledKey = service.enableKey('123456', true)

        then:
        enabledKey.apikey == '123456'
        enabledKey.enabled == true
    }

    void "test validate key"() {
        when:
        def app = new App(name: 'a', userEmail: 'a@b.net', userId: '1234', dateCreated: new Date())
        App.saveAll(app)
//        app.save()
        def lastUsed = new Date() - 1
        def key = new APIKey(apikey: '123456', userEmail: 'a@b.net', userId: '1234', app: app, enabled: false, dateCreated: new Date(), lastUsed: lastUsed)
        APIKey.saveAll(key)
//        key.save()

        then:
        App.count == 1
        APIKey.count == 1

        when:
        def enabledKey = service.validateKey('123456', 'ABCD:ABCD:ABCD:ABCD:ABCD:ABCD:192.168.158.190')

        then:
        enabledKey.apikey == '123456'
        enabledKey.lastRemoteAddr == 'ABCD:ABCD:ABCD:ABCD:ABCD:ABCD:192.168.158.190'
        enabledKey.lastUsed > lastUsed

        when: "non existant key is used"
        def invalidKey = service.validateKey('123457', '127.0.0.1')

        then: "validateKey returns null"
        invalidKey == null
    }

    void "test find all keys"() {
        when:
        def apps = [
                new App(name: 'a', userEmail: 'a@b.net', userId: '1234', dateCreated: new Date())
                ,new App(name: 'b', userEmail: 'b@b.net', userId: '1235', dateCreated: new Date())
        ]
        App.saveAll(apps)

        def keys = [
                new APIKey(apikey: '123456', userEmail: 'a@b.net', userId: '1234', app: apps[0], enabled: true, dateCreated: new Date())
                ,new APIKey(apikey: '123457', userEmail: 'a@b.net', userId: '1234', app: apps[0], enabled: false, dateCreated: new Date())
                ,new APIKey(apikey: '123458', userEmail: 'b@b.net', userId: '1235', app: apps[1], enabled: true, dateCreated: new Date())
                ,new APIKey(apikey: '123459', userEmail: 'b@b.net', userId: '1235', app: apps[1], enabled: false, dateCreated: new Date())
        ]
        APIKey.saveAll(keys)

        then:
        App.count == 2
        APIKey.count == 4

        when:
        def found = service.findAllKeys('enabled', 'desc', 2, 2)

        then:
        found.size() == 2
        found.totalCount == 4
        found*.apikey.containsAll(['123457', '123459'])
    }
}
