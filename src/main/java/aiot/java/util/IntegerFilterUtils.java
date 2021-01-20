package aiot.java.util;

import io.github.jhipster.service.filter.IntegerFilter;

public class IntegerFilterUtils {

    public static IntegerFilter toEqualIntegerFilter(Integer integer) {
        IntegerFilter intf = new IntegerFilter();
        intf.setEquals(integer);
        return intf;
    }
}
