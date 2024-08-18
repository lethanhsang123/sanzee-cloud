package utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Lớp ResourceUtils cung cấp các phương thức tiện ích để làm việc với tài nguyên (resources),
 * bao gồm việc truy xuất và xử lý các tài nguyên từ các vị trí khác nhau như file hệ thống,
 * classpath, URL, và các nguồn tài nguyên khác.
 */
public class ResourceUtils {

    // Logger để ghi lại thông tin nhật ký của lớp.
    private static final Logger log = LoggerFactory.getLogger(ResourceUtils.class);

    // Đối tượng duy nhất của lớp ResourceUtils, sử dụng mô hình Singleton.
    private static volatile ResourceUtils INSTANCE;

    // Bộ giải quyết mẫu tài nguyên, giúp tìm kiếm tài nguyên từ các vị trí xác định.
    private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * Constructor mặc định, được định nghĩa là private để ngăn chặn việc tạo đối tượng bên ngoài.
     */
    private ResourceUtils() {
    }

    /**
     * Trả về đối tượng duy nhất của lớp ResourceUtils, khởi tạo đối tượng này nếu chưa tồn tại.
     * Sử dụng mô hình Singleton với double-checked locking.
     *
     * @return Đối tượng ResourceUtils.
     */
    private static ResourceUtils getInstance() {
        if (ObjectUtils.isEmpty(INSTANCE)) {
            synchronized (ResourceUtils.class) {
                if (ObjectUtils.isEmpty(INSTANCE)) {
                    INSTANCE = new ResourceUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Trả về đối tượng PathMatchingResourcePatternResolver.
     *
     * @return Đối tượng PathMatchingResourcePatternResolver.
     */
    private PathMatchingResourcePatternResolver getPathMatchingResourcePatternResolver() {
        return this.pathMatchingResourcePatternResolver;
    }

    /**
     * Trả về bộ giải quyết tài nguyên sử dụng trong lớp này.
     *
     * @return Đối tượng PathMatchingResourcePatternResolver.
     */
    private static PathMatchingResourcePatternResolver getResolver() {
        return getInstance().getPathMatchingResourcePatternResolver();
    }

    /**
     * Trả về tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Đối tượng Resource tại vị trí chỉ định.
     */
    public static Resource getResource(String location) {
        return getResolver().getResource(location);
    }

    /**
     * Trả về đối tượng File từ tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Đối tượng File tại vị trí chỉ định.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static File getFile(String location) throws IOException, IOException {
        return getResource(location).getFile();
    }

    /**
     * Trả về luồng đầu vào từ tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Đối tượng InputStream của tài nguyên.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static InputStream getInputStream(String location) throws IOException {
        return getResource(location).getInputStream();
    }

    /**
     * Trả về tên của tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Tên của tài nguyên.
     */
    public static String getFilename(String location) {
        return getResource(location).getFilename();
    }

    /**
     * Trả về URI của tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Đối tượng URI của tài nguyên.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static URI getURI(String location) throws IOException {
        return getResource(location).getURI();
    }

    /**
     * Trả về URL của tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Đối tượng URL của tài nguyên.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static URL getURL(String location) throws IOException {
        return getResource(location).getURL();
    }

    /**
     * Trả về độ dài nội dung của tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Độ dài nội dung của tài nguyên.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static long contentLength(String location) throws IOException {
        return getResource(location).contentLength();
    }

    /**
     * Trả về thời gian sửa đổi cuối cùng của tài nguyên tại vị trí chỉ định.
     *
     * @param location Vị trí của tài nguyên.
     * @return Thời gian sửa đổi cuối cùng của tài nguyên.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static long lastModified(String location) throws IOException {
        return getResource(location).lastModified();
    }

    /**
     * Kiểm tra tài nguyên có tồn tại tại vị trí chỉ định hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu tài nguyên tồn tại, ngược lại trả về false.
     */
    public static boolean exists(String location) {
        return getResource(location).exists();
    }

    /**
     * Kiểm tra tài nguyên tại vị trí chỉ định có phải là file hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu tài nguyên là file, ngược lại trả về false.
     */
    public static boolean isFile(String location) {
        return getResource(location).isFile();
    }

    /**
     * Kiểm tra tài nguyên tại vị trí chỉ định có thể đọc được hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu tài nguyên có thể đọc, ngược lại trả về false.
     */
    public static boolean isReadable(String location) {
        return getResource(location).isReadable();
    }

    /**
     * Kiểm tra tài nguyên tại vị trí chỉ định có đang mở hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu tài nguyên đang mở, ngược lại trả về false.
     */
    public static boolean isOpen(String location) {
        return getResource(location).isOpen();
    }

    /**
     * Trả về một mảng các tài nguyên dựa trên mẫu vị trí chỉ định.
     *
     * @param locationPattern Mẫu vị trí của tài nguyên.
     * @return Mảng các đối tượng Resource.
     * @throws IOException Nếu có lỗi khi truy xuất tài nguyên.
     */
    public static Resource[] getResources(String locationPattern) throws IOException {
        return getResolver().getResources(locationPattern);
    }

    /**
     * Kiểm tra vị trí chỉ định có phải là một URL hợp lệ hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu là URL hợp lệ, ngược lại trả về false.
     */
    public static boolean isUrl(String location) {
        return org.springframework.util.ResourceUtils.isUrl(location);
    }

    /**
     * Kiểm tra vị trí chỉ định có phải là một URL trong classpath hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu là URL trong classpath, ngược lại trả về false.
     */
    public static boolean isClasspathUrl(String location) {
        return StringUtils.startsWith(location, "classpath:");
    }

    /**
     * Kiểm tra vị trí chỉ định có phải là một URL trong classpath* hay không.
     *
     * @param location Vị trí của tài nguyên.
     * @return True nếu là URL trong classpath*, ngược lại trả về false.
     */
    public static boolean isClasspathAllUrl(String location) {
        return StringUtils.startsWith(location, "classpath*:");
    }

    /**
     * Kiểm tra URL chỉ định có phải là URL tới file JAR hay không.
     *
     * @param url URL của tài nguyên.
     * @return True nếu là URL tới file JAR, ngược lại trả về false.
     */
    public static boolean isJarUrl(URL url) {
        return org.springframework.util.ResourceUtils.isJarURL(url);
    }

    /**
     * Kiểm tra URL chỉ định có phải là URL tới file hệ thống hay không.
     *
     * @param url URL của tài nguyên.
     * @return True nếu là URL tới file hệ thống, ngược lại trả về false.
     */
    public static boolean isFileUrl(URL url) {
        return org.springframework.util.ResourceUtils.isFileURL(url);
    }

    /**
     * Chuyển đổi tài nguyên thành mảng byte.
     *
     * @param resource Đối tượng Resource cần chuyển đổi.
     * @return Mảng byte từ tài nguyên, hoặc null nếu có lỗi xảy ra.
     */
    public static byte[] toBytes(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            return FileCopyUtils.copyToByteArray(inputStream);
        } catch (IOException var2) {
            log.error("[Herodotus] |- Converter resource to byte[] error!", var2);
            return null;
        }
    }

    /**
     * Chuyển đổi tài nguyên thành chuỗi Base64.
     *
     * @param resource Đối tượng Resource cần chuyển đổi.
     * @return Chuỗi Base64 từ tài nguyên, hoặc null nếu có lỗi xảy ra.
     */
    public static String toBase64(Resource resource) {
        byte[] bytes = toBytes(resource);
        return Base64.encode(bytes);
    }
}

