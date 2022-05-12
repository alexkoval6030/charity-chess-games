package by.kovalenko.mapper;

import by.kovalenko.dto.PaymentDto;
import by.kovalenko.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "paymentDate", source = "paymentDate", dateFormat = "yyyy-MM-dd HH:mm")
    PaymentDto paymentEntityToPaymentDto(PaymentEntity paymentEntity);
}
