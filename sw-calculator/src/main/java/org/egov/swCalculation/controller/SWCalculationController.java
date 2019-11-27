package org.egov.swCalculation.controller;

import java.util.List;

import javax.validation.Valid;

import org.egov.swCalculation.model.Demand;
import org.egov.swCalculation.model.DemandResponse;
import org.egov.swCalculation.service.DemandService;
import org.egov.swCalculation.service.SWCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@RestController
@RequestMapping("/sewerageCalculator")
public class SWCalculationController {
	
	@Autowired
	SWCalculationService sWCalculationService;
	
	@Autowired
	DemandService demandService;
	
	
	
	
	@PostMapping("/_createDemand")
	public ResponseEntity<DemandResponse> generateDemands(@RequestBody @Valid CalculationReq calculationReq) {
		List<Demand> demandList = demandService.generateDemands(calculationReq);
		DemandResponse response = DemandResponse.builder().demands(demandList).responseInfo(responseInfoFactory.createResponseInfoFromRequestInfo(calculationReq.getRequestInfo(), true)).build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
