package org.egov.ps.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.egov.ps.config.Configuration;
import org.egov.ps.model.ApplicationCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ApplicationQueryBuilder {

	@Autowired
	private Configuration config;

	private static final String SELECT = "SELECT ";
	private static final String LEFT_JOIN = "LEFT OUTER JOIN";
	private static final String INNER_JOIN = "INNER JOIN";

	private static final String APP_COLUMNS = " app.id as appid, app.tenantid as apptenantid, app.property_id as appproperty_id,"
			+ " app.application_number as appapplication_number,"
			+ " app.branch_type as appbranch_type, app.module_type as appmodule_type, app.application_type as appapplication_type,"
			+ " app.comments as appcomments, app.hardcopy_received_date as apphardcopy_received_date,"
			+ " app.state as appstate, app.action as appaction," + " app.application_details as appapplication_details,"
			+ " app.created_by as appcreated_by, app.last_modified_by as applast_modified_by,"
			+ " app.created_time as appcreated_time, app.last_modified_time as applast_modified_time, "
			+ " app.bank_name as appbank_name, app.transaction_number as apptransaction_number, "
			+ " app.amount as appamount,  app.payment_type as apppayment_type, app.date_of_payment as appdate_of_payment, "

			+ " pt.id, pt.file_number, ptdl.id as ptdlid, "

			+ " doc.id as docid, doc.reference_id as docapplication_id, doc.tenantid as doctenantid,"
			+ " doc.is_active as docis_active, doc.document_type, doc.file_store_id, doc.property_id as docproperty_id,"
			+ " doc.created_by as dcreated_by, doc.created_time as dcreated_time, "
			+ " doc.last_modified_by as dmodified_by, doc.last_modified_time as dmodified_time ";

	private static final String OWNER_COLUMNS = " ownership.id as oid, ownership.property_details_id as oproperty_details_id, "
			+ " ownership.tenantid as otenantid, ownership.serial_number as oserial_number, "
			+ " ownership.share as oshare, ownership.cp_number as ocp_number, ownership.state as ostate, ownership.action as oaction, "
			+ " ownership.created_by as ocreated_by, ownership.created_time as ocreated_time, ownership.ownership_type, "
			+ " ownership.last_modified_by as omodified_by, ownership.last_modified_time as omodified_time, "

			+ " od.id as odid, od.owner_id as odowner_id,"
			+ " od.owner_name as odowner_name, od.tenantid as odtenantid,"
			+ " od.guardian_name, od.guardian_relation, od.mobile_number,"
			+ " od.allotment_number, od.date_of_allotment, od.possesion_date, od.is_approved, "
			+ " od.is_current_owner, od.is_master_entry, od.address, od.is_director, od.is_previous_owner_required, "
			+ " od.seller_name, od.seller_guardian_name, od.seller_relation, od.mode_of_transfer, od.dob ";

	private static final String APP_TABLE = " FROM cs_ep_application_v1 app " + LEFT_JOIN
			+ " cs_ep_documents_v1 doc ON app.id = doc.reference_id " + LEFT_JOIN
			+ " cs_ep_property_v1 pt ON pt.id = app.property_id " + INNER_JOIN
			+ " cs_ep_property_details_v1 ptdl  ON pt.id = ptdl.property_id ";

	private static final String OWNER_TABLE = " cs_ep_owner_v1 ownership ON ownership.property_details_id = ptdl.id "
			+ LEFT_JOIN + " cs_ep_owner_details_v1 od ON ownership.id = od.owner_id ";

	private static final String OWNERS_TABLE = " cs_ep_owner_v1 ownership " + LEFT_JOIN
			+ " cs_ep_owner_details_v1 od ON ownership.id = od.owner_id ";

	private final String paginationWrapper = "SELECT * FROM "
			+ " (SELECT *, DENSE_RANK() OVER (ORDER BY applast_modified_time desc) offset_ FROM " + "({})"
			+ " result) result_offset " + "WHERE offset_ > :start AND offset_ <= :end";

	public static final String RELATION_OWNER = "owner";
	public static final String RELATION_OWNER_DOCUMENTS = "ownerdocs";

	private String addPaginationWrapper(String query, Map<String, Object> preparedStmtList,
			ApplicationCriteria criteria) {

		/*
		 * if (criteria.getLimit() == null && criteria.getOffset() == null) return
		 * query;
		 */

		Long limit = config.getDefaultLimit();
		Long offset = config.getDefaultOffset();
		String finalQuery = paginationWrapper.replace("{}", query);

		if (criteria.getLimit() != null && criteria.getLimit() <= config.getMaxSearchLimit())
			limit = criteria.getLimit();

		if (criteria.getLimit() != null && criteria.getLimit() > config.getMaxSearchLimit())
			limit = config.getMaxSearchLimit();

		if (criteria.getOffset() != null)
			offset = criteria.getOffset();

		preparedStmtList.put("start", offset);
		preparedStmtList.put("end", limit + offset);

		return finalQuery;
	}

	private static void addClauseIfRequired(Map<String, Object> values, StringBuilder queryString) {
		if (values.isEmpty())
			queryString.append(" WHERE ");
		else {
			queryString.append(" AND ");
		}
	}

	private static final String OWNER_DOCS_COLUMNS = " doc.id as docid, doc.reference_id as docreference_id, doc.tenantid as doctenantid,"
			+ " doc.is_active as docis_active, doc.document_type, doc.file_store_id, doc.property_id as docproperty_id,"
			+ " doc.created_by as dcreated_by, doc.created_time as dcreated_time, doc.last_modified_by as dmodified_by, doc.last_modified_time as dmodified_time ";

	public String getOwnerDocsQuery(List<String> ownerDetailIds, Map<String, Object> params) {
		StringBuilder sb = new StringBuilder(SELECT);
		sb.append(OWNER_DOCS_COLUMNS);
		sb.append(" FROM cs_ep_documents_v1 doc ");
		sb.append(" where doc.reference_id IN (:references)");
		params.put("references", ownerDetailIds);
		return sb.toString();
	}

	public String getOwnersQuery(List<String> propertyDetailIds, Map<String, Object> params) {
		StringBuilder sb = new StringBuilder(SELECT);
		sb.append(OWNER_COLUMNS);
		sb.append(" FROM " + OWNERS_TABLE);
		sb.append(" where ownership.property_details_id IN (:propertyDetailIds)");
		params.put("propertyDetailIds", propertyDetailIds);
		return sb.toString();
	}

	public String getApplicationSearchQuery(ApplicationCriteria criteria, Map<String, Object> preparedStmtList) {

		StringBuilder builder;
		List<String> relations = criteria.getRelations();

		if (relations == null) {
			builder = new StringBuilder(SELECT);
			builder.append(APP_COLUMNS + "," + OWNER_COLUMNS);
			builder.append(APP_TABLE + LEFT_JOIN + OWNER_TABLE);
		} else {

			builder = new StringBuilder(SELECT);
			String columns[] = { APP_COLUMNS };
			List<String> columnList = new ArrayList<>(Arrays.asList(columns));

			String tables[] = { APP_TABLE };
			List<String> tableList = new ArrayList<>(Arrays.asList(tables));

			// columns
			if (relations.contains(RELATION_OWNER)) {
				columnList.add(OWNER_COLUMNS);
			}
			if (relations.contains(RELATION_OWNER_DOCUMENTS)) {
				columnList.add(OWNER_DOCS_COLUMNS);
			}

			String output = columnList.stream().reduce(null, (str1, str2) -> str1 == null ? str2 : str1 + " , " + str2);
			builder.append(output);

			// Joins
			if (relations.contains(RELATION_OWNER)) {
				tableList.add(OWNER_TABLE);
			}

			String tableOutput = tableList.stream().reduce(null,
					(str1, str2) -> str1 == null ? str2 : str1 + LEFT_JOIN + str2);
			builder.append(tableOutput);
		}

		if (!ObjectUtils.isEmpty(criteria.getPropertyId())) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.property_id=:prId");
			preparedStmtList.put("prId", criteria.getPropertyId());
		}
		if (!ObjectUtils.isEmpty(criteria.getApplicationId())) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.id=:appId");
			preparedStmtList.put("appId", criteria.getApplicationId());
		}
		if (null != criteria.getTenantId()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.tenantid=:tenantId");
			preparedStmtList.put("tenantId", criteria.getTenantId());
		}
		if (null != criteria.getFileNumber()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("pt.file_number=:fileNumber");
			preparedStmtList.put("fileNumber", criteria.getFileNumber().toUpperCase().trim());
		}
		if (null != criteria.getApplicationNumber()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.application_number=:appNumber");
			preparedStmtList.put("appNumber", criteria.getApplicationNumber());
		}
		if (null != criteria.getState() && criteria.getState().size() != 0) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.state IN (:state)");
			preparedStmtList.put("state", criteria.getState());
		}
		if (null != criteria.getApplicationType()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.application_type IN (:applicationType)");
			preparedStmtList.put("applicationType", criteria.getApplicationType());
		}
		if (null != criteria.getModuleType()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.module_type IN (:moduleType)");
			preparedStmtList.put("moduleType", criteria.getModuleType());
		}
		if (null != criteria.getBranchType()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.branch_type IN (:branchType)");
			preparedStmtList.put("branchType", criteria.getBranchType());
		}
		if (null != criteria.getCreatedBy()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("app.created_by = :createdBy");
			preparedStmtList.put("createdBy", criteria.getCreatedBy());
		}
		if (null != criteria.getOwnerId()) {
			addClauseIfRequired(preparedStmtList, builder);
			builder.append("ownership.id = :ownerId");
			preparedStmtList.put("ownerId", criteria.getOwnerId());
		}

		return addPaginationWrapper(builder.toString(), preparedStmtList, criteria);
	}
}
