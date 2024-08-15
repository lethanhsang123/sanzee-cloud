package vi.legend.sanzee.module.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

@Schema(
        title = "Thống nhất kiểu dữ liệu trả về",
        description = "Tất cả REST API được thống nhất đối tượng trả về",
        example = "new Result<T>().ok ().message(\"XXX\")"
)
public class Result<T> implements Serializable {

    @Schema(
            title = "Thời gian response",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date timestamp = new Date();

}