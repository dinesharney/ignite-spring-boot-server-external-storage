package ignitecachestore.repository;

import ignitecachestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for accessing Order data from the database.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

