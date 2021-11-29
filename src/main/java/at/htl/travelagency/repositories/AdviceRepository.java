package at.htl.travelagency.repositories;

import at.htl.travelagency.entities.Advice;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AdviceRepository extends EntityRepository<Advice>{

    @Transactional
    public void resetTable() {
        this.deleteAll();
        this.getEntityManager()
                .createNativeQuery("ALTER TABLE db.public.advice ALTER COLUMN adviceid RESTART WITH 1")
                .executeUpdate();
    }
}
