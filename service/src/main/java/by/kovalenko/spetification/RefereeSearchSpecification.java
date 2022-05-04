package by.kovalenko.spetification;

import by.kovalenko.dto.SearchAttributes;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.util.GameStatusName;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RefereeSearchSpecification implements Specification<GameEntity> {
    private final SearchAttributes searchAttributes;

    public RefereeSearchSpecification(SearchAttributes searchAttributes) {
        this.searchAttributes = searchAttributes;
    }

    @Override
    public Predicate toPredicate(Root<GameEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(root.get("gameStatus").get("gameStatusName"), GameStatusName.DURING));
        if (searchAttributes.getCreatorUsername() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("creator").get("username"),
                            searchAttributes.getCreatorUsername()));
        }
        if (searchAttributes.getFromDate() != null && searchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.between(
                            root.get("gameStatus").get("date"),
                            searchAttributes.getFromDate(),
                            searchAttributes.getToDate()));
        }
        if (searchAttributes.getFromDate() != null && searchAttributes.getToDate() == null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("gameStatus").get("date"),
                            searchAttributes.getFromDate()));
        }
        if (searchAttributes.getFromDate() == null && searchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("gameStatus").get("date"),
                            searchAttributes.getToDate()));
        }

        if (searchAttributes.getMinimum() != null && searchAttributes.getMaximum() != null) {
            predicates.add(
                    criteriaBuilder.between(
                            root.get("stakes"),
                            searchAttributes.getMinimum(),
                            searchAttributes.getMaximum()));
        }
        if (searchAttributes.getMinimum() != null && searchAttributes.getMaximum() == null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("creatorStake"),
                            searchAttributes.getMinimum()));
        }
        if (searchAttributes.getFromDate() == null && searchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("creator").get("stake"),
                            searchAttributes.getMaximum()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
