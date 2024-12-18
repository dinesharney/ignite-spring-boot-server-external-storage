package ignitecachestore.cachestore;

import ignitecachestore.context.SpringContext;
import ignitecachestore.entity.User;
import ignitecachestore.repository.UserRepository;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Ignite CacheStore implementation for handling User data persistence.
 */
@Configurable
@Component
public class UserCacheStore extends CacheStoreAdapter<Integer, User> implements Serializable {

    private JpaRepository<User,Long> getRepository() {
        return SpringContext.getBean(UserRepository.class);
    }

    @Override
    public User load(Integer key) {
        return getRepository().findById(Long.valueOf(key)).orElse(null);
    }

    @Override
    public void write(javax.cache.Cache.Entry<? extends Integer, ? extends User> entry) {
        getRepository().save(entry.getValue());
    }

    @Override
    public void delete(Object key) {
        getRepository().deleteById(Long.valueOf((Integer) key));
    }
}