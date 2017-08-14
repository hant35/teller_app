package vn.fpt.dbp.vccb.core.domain.exchange;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

/**
 * Chi tiết về tỉ giá
 *
 */
@Entity
@Table(name = "EXCHANGE_DETAIL")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ExchangeDetail.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class ExchangeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	public ExchangeDetail() {
	}

	public ExchangeDetail(Long id) {
		this.id = id;
	}

	public ExchangeDetail(int id) {
		this.id = new Long(id);
	}

	public ExchangeDetail(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "EXCHANGE_DETAIL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXCHANGE")
	private Exchange exchange;

	/**
	 * Loại tỷ giá<br>
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXCHANGE_TYPE")
	private ExchangeType exchangeType;

	/**
	 * Tỷ giá trung bình<br>
	 */
	@Column(name = "MID_RATE", updatable = true, insertable = true)
	private Double midRate;

	/**
	 * Biên độ mua<br>
	 * Bằng tỷ giá trung bình trừ tỷ giá mua
	 */
	@Column(name = "BUY_BAND", updatable = true, insertable = true)
	private Double buyBand;

	/**
	 * Biên độ bán<br>
	 * Bằng tỷ giá bán trừ tỷ giá trung bình
	 */
	@Column(name = "SELL_BAND", updatable = true, insertable = true)
	private Double sellBand;

	/**
	 * Tỷ giá mua<br>
	 * Tỷ giá mua phải nhỏ hơn hoặc bằng tỷ giá trung bình
	 */
	@Column(name = "BUY_PRICE", updatable = true, insertable = true)
	private Double buyPrice;

	/**
	 * Tỷ giá bán<br>
	 * Tỷ giá bán phải lớn hơn hoặc bằng tỷ giá trung bình
	 */
	@Column(name = "SELL_PRICE", updatable = true, insertable = true)
	private Double sellPrice;

	/**
	 * Ngày hiệu lực
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTED_DATE")
	private Date effectedDate;

	/**
	 * Số lần thay đổi<br>
	 * Hệ thống tự sinh số lần thay đổi tỷ giá trong ngày<br>
	 * 
	 */
	@Column(name = "CHANGED_NUMBER", updatable = true, insertable = true)
	private Integer changedNumber;

	@XmlTransient
	public Exchange getExchange() {
		return this.exchange;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ExchangeDetail))
			return false;
		if (this.getId() == null || ((ExchangeDetail) obj).getId() == null)
			return false;
		return this.getId().equals(((ExchangeDetail) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}
