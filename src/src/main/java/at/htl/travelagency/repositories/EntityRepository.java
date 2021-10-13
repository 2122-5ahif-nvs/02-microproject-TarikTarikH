package at.htl.travelagency.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class EntityRepository<T> implements PanacheRepository<T> {

    @Transactional
    public T saveEntity(T object){
        return this.getEntityManager().merge(object);
    }

    public T findEntity(Class<T> classType, Long id){
        return this.getEntityManager().find(classType, id);
    }

    @Transactional
    public T updateEntity(T newObject){
        return this.getEntityManager().merge(newObject);
    }

    @Transactional
    public void removeEntity(Long id){
        this.getEntityManager().remove(findById(id));
    }

    public List<T> findAllEntities(){
        return findAll().list();
    }

    @Transactional
    public List<T> saveEntities(List<T> objectList){
        List<T> objectsToSave = new ArrayList<>();

        objectList.forEach(o -> objectsToSave.add(this.saveEntity(o)));

        return objectsToSave;
    }
}
