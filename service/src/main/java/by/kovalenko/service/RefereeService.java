package by.kovalenko.service;

import by.kovalenko.dto.GameDto;
import by.kovalenko.dto.SearchAttributes;
import by.kovalenko.spetification.RefereeSearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RefereeService {
    RefereeSearchSpecification getSpecification(SearchAttributes searchAttributes);

    Page<GameDto> findAll(RefereeSearchSpecification refereeSearchSpecification, Pageable pageable);
}
