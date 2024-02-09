package ru.vsu.cs.zmaev.carpartsservice.repository.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartTypeCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchAttributeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CarPartTypeCriteriaRepository extends AbstractCriteriaRepository<CarPartType, CarPartTypeCriteriaSearch> {

    protected CarPartTypeCriteriaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<CarPartType> getEntityClass() {
        return CarPartType.class;
    }

    @Override
    protected Predicate getPredicate(CarPartTypeCriteriaSearch searchCriteria, Root<CarPartType> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getPartType()) && !searchCriteria.getPartType().isEmpty()) {
            try {
                PartType.valueOf(searchCriteria.getPartType());
            } catch (IllegalArgumentException e) {
                throw new NoSuchAttributeException(searchCriteria.getPartType());
            }
            predicates.add(
                    criteriaBuilder.equal(root.get("partType"),
                            PartType.valueOf(searchCriteria.getPartType())
            ));
        }
        if (Objects.nonNull(searchCriteria.getCarPartCategory())) {
            Join<CarPartType, CarPartCategory> customJoin = root.join("carPartCategory", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(
                    customJoin.get("categoryName"), "%" + searchCriteria.getCarPartCategory() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected long getCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CarPartType> countRoot = countQuery.from(CarPartType.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
