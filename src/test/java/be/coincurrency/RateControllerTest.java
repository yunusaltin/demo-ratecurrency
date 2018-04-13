package be.coincurrency;


import be.coincurrency.controller.RateController;
import be.coincurrency.model.Rate;
import be.coincurrency.repository.RateRepository;
import be.coincurrency.services.RateService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

public class RateControllerTest {

    private RateController subject;

    @Mock
    private RateService rateService;


    @BeforeEach
    public void setUp() throws Exception {
        initMocks(this);
        subject = new RateController(rateService);
    }

    @Test
    public void shouldReturnCurrency() throws Exception {
        Rate rate = new Rate("Euro", "EUR", 1.0);
        given(rateService.getRateByCode("EUR")).willReturn(Optional.of(rate));

        String conversion = subject.getRateByCode("EUR");

        assertThat(conversion, is("1 BTC = 1.0 Euro"));
    }

    @Test
    public void shouldTellIfRateIsUnknown() throws Exception {
        given(rateService.getRateByCode(anyString())).willReturn(Optional.empty());

        String conversion = subject.getRateByCode("EUR");

        assertThat(conversion, is("Couldn't find rate for EUR"));
    }
}
