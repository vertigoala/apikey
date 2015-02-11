dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = ""
    password = ""
    properties {
        // http://www.grails.org/doc/latest/guide/single.html#dataSource
        // see http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html#Common_Attributes for more
        timeBetweenEvictionRunsMillis = 60000 // milliseconds (default = 5000)
        testOnBorrow = true // default = false
        testOnReturn = false // default = false
        testWhileIdle = true // default = false
        validationQuery = "SELECT 1" // default = null
        validationQueryTimeout = 10 //seconds (default = -1 i.e. disabled)
        removeAbandoned = true // // default = false
        removeAbandonedTimeout = 300 // seconds (default = 60)
        logAbandoned = true // adds some overhead to every borrow from the pool, disable if it becomes a performance issue
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost:3306/apikey"

        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = ""
        }
    }
}
