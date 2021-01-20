package aiot.java.util;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.util.List;

public class StringFilterUtils {

    public static StringFilter toEqualStringFilter(String str) {
        StringFilter sf = new StringFilter();
        sf.setEquals(str);
        return sf;
    }

    public static StringFilter toNotEqualStringFilter(String str) {
        StringFilter sf = new StringFilter();
        sf.setNotEquals(str);
        return sf;
    }

    public static StringFilter toContainStringFilter(String str) {
        StringFilter sf = new StringFilter();
        sf.setContains(str);
        return sf;
    }

    public static StringFilter toInStringFilter(List<String> strLst) {
        StringFilter sf = new StringFilter();
        sf.setIn(strLst);
        return sf;
    }

    public static StringFilter toNotContainStringFilter(String str) {
        StringFilter sf = new StringFilter();
        sf.setDoesNotContain(str);
        return sf;
    }

    public static LongFilter toEqualLongFilter(Long lon) {
        LongFilter lf = new LongFilter();
        lf.setEquals(lon);
        return lf;
    }
}
