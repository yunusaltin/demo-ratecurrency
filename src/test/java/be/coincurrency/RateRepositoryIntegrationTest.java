package be.coincurrency;

import be.coincurrency.model.Rate;
import be.coincurrency.repository.RateRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

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
