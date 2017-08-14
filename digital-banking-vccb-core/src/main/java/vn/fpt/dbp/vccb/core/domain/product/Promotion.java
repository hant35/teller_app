package vn.fpt.dbp.vccb.core.domain.product;

import java.io.Serializable;
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

@Entity
@Table(name = "PROMOTION")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Promotion.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Promotion extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public Promotion() {
	}

	public Promotion(Long id) {
		this.id = id;
	}

	public Promotion(int id) {
		this.id = new Long(id);
	}

	public Promotion(String id) {
		this.id = new Long(id);
	}

	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 500)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "promotion")
	private Set<ProductPromotion> productPromotions;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Promotion))
			return false;
		if (this.getId() == null || ((Promotion) obj).getId() == null)
			return false;
		return this.getId().equals(((Promotion) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
