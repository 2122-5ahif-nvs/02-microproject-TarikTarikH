package at.htl.travelagency;

import at.htl.travelagency.entities.Customer;
import at.htl.travelagency.entities.Employee;
import at.htl.travelagency.entities.Travel;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager em;

    @Transactional
    void onStart(@Observes StartupEvent event) {

        em.persist(new Travel("NewYork", new BigDecimal(274.99), LocalDate.of(2020, 12, 28)));
        em.persist(new Travel("Tokyo", new BigDecimal(200), LocalDate.of(2021, 3, 17)));
        em.persist(new Travel("London", new BigDecimal(153), LocalDate.of(2021, 1, 22)));

        em.persist(new Employee("Kevin", "Smith", LocalDate.of(2015, 1, 1)));
        em.persist(new Employee("Albert", "Simmons", LocalDate.of(2015, 3, 28)));
        em.persist(new Employee("Bob", "Bob", LocalDate.of(2018, 8, 17)));

        em.persist(new Customer( "Dave", "Bondwell"));
        em.persist(new Customer("Rick", "Clark"));
        em.persist(new Customer("John", "Doe"));
    }
}
