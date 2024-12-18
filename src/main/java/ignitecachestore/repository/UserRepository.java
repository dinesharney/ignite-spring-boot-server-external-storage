package ignitecachestore.repository;

import ignitecachestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for accessing User data from the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
