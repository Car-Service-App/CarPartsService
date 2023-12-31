package ru.vsu.cs.zmaev.carpartsservice.repository.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartAnalogCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartAnalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CarPartAnalogCriteriaRepository extends AbstractCriteriaRepository<CarPartAnalog, CarPartAnalogCriteriaSearch> {

    protected CarPartAnalogCriteriaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<CarPartAnalog> getEntityClass() {
        return CarPartAnalog.class;
    }

    @Override
    protected Predicate getPredicate(CarPartAnalogCriteriaSearch searchCriteria, Root<CarPartAnalog> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getAnalogOem())) {
            predicates.add(criteriaBuilder.like(root.get("analogOem"), "%" + searchCriteria.getAnalogOem() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getOriginalOem())) {
            Join<CarPartAnalog, CarPart> customJoin = root.join("oem", JoinType.INNER);
            predicates.add(criteriaBuilder.like(
                    customJoin.get("oem"), "%" + searchCriteria.getOriginalOem() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getPrice())) {
            predicates.add(criteriaBuilder.like(root.get("price"), "%" + searchCriteria.getAnalogOem() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected long getCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CarPartAnalog> countRoot = countQuery.from(CarPartAnalog.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
