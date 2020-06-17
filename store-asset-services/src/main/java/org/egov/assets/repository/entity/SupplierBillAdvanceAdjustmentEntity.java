package org.egov.assets.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import org.egov.assets.model.SupplierAdvanceRequisition;
import org.egov.assets.model.SupplierBillAdvanceAdjustment;

import static org.springframework.util.StringUtils.isEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierBillAdvanceAdjustmentEntity {

    private String id;

    private String tenantId;

    private String supplierBill;

    private String supplierAdvanceRequisition;

    private BigDecimal advanceAdjustedAmount;

    private String createdBy;

    private Long createdTime;

    private String lastmodifiedBy;

    private Long lastmodifiedTime;

    public SupplierBillAdvanceAdjustment toDomain() {

        SupplierBillAdvanceAdjustment supplierBillAdvanceAdjustment = new SupplierBillAdvanceAdjustment();

        return supplierBillAdvanceAdjustment.id(id)
                .tenantId(tenantId)
                .supplierBill(supplierBill)
                .supplierAdvanceRequisition(!isEmpty(supplierAdvanceRequisition) ? new SupplierAdvanceRequisition().id(supplierAdvanceRequisition) : null)
                .advanceAdjustedAmount(advanceAdjustedAmount);
    }
}
