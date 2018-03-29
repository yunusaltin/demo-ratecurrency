package be.coincurrency.controller;

import be.coincurrency.repository.RateRepository;
import be.coincurrency.model.Rate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RateController {
    private RateRepository rateRepository;

    public RateController(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Rate> allRates() {
        return rateRepository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping("/hello")
    @CrossOrigin(origins = "http://localhost:4200")
    public String hello() {

        Float.valueOf("0.1").floatValue();
        return "Hello World";
    }

    @GetMapping("/{code}")
    public String  getRateByCode(@PathVariable("code") String code)
    {
        Optional<Rate> foundRate = rateRepository.findByCodeContainingIgnoreCase(code);
        return foundRate
                .map(rate -> String.format("1 BTC = %s %s", rate.getRate(),rate.getName()) )
                .orElse(String.format("Couldn't find rate for %s", code));


    }

}
