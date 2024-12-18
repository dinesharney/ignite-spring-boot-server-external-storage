package ignitecachestore.repository;

import ignitecachestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for accessing Product data from the database.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
