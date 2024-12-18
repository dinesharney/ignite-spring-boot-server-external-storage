package ignitecachestore.factory;

import javax.cache.configuration.Factory;
import java.io.Serializable;

public class CacheStoreFactory<T> implements Factory<T>, Serializable {
    public static final long serialVersionUID = 201305101634L;
    private T instance;

    public CacheStoreFactory(T instance) {
        this.instance = instance;
    }

    public T create() {
        return this.instance;
    }

}
