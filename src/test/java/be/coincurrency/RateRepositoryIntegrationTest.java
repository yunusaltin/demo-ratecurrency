package be.coincurrency;

import be.coincurrency.model.Rate;
import be.coincurrency.repository.RateRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RateRepositoryIntegrationTest {

    @Autowired
    private RateRepository subject;

    @After
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
