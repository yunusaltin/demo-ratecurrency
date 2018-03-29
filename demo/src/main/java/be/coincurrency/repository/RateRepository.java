package be.coincurrency.repository;

import be.coincurrency.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RateRepository extends JpaRepository<Rate,Long> {
    Optional<Rate> findByCodeContainingIgnoreCase(String code);
}
