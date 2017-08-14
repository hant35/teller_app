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

import vn.fpt.dbp.vccb.core.domain.currency.QVault;
import vn.fpt.dbp.vccb.core.domain.currency.Vault;
import vn.fpt.dbp.vccb.core.domain.currency.repository.VaultRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class VaultController {
	
	@Autowired
	private VaultRepository vaultRepository;
	
	@RequestMapping(value = "/api/vault/list", method = RequestMethod.GET, produces = "application/json")
	public List<Vault> listVaults(Principal principle) {
		return vaultRepository.findAll();
	}
	
	@RequestMapping(value = "/api/vault/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Vault vaultInfo(Principal principle, @PathVariable("id") Long id) {
		Vault vault = vaultRepository.findOne(id);
		
		return vault;
	}
	
	@RequestMapping(value = "/api/vault/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Vault> searchVault(Principal principle, @RequestBody Vault vault, Pageable pageable) {
		QVault qVault = QVault.vault;
		BooleanExpression booleanExpression = qVault.name.like("%");
		if (StringUtils.isNotEmpty(vault.getCode())) {
			booleanExpression = booleanExpression.and(qVault.code.upper().like(vault.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(vault.getName())) {
			booleanExpression = booleanExpression.and(qVault.name.upper().like(vault.getName().toUpperCase()));
		}
		
		if(vault.getBranch()!=null)
		{
			if(vault.getBranch().getId()!=null){
				booleanExpression = booleanExpression.and(qVault.branch.id.eq(vault.getBranch().getId()));
			}
			if(StringUtils.isNotEmpty(vault.getBranch().getCode())){
				booleanExpression = booleanExpression.and(qVault.branch.code.upper().eq(vault.getBranch().getCode().toUpperCase()));
			}
			if(StringUtils.isNotEmpty(vault.getBranch().getName())){
				booleanExpression = booleanExpression.and(qVault.branch.name.upper().eq(vault.getBranch().getName().toUpperCase()));
			}
		}
		
		Page<Vault> result = vaultRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Vault>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
		
	}
}