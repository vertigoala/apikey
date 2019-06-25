package apikey

import grails.gorm.transactions.Transactional

@Transactional
class ApiKeyService {

    @Transactional(readOnly = true)
    List<APIKey> findAllKeys(String sort, String order, int max, int offset) {
        final keys = APIKey.list(sort: sort, order: order, max: max, offset: offset, fetch: [app: 'eager'], readOnly: true)
        return keys
    }

    def enableKey(String apikey, boolean enabled) {
        final result = APIKey.findByApikey(apikey)
        if (result) {
            result.enabled = enabled
            result.save()
        }
        return result
    }

    /**
     * Retrieve a key by apikey and set the last used timestamp and last remote addr properties
     * @param apikey The key to check
     * @param remoteAddr the remote address that is validating this key
     * @return They key
     */
    def validateKey(String apikey, String remoteAddr = null) {
        final result = APIKey.findByApikey(apikey)
        if (result) {
            result.lastUsed = new Date()
            result.lastRemoteAddr = remoteAddr
            result.save()
        }
        return result
    }
}
