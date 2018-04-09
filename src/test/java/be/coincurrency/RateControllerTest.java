package be.coincurrency;


import be.coincurrency.controller.RateController;
import be.coincurrency.model.Rate;
import be.coincurrency.repository.RateRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.junit.Assert.assertThat;

public class RateControllerTest {

    private RateController subject;

    @Mock
    private RateRepository rateRepository;


    @BeforeEach
    public void setUp() throws Exception {
        initMocks(this);
        subject = new RateController(rateRepository);
    }

    @Test
    public void shouldReturnCurrency() throws Exception {
        Rate rate = new Rate("Euro", "EUR", 1.0);
        given(rateRepository.findByCodeContainingIgnoreCase("EUR")).willReturn(Optional.of(rate));

        String conversion = subject.getRateByCode("EUR");

        assertThat(conversion, is("1 BTC = 1.0 Euro"));
    }

    @Test
    public void shouldTellIfRateIsUnknown() throws Exception {
        given(rateRepository.findByCodeContainingIgnoreCase(anyString())).willReturn(Optional.empty());

        String conversion = subject.getRateByCode("EUR");

        assertThat(conversion, is("Couldn't find rate for EUR"));
    }
}
