package vn.fpt.dbp.vccb.core.domain.limit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "LIMIT")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Limit.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Limit extends WorkFlowModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Limit(){}
	
	public Limit(Long id){
		this.id= id;
	}
	
	public Limit(int id){
		this.id= new Long(id);
	}
	
	public Limit(String id){
		this.id= new Long(id);
	}
	

	@Column(name = "CODE", updatable=true, insertable=true, length = 50)
	private String code;
	
	@Column(name = "NAME", updatable=true, insertable=true, length = 200)
	private String name;
	
	@Column(name = "STATUS", updatable=true, insertable=true, length = 50)
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="limit")
	@OrderBy("id ASC")
	private Set<LimitDetail> limitDetails;
	/*
	@ManyToMany
	@JoinTable(
		      name="LIMITGROUP_DETAIL",
		      joinColumns=@JoinColumn(name="LIMIT", referencedColumnName="ID"),
		      inverseJoinColumns=@JoinColumn(name="LIMIT_GROUP", referencedColumnName="ID"))
	private Set<LimitGroup> limitGroups; 
	
	@ManyToMany(mappedBy="limits")
	private Set<LimitGroup> limitGroups;
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Limit))
			return false;
		if (this.getId() == null || ((Limit) obj).getId() == null)
			return false;
		return this.getId().equals(((Limit) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}