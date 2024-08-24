package vi.legend.assistant.core.context;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;

/**
 * class PropertyResolver cung cấp các phương thức tiện ích để lấy giá trị của các thuộc tính cấu hình
 * từ đối tượng Environment hoặc ConditionContext trong Spring Framework.
 */
public class PropertyResolver {

    /**
     * Constructor mặc định của class PropertyResolver.
     */
    public PropertyResolver() {
    }

    /**
     * Lấy giá trị của thuộc tính từ Environment.
     *
     * @param environment Đối tượng Environment chứa các thuộc tính.
     * @param property    Tên của thuộc tính cần lấy.
     * @return Giá trị của thuộc tính, hoặc null nếu không tìm thấy.
     */
    public static String getProperty(Environment environment, String property) {
        return environment.getProperty(property);
    }

    /**
     * Lấy giá trị của thuộc tính từ Environment với giá trị mặc định nếu thuộc tính không tồn tại.
     *
     * @param environment  Đối tượng Environment chứa các thuộc tính.
     * @param property     Tên của thuộc tính cần lấy.
     * @param defaultValue Giá trị mặc định trả về nếu thuộc tính không tồn tại.
     * @return Giá trị của thuộc tính, hoặc defaultValue nếu không tìm thấy.
     */
    public static String getProperty(Environment environment, String property, String defaultValue) {
        return environment.getProperty(property, defaultValue);
    }

    /**
     * Lấy giá trị của thuộc tính từ ConditionContext.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần lấy.
     * @return Giá trị của thuộc tính, hoặc null nếu không tìm thấy.
     */
    public static String getProperty(ConditionContext conditionContext, String property) {
        return getProperty(conditionContext.getEnvironment(), property);
    }

    /**
     * Lấy giá trị của thuộc tính từ ConditionContext với giá trị mặc định nếu thuộc tính không tồn tại.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần lấy.
     * @param defaultValue     Giá trị mặc định trả về nếu thuộc tính không tồn tại.
     * @return Giá trị của thuộc tính, hoặc defaultValue nếu không tìm thấy.
     */
    public static String getProperty(ConditionContext conditionContext, String property, String defaultValue) {
        return getProperty(conditionContext.getEnvironment(), property, defaultValue);
    }

    /**
     * Lấy giá trị của thuộc tính từ Environment và chuyển đổi sang kiểu dữ liệu cụ thể.
     *
     * @param environment Đối tượng Environment chứa các thuộc tính.
     * @param property    Tên của thuộc tính cần lấy.
     * @param targetType  Kiểu dữ liệu đích của thuộc tính.
     * @param <T>         Kiểu dữ liệu đích.
     * @return Giá trị của thuộc tính, hoặc null nếu không tìm thấy.
     */
    public static <T> T getProperty(Environment environment, String property, Class<T> targetType) {
        return environment.getProperty(property, targetType);
    }

    /**
     * Lấy giá trị của thuộc tính từ Environment với giá trị mặc định nếu thuộc tính không tồn tại,
     * và chuyển đổi sang kiểu dữ liệu cụ thể.
     *
     * @param environment  Đối tượng Environment chứa các thuộc tính.
     * @param property     Tên của thuộc tính cần lấy.
     * @param targetType   Kiểu dữ liệu đích của thuộc tính.
     * @param defaultValue Giá trị mặc định trả về nếu thuộc tính không tồn tại.
     * @param <T>          Kiểu dữ liệu đích.
     * @return Giá trị của thuộc tính, hoặc defaultValue nếu không tìm thấy.
     */
    public static <T> T getProperty(Environment environment, String property, Class<T> targetType, T defaultValue) {
        return environment.getProperty(property, targetType, defaultValue);
    }

