package be.coincurrency.services;

import be.coincurrency.model.Rate;
import be.coincurrency.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateService {

    private RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }


    public Optional<Rate> getRateByCode(String code) {
        return rateRepository.findByCodeContainingIgnoreCase(code);
    }

    public Collection<Rate> getRates() {
        return rateRepository.findAll().stream().collect(Collectors.toList());
    }

}
