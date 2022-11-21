package com.eusosys.salelist.services;

import com.eusosys.salelist.entities.Sale;
import com.eusosys.salelist.repositories.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleService {
    private final SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }


    public Page<Sale> findAll(Pageable pageable) {
        return repository.findAll(pageable);

    }

    public Page<Sale> findByInterval(String start, String end, Pageable pageable) {
        LocalDate minDate = LocalDate.parse(start);
        LocalDate maxDate = LocalDate.parse(end);
        return repository.findAllByInterval(minDate, maxDate, pageable);
    }
}
