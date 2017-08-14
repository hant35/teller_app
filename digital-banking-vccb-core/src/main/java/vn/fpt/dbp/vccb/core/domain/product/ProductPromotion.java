package vn.fpt.dbp.vccb.core.domain.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_PROMOTION")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductPromotion.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class ProductPromotion implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProductPromotion() {
	}

	public ProductPromotion(Long id) {
		this.id = id;
	}

	public ProductPromotion(int id) {
		this.id = new Long(id);
	}

	public ProductPromotion(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "PRODUCT_PROMOTION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROMOTION")
	private Promotion promotion;

	@XmlTransient
	public Product getProduct() {
		return this.product;
	}

	@XmlTransient
	public Promotion getPromotion() {
		return this.promotion;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ProductPromotion))
			return false;
		if (this.getId() == null || ((ProductPromotion) obj).getId() == null)
			return false;
		return this.getId().equals(((ProductPromotion) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
