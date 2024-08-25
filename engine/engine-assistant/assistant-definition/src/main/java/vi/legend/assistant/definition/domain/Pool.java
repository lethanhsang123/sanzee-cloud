package vi.legend.assistant.definition.domain;

import com.google.common.base.MoreObjects;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;

import java.time.Duration;

/**
 * Lớp Pool chứa cấu hình cho một bể kết nối (connection pool), bao gồm các tham số như số lượng tối đa,
 * số lượng tối thiểu của kết nối nhàn rỗi, thời gian chờ tối đa, và các cấu hình khác liên quan đến việc
 * quản lý kết nối trong bể.
 */
public class Pool {
    private Integer maxTotal = 8; // Số lượng kết nối tối đa trong bể.
    private Integer maxIdle = 8; // Số lượng kết nối nhàn rỗi tối đa trong bể.
    private Integer minIdle = 0; // Số lượng kết nối nhàn rỗi tối thiểu trong bể.
    private Boolean lifo = true; // Sử dụng chính sách LIFO (Last In First Out) cho các kết nối nhàn rỗi.
    private Duration maxWait; // Thời gian chờ tối đa để lấy một kết nối từ bể.
    private Boolean blockWhenExhausted; // Cho biết liệu có chặn yêu cầu khi bể cạn kết nối hay không.
    private Duration minEvictableIdleDuration; // Thời gian tối thiểu mà một kết nối nhàn rỗi có thể bị loại bỏ.
    private Duration softMinEvictableIdleDuration; // Thời gian mềm tối thiểu mà một kết nối nhàn rỗi có thể bị loại bỏ.

    /**
     * Constructor mặc định của lớp Pool, thiết lập các giá trị mặc định cho một số tham số.
     */
    public Pool() {
        this.maxWait = BaseObjectPoolConfig.DEFAULT_MAX_WAIT;
        this.blockWhenExhausted = true;
        this.minEvictableIdleDuration = BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_DURATION;
        this.softMinEvictableIdleDuration = BaseObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION;
    }

    /**
     * Lấy số lượng kết nối tối đa trong bể.
     *
     * @return Số lượng kết nối tối đa.
     */
    public Integer getMaxTotal() {
        return this.maxTotal;
    }

    /**
     * Thiết lập số lượng kết nối tối đa trong bể.
     *
     * @param maxTotal Số lượng kết nối tối đa cần thiết lập.
     */
    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    /**
     * Lấy số lượng kết nối nhàn rỗi tối đa trong bể.
     *
     * @return Số lượng kết nối nhàn rỗi tối đa.
     */
    public Integer getMaxIdle() {
        return this.maxIdle;
    }

    /**
     * Thiết lập số lượng kết nối nhàn rỗi tối đa trong bể.
     *
     * @param maxIdle Số lượng kết nối nhàn rỗi tối đa cần thiết lập.
     */
    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    /**
     * Lấy số lượng kết nối nhàn rỗi tối thiểu trong bể.
     *
     * @return Số lượng kết nối nhàn rỗi tối thiểu.
     */
    public Integer getMinIdle() {
        return this.minIdle;
    }

    /**
     * Thiết lập số lượng kết nối nhàn rỗi tối thiểu trong bể.
     *
     * @param minIdle Số lượng kết nối nhàn rỗi tối thiểu cần thiết lập.
     */
    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    /**
     * Lấy giá trị của thuộc tính LIFO (Last In First Out).
     *
     * @return Giá trị của thuộc tính LIFO.
     */
    public Boolean getLifo() {
        return this.lifo;
    }

    /**
     * Thiết lập giá trị cho thuộc tính LIFO (Last In First Out).
     *
     * @param lifo Giá trị LIFO cần thiết lập.
     */
    public void setLifo(Boolean lifo) {
        this.lifo = lifo;
    }

    /**
     * Lấy thời gian chờ tối đa để lấy một kết nối từ bể.
     *
     * @return Thời gian chờ tối đa.
     */
    public Duration getMaxWait() {
        return this.maxWait;
    }

    /**
     * Thiết lập thời gian chờ tối đa để lấy một kết nối từ bể.
     *
     * @param maxWait Thời gian chờ tối đa cần thiết lập.
     */
    public void setMaxWait(Duration maxWait) {
        this.maxWait = maxWait;
    }

    /**
     * Lấy giá trị của thuộc tính blockWhenExhausted (chặn khi bể cạn kết nối).
     *
     * @return Giá trị của thuộc tính blockWhenExhausted.
     */
    public Boolean getBlockWhenExhausted() {
        return this.blockWhenExhausted;
    }

    /**
     * Thiết lập giá trị cho thuộc tính blockWhenExhausted (chặn khi bể cạn kết nối).
     *
     * @param blockWhenExhausted Giá trị blockWhenExhausted cần thiết lập.
     */
    public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    /**
     * Lấy thời gian tối thiểu mà một kết nối nhàn rỗi có thể bị loại bỏ.
     *
     * @return Thời gian tối thiểu để loại bỏ kết nối nhàn rỗi.
     */
    public Duration getMinEvictableIdleDuration() {
        return this.minEvictableIdleDuration;
    }

    /**
     * Thiết lập thời gian tối thiểu mà một kết nối nhàn rỗi có thể bị loại bỏ.
     *
     * @param minEvictableIdleDuration Thời gian tối thiểu cần thiết lập.
     */
    public void setMinEvictableIdleDuration(Duration minEvictableIdleDuration) {
        this.minEvictableIdleDuration = minEvictableIdleDuration;
    }

    /**
     * Lấy thời gian mềm tối thiểu mà một kết nối nhàn rỗi có thể bị loại bỏ.
     *
     * @return Thời gian mềm tối thiểu để loại bỏ kết nối nhàn rỗi.
     */
    public Duration getSoftMinEvictableIdleDuration() {
        return this.softMinEvictableIdleDuration;
    }

    /**
     * Thiết lập thời gian mềm tối thiểu mà một kết nối nhàn rỗi có thể bị loại bỏ.
     *
     * @param softMinEvictableIdleDuration Thời gian mềm tối thiểu cần thiết lập.
     */
    public void setSoftMinEvictableIdleDuration(Duration softMinEvictableIdleDuration) {
        this.softMinEvictableIdleDuration = softMinEvictableIdleDuration;
    }

    /**
     * Phương thức toString cung cấp chuỗi mô tả cho các thuộc tính của đối tượng Pool.
     *
     * @return Chuỗi mô tả các thuộc tính của Pool.
     */
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("maxTotal", this.maxTotal)
                .add("maxIdle", this.maxIdle)
                .add("minIdle", this.minIdle)
                .add("lifo", this.lifo)
                .add("maxWait", this.maxWait)
                .add("blockWhenExhausted", this.blockWhenExhausted)
                .add("minEvictableIdleTime", this.minEvictableIdleDuration)
                .add("softMinEvictableIdleTime", this.softMinEvictableIdleDuration)
                .toString();
    }
}

