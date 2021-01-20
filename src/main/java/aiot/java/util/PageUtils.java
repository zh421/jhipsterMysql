package aiot.java.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtils {

    public static <T> Page<T> convertToPage (List<T> source, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > source.size() ? source.size() : ( start + pageable.getPageSize());
        Page<T> result = new PageImpl<>(source.subList(start, end), pageable, source.size());
        return result;
    }
}
