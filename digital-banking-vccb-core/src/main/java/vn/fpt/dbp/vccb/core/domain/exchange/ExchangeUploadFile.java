package vn.fpt.dbp.vccb.core.domain.exchange;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;

/**
 * The Class ExchangeUploadFile.
 */
@Entity
@Table(name = "EXCHANGE_UPLOAD_FILE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ExchangeUploadFile.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class ExchangeUploadFile extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public ExchangeUploadFile() {
	}

	public ExchangeUploadFile(Long id) {
		this.id = id;
	}

	public ExchangeUploadFile(int id) {
		this.id = new Long(id);
	}

	public ExchangeUploadFile(String id) {
		this.id = new Long(id);
	}

	/**
	 * Tên file<br>
	 */
	@Column(name = "FILENAME", updatable = true, insertable = true, length = 200)
	private String fileName;

	/**
	 * Ngày upload<br>
	 */
	@Column(name = "UPLOAD_DATE", updatable = true, insertable = true)
	private Date uploadDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchangeUploadFile")
	private Set<Exchange> exchanges;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ExchangeUploadFile))
			return false;
		if (this.getId() == null || ((ExchangeUploadFile) obj).getId() == null)
			return false;
		return this.getId().equals(((ExchangeUploadFile) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}
