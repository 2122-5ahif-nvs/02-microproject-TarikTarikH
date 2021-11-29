package at.htl.travelagency.repositories;

import at.htl.travelagency.entities.Booking;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class BookingRepository extends EntityRepository<Booking>{

    @Transactional
    public void resetTable() {
        this.deleteAll();
        this.getEntityManager()
                .createNativeQuery("ALTER TABLE db.public.booking ALTER COLUMN bookingid RESTART WITH 1")
                .executeUpdate();
    }
}