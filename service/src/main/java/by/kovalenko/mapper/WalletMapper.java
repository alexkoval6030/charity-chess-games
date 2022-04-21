package by.kovalenko.mapper;

import by.kovalenko.dto.WalletDto;
import by.kovalenko.entity.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto walletEntityToWalletDto(WalletEntity walletEntity);

    WalletEntity walletDtoToWalletEntity(WalletDto walletDto);
}
