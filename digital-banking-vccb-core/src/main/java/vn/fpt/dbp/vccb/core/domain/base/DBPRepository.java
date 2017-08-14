package vn.fpt.dbp.vccb.core.domain.base;

import org.springframework.data.domain.Pageable;

import vn.fpt.util.rest.PagedResource;

public interface DBPRepository<T> {
	//public EntityManager entityManager();
	public PagedResource<T> searchLastedVersion(T model, Pageable pageable);
}
