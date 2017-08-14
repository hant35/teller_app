package vn.fpt.dbp.vccb.core.domain.limit;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "LIMIT_GROUP")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=LimitGroup.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class LimitGroup extends WorkFlowModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public LimitGroup(){}
	
	public LimitGroup(Long id){
		this.id= id;
	}
	
	public LimitGroup(int id){
		this.id= new Long(id);
	}
	
	public LimitGroup(String id){
		this.id= new Long(id);
	}
	
	@Column(name = "CODE", updatable=true, insertable=true, length = 50)
	private String code;
	
	@Column(name = "NAME", updatable=true, insertable=true, length = 200)
	private String name;
	
	@Column(name = "STATUS", updatable=true, insertable=true, length = 50)
	private String status;
	
	@ManyToMany
	@JoinTable(
		      name="LIMITGROUP_DETAIL",
		      joinColumns=@JoinColumn(name="LIMIT_GROUP", referencedColumnName="ID"),
		      inverseJoinColumns=@JoinColumn(name="LIMIT", referencedColumnName="ID"))
	private Set<Limit> limits; 
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof LimitGroup))
			return false;
		if (this.getId() == null || ((LimitGroup) obj).getId() == null)
			return false;
		return this.getId().equals(((LimitGroup) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
