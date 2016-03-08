package com.redhat.consulting.fusequickstars.eap.jpa.route;

import com.redhat.consulting.fusequickstars.eap.jpa.model.User;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jpa.JpaEndpoint;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *  @author lberetta
 */

@ContextName("jpa")
public class JpaRoutes extends RouteBuilder {

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction userTransaction;

    @Override
    public void configure() throws Exception {
        EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();

        // Configure a JtaTransactionManager
        JtaTransactionManager transactionManager = new JtaTransactionManager(userTransaction);
        transactionManager.afterPropertiesSet();

        // Configure the JPA endpoint
        JpaEndpoint jpaEndpoint = new JpaEndpoint();
        jpaEndpoint.setCamelContext(getContext());
        jpaEndpoint.setEntityType(User.class);
        jpaEndpoint.setEntityManagerFactory(entityManagerFactory);
        jpaEndpoint.setTransactionManager(transactionManager);

        from("timer://timeToPersist?fixedRate=true&period=5000")
                .setBody(constant(new User("leandro")))
                .log("Inserting new user")
                .to(jpaEndpoint);

        from(jpaEndpoint)
                .log("Retrieving user: ${body}");
    }
}
