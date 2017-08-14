package vn.fpt.dbp.vccb.core.domain.casa;

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
@Table(name = "SERVICE_GROUP_TYPE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ServiceGroupType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class ServiceGroupType implements Serializable {
	private static final long serialVersionUID = 1L;

	public ServiceGroupType() {
	}

	public ServiceGroupType(Long id) {
		this.id = id;
	}

	public ServiceGroupType(int id) {
		this.id = new Long(id);
	}

	public ServiceGroupType(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "SERVICE_GROUP_TYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_GROUP")
	private ServiceGroup serviceGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_TYPE")
	private ServiceType serviceType;

	@XmlTransient
	public ServiceGroup getServiceGroup() {
		return this.serviceGroup;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ServiceGroupType))
			return false;
		if (this.getId() == null || ((ServiceGroupType) obj).getId() == null)
			return false;
		return this.getId().equals(((ServiceGroupType) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}