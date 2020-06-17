package org.egov.assets.web.controller;

import static java.util.Arrays.asList;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.assets.model.MaterialReceiptRequest;
import org.egov.assets.model.MaterialReceiptResponse;
import org.egov.assets.model.MaterialReceiptSearch;
import org.egov.assets.service.ReceiptNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/receiptnotes")
public class ReceiptnotesApiController {

	private ReceiptNoteService receiptNoteService;

	public ReceiptnotesApiController(ReceiptNoteService receiptNoteService) {
		this.receiptNoteService = receiptNoteService;
	}

	@PostMapping(value = "/_create", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<MaterialReceiptResponse> receiptnotesCreatePost(
			@NotNull @RequestParam(value = "tenantId", required = true) String tenantId,
			@Valid @RequestBody MaterialReceiptRequest materialReceipt) {
		MaterialReceiptResponse materialReceiptResponse = receiptNoteService.create(materialReceipt, tenantId);
		return new ResponseEntity<>(materialReceiptResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/_search", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<MaterialReceiptResponse> receiptnotesSearchPost(
			@Valid @RequestBody org.egov.common.contract.request.RequestInfo requestInfo,
			@NotNull @RequestParam(value = "tenantId", required = true) String tenantId,
			@Size(max = 100) @RequestParam(value = "mrnNumber", required = false) List<String> mrnNumber,
			@Size(max = 3) @RequestParam(value = "receiptType", required = false) List<String> receiptType,
			@RequestParam(value = "mrnStatus", required = false) String mrnStatus,
			@RequestParam(value = "receivingStore", required = false) String receivingStore,
			@RequestParam(value = "supplierCode", required = false) String supplierCode,
			@RequestParam(value = "receiptDateFrom", required = false) Long receiptDateFrom,
			@RequestParam(value = "receiptDateT0", required = false) Long receiptDateT0,
			@RequestParam(value = "supplierBillPaid", required = false) Boolean supplierBillPaid,
			@Min(0) @Max(100) @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {
		MaterialReceiptSearch materialReceiptSearch = MaterialReceiptSearch.builder().tenantId(tenantId)
				.mrnNumber(mrnNumber).receiptType(receiptType).mrnStatus(null != mrnStatus ? asList(mrnStatus) : null)
				.receivingStore(receivingStore).supplierCode(supplierCode).receiptDate(receiptDateFrom)
				.receiptDate(receiptDateT0).supplierBillPaid(supplierBillPaid).pageNumber(pageNumber).pageSize(pageSize)
				.build();
		MaterialReceiptResponse materialReceiptResponse = receiptNoteService.search(materialReceiptSearch);
		return new ResponseEntity<>(materialReceiptResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/_update", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<MaterialReceiptResponse> receiptnotesUpdatePost(
			@NotNull @RequestParam(value = "tenantId", required = true) String tenantId,
			@Valid @RequestBody MaterialReceiptRequest materialReceipt) {
		MaterialReceiptResponse materialReceiptResponse = receiptNoteService.update(materialReceipt, tenantId);
		return new ResponseEntity<>(materialReceiptResponse, HttpStatus.OK);
	}

}
