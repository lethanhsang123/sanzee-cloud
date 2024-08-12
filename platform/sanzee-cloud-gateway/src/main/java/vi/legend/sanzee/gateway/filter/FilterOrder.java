package vi.legend.sanzee.gateway.filter;

public class FilterOrder {
    private static final int INITIAL_ORDER = -10;
    private static final int ORDER_STEP = 10;

    public static final int GLOBAL_CACHE_BODY_FILTER_ORDER = INITIAL_ORDER;
    public static final int GLOBAL_SQL_INJECTION_FILTER_ORDER = GLOBAL_CACHE_BODY_FILTER_ORDER + ORDER_STEP;
    public static final int GLOBAL_CERTIFICATION_FILTER_ORDER = GLOBAL_SQL_INJECTION_FILTER_ORDER + ORDER_STEP;
    public static final int GLOBAL_RESPONSE_FILTER_ORDER = GLOBAL_CERTIFICATION_FILTER_ORDER + ORDER_STEP;
}
