package vi.legend.assistant.definition.domain.view.datatables;

import java.util.Iterator;
import java.util.List;

public class DataTableUtils {
    public static final String ECHO = "sEcho";
    public static final String DISPLAY_START = "iDisplayStart";
    public static final String DISPLAY_LENGTH = "iDisplayLength";
    public static final String QUERY_JSON = "queryJson";
    public static final String DATA = "data";

    public DataTableUtils() {
    }

    public static DataTableResult parseDataTableParameter(List<DataTableParameter> params) {
        String sEcho = null;
        String jsonString = null;
        int iDisplayStart = 0;
        int iDisplayLength = 0;
        Iterator var5 = params.iterator();

        while(var5.hasNext()) {
            DataTableParameter param = (DataTableParameter)var5.next();
            if (param.getName().equals("sEcho")) {
                sEcho = param.getValue().toString();
            }

            if (param.getName().equals("iDisplayStart")) {
                iDisplayStart = (Integer)param.getValue();
            }

            if (param.getName().equals("iDisplayLength")) {
                iDisplayLength = (Integer)param.getValue();
            }

            if (param.getName().equals("queryJson")) {
                jsonString = param.getValue().toString();
            }
        }

        return new DataTableResult(sEcho, iDisplayStart, iDisplayLength, jsonString);
    }
}
