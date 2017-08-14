package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.organization.Department;
import vn.fpt.dbp.vccb.core.domain.organization.QDepartment;
import vn.fpt.dbp.vccb.core.domain.organization.repository.DepartmentRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@RequestMapping(value = "/api/department/list", method = RequestMethod.GET, produces = "application/json")
	public List<Department> listDepartment(Principal principle) {
		return departmentRepository.findAll();
	}

	@RequestMapping(value = "/api/department/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Department departmentInfo(Principal principle, @PathVariable("id") Long id) {
		Department Branch = departmentRepository.findOne(id);

		return Branch;
	}
	
	@RequestMapping(value = "/api/department/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Department> searchDepartment(Principal principle, @RequestBody Department department, Pageable pageable) {
		QDepartment qdepartment = QDepartment.department;
		BooleanExpression booleanExpression = qdepartment.name.like("%");
		
		if (StringUtils.isNotEmpty(department.getCode())) {
			booleanExpression = booleanExpression.and(qdepartment.code.like(department.getCode()));
		}
		if (StringUtils.isNotEmpty(department.getName())) {
			booleanExpression = booleanExpression.and(qdepartment.name.like(department.getName()));
		}
		if (department.getBranch() != null){
			if(department.getBranch().getId() != null){
				booleanExpression = booleanExpression.and(qdepartment.branch.id.eq(department.getBranch().getId()));
			}
			if(StringUtils.isNotEmpty(department.getBranch().getName())){
				booleanExpression = booleanExpression.and(qdepartment.branch.name.like(department.getBranch().getName()));
			}
			if(StringUtils.isNotEmpty(department.getBranch().getCode())){
				booleanExpression = booleanExpression.and(qdepartment.branch.code.like(department.getBranch().getCode()));
			}
		}
		Page<Department> result = departmentRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Department>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());
	}

}
