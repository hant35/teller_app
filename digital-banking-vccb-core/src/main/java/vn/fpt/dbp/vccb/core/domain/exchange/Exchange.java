package vn.fpt.dbp.vccb.core.domain.exchange;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.interest.InterestUploadFile;
import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

/**
 * Tỷ giá
 *
 */
@Entity
@Table(name = "EXCHANGE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Exchange.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Exchange extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public Exchange() {
	}

	public Exchange(Long id) {
		this.id = id;
	}

	public Exchange(int id) {
		this.id = new Long(id);
	}

	public Exchange(String id) {
		this.id = new Long(id);
	}

	/**
	 * Khu vực
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA")
	private Area area;

	/**
	 * Đơn vị
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/**
	 * Loại tiền (đổi từ loại tiền)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY_FROM")
	private Currency currencyFrom;

	/**
	 * Loại tiền (đổi đến loại tiền)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY_TO")
	private Currency currencyTo;

	/** The exchange upload file. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXCHANGE_UPLOAD_FILE")
	private ExchangeUploadFile exchangeUploadFile;

	/**
	 * Chi tiết về tỉ giá
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exchange")
	private Set<ExchangeDetail> exchangeDetails;

	@XmlTransient
	public ExchangeUploadFile getExchangeUploadFile() {
		return this.exchangeUploadFile;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Exchange))
			return false;
		if (this.getId() == null || ((Exchange) obj).getId() == null)
			return false;
		return this.getId().equals(((Exchange) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}
