package by.kovalenko.service.impl;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.RefereeSearchAttributes;
import by.kovalenko.entity.GameEntity;
import by.kovalenko.mapper.GameMapper;
import by.kovalenko.repositories.GameRepository;
import by.kovalenko.service.RefereeService;
import by.kovalenko.spetification.RefereeSearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Override
    public RefereeSearchSpecification getSpecification(RefereeSearchAttributes searchAttributes) {
        return new RefereeSearchSpecification(searchAttributes);
    }

    @Override
    public Page<GameDto> findAll(RefereeSearchSpecification refereeSearchSpecification, Pageable pageable) {
        Page<GameEntity> gameEntityPage = gameRepository.findAll(refereeSearchSpecification, pageable);
        return gameEntityPage.map(gameMapper::gameEntityToGameDto);
    }
}
