package be.coincurrency;

import be.coincurrency.model.Rate;
import be.coincurrency.repository.RateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class RateRepositoryIntegrationTest {

    @Autowired
    private RateRepository subject;

    @AfterEach
    public void tearDown() throws Exception {
        subject.deleteAll();
    }


    @Test
    public void shouldSaveAndFetchRate() throws Exception {
        Rate rate = new Rate("euro","EUR",1.0);
        subject.save(rate);

        Optional<Rate> maybeRate = subject.findByCodeContainingIgnoreCase("EUR");

        assertThat(maybeRate,is(Optional.of(rate)));
    }
}
