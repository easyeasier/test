package wz.test.jdk.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.atomic.AtomicInteger;

public class UserFactory implements PooledObjectFactory<User> {
    static AtomicInteger id = new AtomicInteger(1);

    @Override
    public PooledObject<User> makeObject() throws Exception {
        return new DefaultPooledObject<>(new User("", true, id.getAndIncrement()));
    }

    @Override
    public void destroyObject(PooledObject<User> p) throws Exception {
        User user = p.getObject();
        p = null;
    }

    @Override
    public boolean validateObject(PooledObject<User> p) {
        return p.getObject().isActive;
    }

    @Override
    public void activateObject(PooledObject<User> p) throws Exception {
        p.getObject().setActive(true);
    }

    @Override
    public void passivateObject(PooledObject<User> p) throws Exception {
        p.getObject().setActive(false);
    }
}
