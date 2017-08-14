package vn.fpt.dbp.vccb.core.domain.base;

import org.springframework.data.domain.Pageable;
import vn.fpt.util.rest.PagedResource;

/**
 * Created by NghiaPV on 7/4/2017.
 */
public interface CustomRepository<T, V> {
    public PagedResource<T> searchLastedVersionCust(V model, Pageable pageable);
}
