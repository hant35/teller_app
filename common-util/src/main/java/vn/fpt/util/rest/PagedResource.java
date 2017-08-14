package vn.fpt.util.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Pagination for a resource content
 * 
 * @see http://www.javaenthusiasm.com/rest-pagination-with-spring-boot/
 *
 * @param <T>
 *            content to render
 */
@SuppressWarnings("rawtypes")
public class PagedResource<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> content;
	private final Map metadata = new HashMap();

	public PagedResource() {
	}

	public PagedResource(List<T> content, int pageNumber, int perPage, int totalPages) {
		super();
		this.setContent(content);
		populateMetadata(pageNumber, perPage, totalPages, Long.valueOf(perPage * totalPages));
	}

	public PagedResource(List<T> content, int pageNumber, int perPage, int totalPages, long totalElements) {
		super();
		this.setContent(content);
		populateMetadata(pageNumber, perPage, totalPages, totalElements);
	}

	@SuppressWarnings("unchecked")
	private void populateMetadata(int pageNumber, int perPage, int totalPages, long totalElements) {
		metadata.put("pageNumber", pageNumber);
		metadata.put("perPage", perPage);
		metadata.put("totalPages", totalPages);
		metadata.put("totalElements", totalElements);
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Map getMetadata() {
		return metadata;
	}

}
