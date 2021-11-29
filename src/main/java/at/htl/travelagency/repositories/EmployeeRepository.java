package at.htl.travelagency.repositories;

import at.htl.travelagency.dto.EmployeeDtoFirstName;
import at.htl.travelagency.entities.Advice;
import at.htl.travelagency.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.*;
import java.math.BigDecimal;

@ApplicationScoped
public class EmployeeRepository extends EntityRepository<Employee>{

    @Inject
    AdviceRepository adviceRepository;

    @Inject
    TravelRepository travelRepository;

    @Transactional
    public BigDecimal calculateSales(Long id){
        double sum = 0;

      var advices = adviceRepository.find("employee_id", id).list();
        for (Advice a : advices) {
            sum += travelRepository.find("travelid", a.getTravel().getTravelId()).singleResult()
                    .getPrice().doubleValue();
        }

        System.out.println(sum);
      return new BigDecimal(sum);
    }

    @Transactional
    public Employee patchEmployee(EmployeeDtoFirstName dto) {
        var emp = findById(dto.getId());

        emp.setFirstName(dto.getFirstName());

        return emp;
    }

    @Transactional
    public void resetTable() {
        this.deleteAll();
        this.getEntityManager()
                .createNativeQuery("ALTER TABLE db.public.employee ALTER COLUMN employeeid RESTART WITH 1")
                .executeUpdate();
    }
}