package be.coincurrency.controller;

import be.coincurrency.repository.RateRepository;
import be.coincurrency.model.Rate;
import be.coincurrency.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RateController {
    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Rate> allRates() {
        return rateService.getRates();
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
        Optional<Rate> foundRate = rateService.getRateByCode(code);
        return foundRate
                .map(rate -> String.format("1 BTC = %s %s", rate.getRate(),rate.getName()) )
                .orElse(String.format("Couldn't find rate for %s", code));


    }

}
