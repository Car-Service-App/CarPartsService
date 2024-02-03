package ru.vsu.cs.zmaev.carpartsservice.repository.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchAttributeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class CarPartCriteriaRepository extends AbstractCriteriaRepository<CarPart, CarPartCriteriaSearch> {

    protected CarPartCriteriaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<CarPart> getEntityClass() {
        return CarPart.class;
    }

    @Override
    protected Predicate getPredicate(CarPartCriteriaSearch searchCriteria, Root<CarPart> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getManufacturerId())) {
            predicates.add(criteriaBuilder.like(root.get("manufacturerId"), "%" + searchCriteria.getManufacturerId() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getName())) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchCriteria.getName() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getOem())) {
            predicates.add(criteriaBuilder.like(root.get("oem"), "%" + searchCriteria.getOem() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getPrice())) {
            predicates.add(criteriaBuilder.like(root.get("price"), "%" + searchCriteria.getPrice() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getCarPartType())) {
            try {
                PartType.valueOf(searchCriteria.getCarPartType());
            } catch (IllegalArgumentException e) {
                throw new NoSuchAttributeException(searchCriteria.getCarPartType());
            }
            predicates.add(
                    criteriaBuilder.equal(root.get("carPartType"), PartType.valueOf(searchCriteria.getCarPartType())
                    ));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected long getCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CarPart> countRoot = countQuery.from(CarPart.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
