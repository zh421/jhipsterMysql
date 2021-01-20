package aiot.java.util;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

public class LongFilterUtils {

    public static LongFilter toEqualLongFilter(Long lon) {
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(lon);
        return longFilter;
    }
    public static LongFilter toNotEqualLongFilter(Long lon) {
        LongFilter longFilter = new LongFilter();
        longFilter.setNotEquals(lon);
        return longFilter;
    }
}
