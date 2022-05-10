package by.kovalenko.spetification;

import by.kovalenko.dto.PaymentSearchAttributes;
import by.kovalenko.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PaymentSearchSpecification implements Specification<PaymentEntity> {
    private final PaymentSearchAttributes paymentSearchAttributes;

    @Override
    public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(root.get("user").get("username"), paymentSearchAttributes.getUsername()));
        if (paymentSearchAttributes.getPaymentTransactionType() != null){
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("paymentTransactionType"),
                            paymentSearchAttributes.getPaymentTransactionType()));
        }
        if (paymentSearchAttributes.getFromDate() != null && paymentSearchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.between(
                            root.get("paymentDate"),
                            paymentSearchAttributes.getFromDate(),
                            paymentSearchAttributes.getToDate()));
        }
        if (paymentSearchAttributes.getFromDate() != null && paymentSearchAttributes.getToDate() == null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("paymentDate"),
                            paymentSearchAttributes.getFromDate()));
        }
        if (paymentSearchAttributes.getFromDate() == null && paymentSearchAttributes.getToDate() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("paymentDate"),
                            paymentSearchAttributes.getToDate()));
        }

        if (paymentSearchAttributes.getMinimum() != null && paymentSearchAttributes.getMaximum() != null) {
            predicates.add(
                    criteriaBuilder.between(
                            root.get("paymentSum"),
                            paymentSearchAttributes.getMinimum(),
                            paymentSearchAttributes.getMaximum()));
        }
        if (paymentSearchAttributes.getMinimum() != null && paymentSearchAttributes.getMaximum() == null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("paymentSum"),
                            paymentSearchAttributes.getMinimum()));
        }
        if (paymentSearchAttributes.getMinimum() == null && paymentSearchAttributes.getMaximum() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("paymentSum"),
                            paymentSearchAttributes.getMaximum()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
