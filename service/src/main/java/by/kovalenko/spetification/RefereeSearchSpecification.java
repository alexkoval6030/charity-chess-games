package by.kovalenko.spetification;

import by.kovalenko.dto.RefereeSearchAttributes;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.util.GameStatusName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RefereeSearchSpecification implements Specification<GameEntity> {
    private final RefereeSearchAttributes refereeSearchAttributes;

    @Override
    public Predicate toPredicate(Root<GameEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(root.get("gameStatus").get("gameStatusName"), GameStatusName.DURING));
        if (refereeSearchAttributes.getUsername() != null) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("creator").get("username"), "%" +
                            refereeSearchAttributes.getUsername() + "%"));
        }
        if (refereeSearchAttributes.getFromDate() != null && refereeSearchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.between(
                            root.get("gameStatus").get("date"),
                            refereeSearchAttributes.getFromDate(),
                            refereeSearchAttributes.getToDate()));
        }
        if (refereeSearchAttributes.getFromDate() != null && refereeSearchAttributes.getToDate() == null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("gameStatus").get("date"),
                            refereeSearchAttributes.getFromDate()));
        }
        if (refereeSearchAttributes.getFromDate() == null && refereeSearchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("gameStatus").get("date"),
                            refereeSearchAttributes.getToDate()));
        }

        if (refereeSearchAttributes.getMinimum() != null && refereeSearchAttributes.getMaximum() != null) {
            predicates.add(
                    criteriaBuilder.between(
                            root.get("creatorStake").get("stake"),
                            refereeSearchAttributes.getMinimum(),
                            refereeSearchAttributes.getMaximum()));
        }
        if (refereeSearchAttributes.getMinimum() != null && refereeSearchAttributes.getMaximum() == null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("creatorStake").get("stake"),
                            refereeSearchAttributes.getMinimum()));
        }
        if (refereeSearchAttributes.getMinimum() == null && refereeSearchAttributes.getMaximum() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("creatorStake").get("stake"),
                            refereeSearchAttributes.getMaximum()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
