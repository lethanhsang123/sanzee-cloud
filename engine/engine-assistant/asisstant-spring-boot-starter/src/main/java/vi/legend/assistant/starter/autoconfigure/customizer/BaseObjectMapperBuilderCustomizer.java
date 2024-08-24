package vi.legend.assistant.starter.autoconfigure.customizer;

import com.fasterxml.jackson.databind.Module;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * Interface BaseObjectMapperBuilderCustomizer mở rộng từ Jackson2ObjectMapperBuilderCustomizer
 * và Ordered để tùy chỉnh việc cấu hình Jackson ObjectMapper.
 */
public interface BaseObjectMapperBuilderCustomizer extends Jackson2ObjectMapperBuilderCustomizer, Ordered {

    /**
     * Phương thức chuyển đổi một danh sách các Module thành một mảng các Module.
     *
     * @param modules Danh sách các Module cần chuyển đổi.
     * @return Mảng các Module nếu danh sách không rỗng, ngược lại trả về mảng rỗng.
     */
    default Module[] toArray(List<Module> modules) {
        if (CollectionUtils.isNotEmpty(modules)) {
            Module[] temps = new Module[modules.size()];
            return (Module[]) modules.toArray(temps);
        } else {
            return new Module[0];
        }
    }
}
