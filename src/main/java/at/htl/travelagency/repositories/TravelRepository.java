package at.htl.travelagency.repositories;

import at.htl.travelagency.entities.Travel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;
import java.util.List;

@ApplicationScoped
public class TravelRepository  extends EntityRepository<Travel>{

    public Travel findAdviceByDestination(String destination){
        return this.find("destination", destination).firstResult();
    }

    @Transactional
    public void resetTable() {
        this.deleteAll();
        this.getEntityManager()
                .createNativeQuery("ALTER TABLE db.public.travel ALTER COLUMN travelid RESTART WITH 1")
                .executeUpdate();
    }
}