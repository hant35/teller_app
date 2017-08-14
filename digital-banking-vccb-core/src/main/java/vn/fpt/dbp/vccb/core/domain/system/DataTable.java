package vn.fpt.dbp.vccb.core.domain.system;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SYS_DATA_TABLE")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=DataTable.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class DataTable implements Serializable{
	private static final long serialVersionUID = 1L;
	public DataTable(){}
	
	public DataTable(Long id){
		this.id = id;
	}
	
	public DataTable(int id){
		this.id = new Long(id);
	}
	
	public DataTable(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "DATA_TABLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@Column(name = "SCREEN_CODE", updatable=true, insertable=true, length = 50)
	private String screenCode;
	
	@Column(name = "DATA_TABLE_CODE", updatable=true, insertable=true, length = 50)
	private String tableCode;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="dataTable")
	private Set<DataTableDetail> dataTableDetails;

}
