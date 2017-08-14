package vn.fpt.dbp.vccb.core.domain.limit;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.customer.CustomerGroup;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.system.Function;

@Entity
@Table(name = "LIMIT_DETAIL")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=LimitDetail.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class LimitDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public LimitDetail(){}
	
	public LimitDetail(Long id){
		this.id = id;
	}
	
	public LimitDetail(int id){
		this.id = new Long(id);
	}
	
	public LimitDetail(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "LIMITDETAIL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIMIT")
	private Limit limit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERGROUP")
	private CustomerGroup customerGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FUNCTION")
	private Function function;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;
	
	@Column(name = "LIMIT_VALUE", updatable=true, insertable=true)
	private Long limitValue;
	
	@Column(name = "MUST_APPROVE", updatable=true, insertable=true)
	private Boolean isMustApprove;
	
	@Column(name = "MUST_APPROVE_OVER", updatable=true, insertable=true)
	private Boolean isMustApproveOver;
	
	@Column(name = "STOP_WHEN_OVER", updatable=true, insertable=true)
	private Boolean isStopWhenOver;
	
	@Column(name = "IS_VIEW", updatable=true, insertable=true)
	private Boolean isView;
	
	@XmlTransient
	public Limit getLimit()
	{
		return this.limit; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof LimitDetail))
			return false;
		if (this.getId() == null || ((LimitDetail) obj).getId() == null)
			return false;
		return this.getId().equals(((LimitDetail) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
