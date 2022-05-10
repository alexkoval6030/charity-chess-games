package by.kovalenko.mapper;

import by.kovalenko.dto.PaymentDto;
import by.kovalenko.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto paymentEntityToPaymentDto(PaymentEntity paymentEntity);
}
