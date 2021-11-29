package at.htl.travelagency.repositories;

import at.htl.travelagency.entities.Customer;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomerRepository extends EntityRepository<Customer> {

    @Transactional
    public void resetTable() {
        this.deleteAll();
        this.getEntityManager().createNativeQuery("ALTER TABLE db.public.customer ALTER COLUMN customerId RESTART WITH 1")
                .executeUpdate();
    }
}
