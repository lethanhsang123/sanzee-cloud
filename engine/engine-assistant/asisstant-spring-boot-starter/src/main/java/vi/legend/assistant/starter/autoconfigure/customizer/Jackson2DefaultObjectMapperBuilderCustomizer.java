package vi.legend.assistant.starter.autoconfigure.customizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import vi.legend.assistant.core.json.jackson2.modules.EncapsulationClassJackson2Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp Jackson2DefaultObjectMapperBuilderCustomizer hiện thực giao diện BaseObjectMapperBuilderCustomizer,
 * thực hiện tùy chỉnh cấu hình cho Jackson2ObjectMapperBuilder.
 */
public class Jackson2DefaultObjectMapperBuilderCustomizer implements BaseObjectMapperBuilderCustomizer{

    /**
     * Constructor mặc định của lớp Jackson2DefaultObjectMapperBuilderCustomizer.
     */
    public Jackson2DefaultObjectMapperBuilderCustomizer() {
    }

    /**
     * Phương thức customize tùy chỉnh Jackson2ObjectMapperBuilder bằng cách:
     * - Bật các tính năng: sắp xếp các mục trong Map theo khóa (ORDER_MAP_ENTRIES_BY_KEYS),
     *   cho phép sử dụng dấu nháy đơn trong JSON (ALLOW_SINGLE_QUOTES),
     *   và cho phép các ký tự điều khiển không thoát (ALLOW_UNESCAPED_CONTROL_CHARS).
     * - Tắt các tính năng: không thất bại khi gặp đối tượng rỗng (FAIL_ON_EMPTY_BEANS),
     *   không ghi ngày tháng dưới dạng dấu thời gian (WRITE_DATES_AS_TIMESTAMPS),
     *   và không thất bại khi gặp các thuộc tính không xác định (FAIL_ON_UNKNOWN_PROPERTIES).
     * - Cài đặt các mô-đun bổ sung: EncapsulationClassJackson2Module, Jdk8Module, và JavaTimeModule.
     * - Sử dụng ServiceLoader để tự động tìm kiếm và cài đặt các mô-đun Jackson.
     *
     * @param builder Đối tượng Jackson2ObjectMapperBuilder cần được tùy chỉnh.
     */
    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToEnable(new Object[]{
                SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,
                JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature()
        });
        builder.featuresToDisable(new Object[]{
                SerializationFeature.FAIL_ON_EMPTY_BEANS,
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        });
        builder.modulesToInstall((modules) -> {
            List<Module> install = new ArrayList(modules);
            install.add(new EncapsulationClassJackson2Module());
            install.add(new Jdk8Module());
            install.add(new JavaTimeModule());
            builder.modulesToInstall(this.toArray(install));
        });
        builder.findModulesViaServiceLoader(true);
    }

    /**
     * Phương thức getOrder trả về thứ tự ưu tiên của customizer này khi có nhiều customizer được đăng ký.
     *
     * @return Giá trị thứ tự ưu tiên, ở đây là 1.
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
