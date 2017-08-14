package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.customer.Position;
import vn.fpt.dbp.vccb.core.domain.customer.QPosition;
import vn.fpt.dbp.vccb.core.domain.customer.repository.PositionRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class PositionController {

	@Autowired
	PositionRepository positionRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/position/list", method = RequestMethod.GET, produces = "application/json")
	public List<Position> listPositions(Principal principle) {
		return positionRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/position/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Position> searchPositions(Principal principle, @RequestBody Position request,
			Pageable pageable) {
		QPosition qPosition = QPosition.position;
		BooleanExpression booleanExpression = qPosition.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression = booleanExpression
					.and(qPosition.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		Page<Position> result = positionRepository.findAll(booleanExpression, pageable);
		return new PagedResource<Position>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}
}
