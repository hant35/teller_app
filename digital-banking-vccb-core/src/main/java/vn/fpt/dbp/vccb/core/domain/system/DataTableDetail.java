package vn.fpt.dbp.vccb.core.domain.system;

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

@Entity
@Table(name = "SYS_DATA_TABLE_DETAIL")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=DataTableDetail.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class DataTableDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public DataTableDetail(){}
	
	public DataTableDetail(Long id){
		this.id = id;
	}
	
	public DataTableDetail(int id){
		this.id = new Long(id);
	}
	
	public DataTableDetail(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "DATATABLE_DETAIL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@Column(name = "COLUMN_NAME", updatable=true, insertable=true, length = 50)
	private String coulmnName;
	
	@Column(name = "COLUMN_TITLE", updatable=true, insertable=true, length = 50)
	private String coulmnTitle;
	
	@Column(name = "ENABLE", updatable=true, insertable=true)
	private boolean enable;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DATATABLE")
	private DataTable dataTable;
	
	@XmlTransient
	public DataTable getDataTable(){
		return this.dataTable;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof DataTableDetail))
			return false;
		if (this.getId() == null || ((DataTableDetail) obj).getId() == null)
			return false;
		return this.getId().equals(((DataTableDetail) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
