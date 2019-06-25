import org.springframework.beans.factory.config.BeanDefinition

// Place your Spring DSL code here
beans = {
    if (application.config.flyway.enabled) {

        BeanDefinition hibernateDatastore = getBeanDefinition('hibernateDatastore')
        if (hibernateDatastore) {
            def dependsOnList = ['flywayInitializer'] as Set
            if (hibernateDatastore.dependsOn?.length > 0) {
                dependsOnList.addAll(hibernateDatastore.dependsOn)
            }
            hibernateDatastore.dependsOn = dependsOnList as String[]
        }

        BeanDefinition sessionFactoryBeanDef = getBeanDefinition('sessionFactory')

        if (sessionFactoryBeanDef) {
            def dependsOnList = ['flywayInitializer'] as Set
            if (sessionFactoryBeanDef.dependsOn?.length > 0) {
                dependsOnList.addAll(sessionFactoryBeanDef.dependsOn)
            }
            sessionFactoryBeanDef.dependsOn = dependsOnList as String[]
        }
    }
    else {
        log.info "Grails Flyway plugin has been disabled"
    }
}