    /**
     * Lấy giá trị của thuộc tính từ ConditionContext và chuyển đổi sang kiểu dữ liệu cụ thể.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần lấy.
     * @param targetType       Kiểu dữ liệu đích của thuộc tính.
     * @param <T>              Kiểu dữ liệu đích.
     * @return Giá trị của thuộc tính, hoặc null nếu không tìm thấy.
     */
    public static <T> T getProperty(ConditionContext conditionContext, String property, Class<T> targetType) {
        return getProperty(conditionContext.getEnvironment(), property, targetType);
    }

    /**
     * Lấy giá trị của thuộc tính từ ConditionContext với giá trị mặc định nếu thuộc tính không tồn tại,
     * và chuyển đổi sang kiểu dữ liệu cụ thể.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần lấy.
     * @param targetType       Kiểu dữ liệu đích của thuộc tính.
     * @param defaultValue     Giá trị mặc định trả về nếu thuộc tính không tồn tại.
     * @param <T>              Kiểu dữ liệu đích.
     * @return Giá trị của thuộc tính, hoặc defaultValue nếu không tìm thấy.
     */
    public static <T> T getProperty(ConditionContext conditionContext, String property, Class<T> targetType, T defaultValue) {
        return getProperty(conditionContext.getEnvironment(), property, targetType, defaultValue);
    }

    /**
     * Kiểm tra xem thuộc tính có tồn tại trong Environment hay không.
     *
     * @param environment Đối tượng Environment chứa các thuộc tính.
     * @param property    Tên của thuộc tính cần kiểm tra.
     * @return true nếu thuộc tính tồn tại, false nếu không.
     */
    public static boolean contains(Environment environment, String property) {
        return environment.containsProperty(property);
    }

    /**
     * Kiểm tra xem thuộc tính có tồn tại trong ConditionContext hay không.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần kiểm tra.
     * @return true nếu thuộc tính tồn tại, false nếu không.
     */
    public static boolean contains(ConditionContext conditionContext, String property) {
        return contains(conditionContext.getEnvironment(), property);
    }

    /**
     * Lấy giá trị của thuộc tính dưới dạng boolean từ Environment với giá trị mặc định.
     *
     * @param environment  Đối tượng Environment chứa các thuộc tính.
     * @param property     Tên của thuộc tính cần lấy.
     * @param defaultValue Giá trị mặc định trả về nếu thuộc tính không tồn tại.
     * @return Giá trị boolean của thuộc tính, hoặc defaultValue nếu không tìm thấy.
     */
    public static boolean getBoolean(Environment environment, String property, boolean defaultValue) {
        return (Boolean)getProperty((Environment)environment, property, Boolean.class, defaultValue);
    }

    /**
     * Lấy giá trị của thuộc tính dưới dạng boolean từ Environment.
     *
     * @param environment Đối tượng Environment chứa các thuộc tính.
     * @param property    Tên của thuộc tính cần lấy.
     * @return Giá trị boolean của thuộc tính, hoặc false nếu không tìm thấy.
     */
    public static boolean getBoolean(Environment environment, String property) {
        return (Boolean)getProperty((Environment)environment, property, Boolean.class, false);
    }

    /**
     * Lấy giá trị của thuộc tính dưới dạng boolean từ ConditionContext.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần lấy.
     * @return Giá trị boolean của thuộc tính, hoặc false nếu không tìm thấy.
     */
    public static boolean getBoolean(ConditionContext conditionContext, String property) {
        return (Boolean)getProperty((ConditionContext)conditionContext, property, Boolean.class, false);
    }

    /**
     * Lấy giá trị của thuộc tính dưới dạng boolean từ ConditionContext với giá trị mặc định.
     *
     * @param conditionContext Đối tượng ConditionContext chứa Environment.
     * @param property         Tên của thuộc tính cần lấy.
     * @param defaultValue     Giá trị mặc định trả về nếu thuộc tính không tồn tại.
     * @return Giá trị boolean của thuộc tính, hoặc defaultValue nếu không tìm thấy.
     */
    public static boolean getBoolean(ConditionContext conditionContext, String property, boolean defaultValue) {
        return (Boolean)getProperty((ConditionContext)conditionContext, property, Boolean.class, defaultValue);
    }
}

