package ru.vsu.cs.zmaev.carpartsservice.repository.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCategoryCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CarPartCategoryCriteriaRepository extends AbstractCriteriaRepository<CarPartCategory, CarPartCategoryCriteriaSearch> {
    protected CarPartCategoryCriteriaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<CarPartCategory> getEntityClass() {
        return CarPartCategory.class;
    }

    @Override
    protected Predicate getPredicate(CarPartCategoryCriteriaSearch searchCriteria, Root<CarPartCategory> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getCategoryName())) {
            predicates.add(criteriaBuilder.like(root.get("categoryName"), "%" + searchCriteria.getCategoryName() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected long getCount() {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CarPartCategory> countRoot = countQuery.from(CarPartCategory.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
