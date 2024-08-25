package vi.legend.assistant.definition.support;

import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vi.legend.assistant.definition.domain.Pool;
import vi.legend.assistant.definition.exception.BorrowObjectFromPoolErrorException;

/**
 * Lớp trừu tượng AbstractObjectPool đại diện cho một bể đối tượng tổng quát (generic object pool)
 * để quản lý các đối tượng loại T, sử dụng cấu hình được cung cấp từ lớp Pool.
 *
 * @param <T> Loại đối tượng mà bể sẽ quản lý.
 */
public abstract class AbstractObjectPool<T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractObjectPool.class); // Logger để ghi log.
    private final GenericObjectPool<T> genericObjectPool; // Bể đối tượng tổng quát sử dụng thư viện Apache Commons Pool.

    /**
     * Constructor được bảo vệ để khởi tạo AbstractObjectPool với một PooledObjectFactory và cấu hình Pool.
     *
     * @param pooledObjectFactory Nhà máy tạo đối tượng được quản lý bởi bể.
     * @param pool Cấu hình cho bể đối tượng.
     */
    protected AbstractObjectPool(@Nonnull PooledObjectFactory<T> pooledObjectFactory, @Nonnull Pool pool) {
        GenericObjectPoolConfig<T> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(pool.getMaxTotal());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        config.setMaxWait(pool.getMaxWait());
        config.setMinEvictableIdleDuration(pool.getMinEvictableIdleDuration());
        config.setSoftMinEvictableIdleDuration(pool.getSoftMinEvictableIdleDuration());
        config.setLifo(pool.getLifo());
        config.setBlockWhenExhausted(pool.getBlockWhenExhausted());
        this.genericObjectPool = new GenericObjectPool<>(pooledObjectFactory, config);
    }

    /**
     * Lấy một đối tượng từ bể.
     *
     * @return Đối tượng loại T từ bể.
     * @throws BorrowObjectFromPoolErrorException nếu không thể lấy đối tượng từ bể.
     */
    public T get() {
        try {
            T object = this.genericObjectPool.borrowObject();
            log.debug("[Herodotus] |- Lấy đối tượng từ bể đối tượng.");
            return object;
        } catch (Exception var2) {
            log.error("[Herodotus] |- Không thể lấy đối tượng từ bể.", var2);
            throw new BorrowObjectFromPoolErrorException("Không thể lấy đối tượng từ bể.");
        }
    }

    /**
     * Đóng đối tượng và trả lại nó vào bể.
     *
     * @param client Đối tượng cần trả lại vào bể.
     */
    public void close(T client) {
        if (ObjectUtils.isNotEmpty(client)) {
            log.debug("[Herodotus] |- Đóng đối tượng và trả lại vào bể.");
            this.genericObjectPool.returnObject(client);
        }
    }
}

