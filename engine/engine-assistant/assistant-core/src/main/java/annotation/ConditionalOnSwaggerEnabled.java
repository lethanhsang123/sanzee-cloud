package annotation;

import condition.SwaggerEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Annotation @ConditionalOnSwaggerEnabled được sử dụng để điều kiện hóa việc kích hoạt hoặc cấu hình
 * một lớp hoặc phương thức chỉ khi Swagger được bật trong ứng dụng.
 *
 * - @Retention(RetentionPolicy.RUNTIME): Annotation này sẽ được giữ lại tại thời điểm runtime,
 *   cho phép nó được truy cập thông qua phản chiếu.
 *
 * - @Target({ElementType.TYPE, ElementType.METHOD}): Annotation này có thể được áp dụng cho cả lớp (TYPE)
 *   và phương thức (METHOD).
 *
 * - @Documented: Đảm bảo rằng annotation này được bao gồm trong tài liệu Javadoc của các phần tử mà nó đánh dấu.
 *
 * - @Conditional({SwaggerEnabledCondition.class}): Điều kiện hóa annotation dựa trên kết quả của
 *   lớp điều kiện `SwaggerEnabledCondition`. Nếu điều kiện này được thỏa mãn (Swagger được bật),
 *   các lớp hoặc phương thức được đánh dấu bằng annotation này sẽ được kích hoạt.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({SwaggerEnabledCondition.class})
public @interface ConditionalOnSwaggerEnabled {
}

