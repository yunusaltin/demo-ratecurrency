package be.coincurrency.services;

import be.coincurrency.config.ApplicationConfiguration;
import be.coincurrency.repository.RateRepository;
import be.coincurrency.model.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RateLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RateLoader.class);

    private RestTemplate restTemplate;
    private ApplicationConfiguration applicationConfiguration;
    private RateRepository rateRepository;

    @Autowired
    public RateLoader(RestTemplate restTemplate, ApplicationConfiguration applicationConfiguration, RateRepository rateRepository) {
        this.restTemplate = restTemplate;
        this.applicationConfiguration = applicationConfiguration;
        this.rateRepository = rateRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Rate> rates = loadFromAPI();
        log.info(rates.toString());
        saveRates(rates);
    }


    private List<Rate> loadFromAPI()
    {
        ResponseEntity<List<Rate>> rateResponse =
                restTemplate.exchange(applicationConfiguration.getUrl(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
                        });
        return rateResponse.getBody();
    }

    private void saveRates(List<Rate> rates)
    {
        rates.stream().forEach(rate -> rateRepository.save(rate));
    }

}
