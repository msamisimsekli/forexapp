package com.sami.forexapp.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sami.forexapp.entity.Conversion;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {

	Page<Conversion> findAllByConversionDateBetween(LocalDateTime beginDate, LocalDateTime endDate, Pageable paging);
}
