"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
var envVariables = {

  //server configurations
  SERVER_PORT: process.env.SERVER_PORT || "8097",

  //kafka configurations
  KAFKA_BROKER_HOST: process.env.KAFKA_BROKER_HOST || "localhost:9092",

  EGOV_PENSION_BENEFIT_CODE_IR: process.env.EGOV_PENSION_BENEFIT_CODE_IR || "IR",
  EGOV_PENSION_BENEFIT_CODE_COMMUTATION_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_COMMUTATION_PENSION || "COMMUTATION_PENSION",
  EGOV_PENSION_BENEFIT_CODE_BASIC_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_BASIC_PENSION || "BASIC_PENSION",
  EGOV_PENSION_BENEFIT_CODE_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_PENSION || "PENSION",
  EGOV_PENSION_BENEFIT_CODE_PENSION_DEDUCTION: process.env.EGOV_PENSION_BENEFIT_CODE_PENSION_DEDUCTION || "PENSION_DEDUCTION",
  EGOV_PENSION_BENEFIT_CODE_COMMUTATION_VALUE: process.env.EGOV_PENSION_BENEFIT_CODE_COMMUTATION_VALUE || "COMMUTATION_VALUE",
  EGOV_PENSION_BENEFIT_CODE_COMMUTED_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_COMMUTED_PENSION || "COMMUTED_PENSION",
  EGOV_PENSION_BENEFIT_CODE_DA: process.env.EGOV_PENSION_BENEFIT_CODE_DA || "DA",
  EGOV_PENSION_BENEFIT_CODE_DCRG: process.env.EGOV_PENSION_BENEFIT_CODE_DCRG || "DCRG",
  EGOV_PENSION_BENEFIT_CODE_FINAL_CALCULATED_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_FINAL_CALCULATED_PENSION || "FINAL_CALCULATED_PENSION",
  EGOV_PENSION_BENEFIT_CODE_DCRG_DUES_DEDUCTION: process.env.EGOV_PENSION_BENEFIT_CODE_DCRG_DUES_DEDUCTION || "DCRG_DUES_DEDUCTION",
  EGOV_PENSION_BENEFIT_CODE_ADDITIONAL_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_ADDITIONAL_PENSION || "ADDITIONAL_PENSION",
  EGOV_PENSION_BENEFIT_CODE_NET_DEDUCTION: process.env.EGOV_PENSION_BENEFIT_CODE_NET_DEDUCTION || "NET_DEDUCTION",
  EGOV_PENSION_BENEFIT_CODE_TOTAL_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_TOTAL_PENSION || "TOTAL_PENSION",
  EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_1: process.env.EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_1 || "FAMILY_PENSION_1",
  EGOV_PENSION_BENEFIT_CODE_ADDITIONAL_FAMILY_PENSION_1: process.env.EGOV_PENSION_BENEFIT_CODE_ADDITIONAL_FAMILY_PENSION_1 || "ADDITIONAL_FAMILY_PENSION_1",
  EGOV_PENSION_BENEFIT_CODE_PROVISIONAL_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_PROVISIONAL_PENSION || "PROVISIONAL_PENSION",
  EGOV_PENSION_BENEFIT_CODE_COMPASSIONATE_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_COMPASSIONATE_PENSION || "COMPASSIONATE_PENSION",
  EGOV_PENSION_BENEFIT_CODE_COMPENSATION_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_COMPENSATION_PENSION || "COMPENSATION_PENSION",
  EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_1_START_DATE: process.env.EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_1_START_DATE || "FAMILY_PENSION_1_START_DATE",
  EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_1_END_DATE: process.env.EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_1_END_DATE || "FAMILY_PENSION_1_END_DATE",
  EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_2_START_DATE: process.env.EGOV_PENSION_BENEFIT_CODE_FAMILY_PENSION_2_START_DATE || "FAMILY_PENSION_2_START_DATE",
  EGOV_PENSION_BENEFIT_CODE_PENSION_DA: process.env.EGOV_PENSION_BENEFIT_CODE_PENSION_DA || "PENSION_DA",
  EGOV_PENSION_BENEFIT_CODE_PENSIONER_FAMILY_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_PENSIONER_FAMILY_PENSION || "PENSIONER_FAMILY_PENSION",
  EGOV_PENSION_BENEFIT_CODE_PENSION_IR: process.env.EGOV_PENSION_BENEFIT_CODE_PENSION_IR || "PENSION_IR",
  EGOV_PENSION_BENEFIT_CODE_ONE_FORTH_HALF_YEAR_OF_SERVICE: process.env.EGOV_PENSION_BENEFIT_CODE_ONE_FORTH_HALF_YEAR_OF_SERVICE || "ONE_FORTH_HALF_YEAR_OF_SERVICE",
  EGOV_PENSION_BENEFIT_CODE_INVALID_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_INVALID_PENSION || "INVALID_PENSION",
  EGOV_PENSION_BENEFIT_CODE_WOUND_EXTRAORDINARY_PENSION: process.env.EGOV_PENSION_BENEFIT_CODE_WOUND_EXTRAORDINARY_PENSION || "WOUND_EXTRAORDINARY_PENSION",
  EGOV_PENSION_BENEFIT_CODE_ATTENDANT_ALLOWANCE: process.env.EGOV_PENSION_BENEFIT_CODE_ATTENDANT_ALLOWANCE || "ATTENDANT_ALLOWANCE",

  //tracer configurations
  TRACER_ENABLE_REQUEST_LOGGING: process.env.TRACER_ENABLE_REQUEST_LOGGING || false,

  //logger configurations
  HTTP_CLIENT_DETAILED_LOGGING_ENABLED: process.env.HTTP_CLIENT_DETAILED_LOGGING_ENABLED || false,

  //mdms service configurations
  EGOV_MDMS_HOST: process.env.EGOV_MDMS_HOST || "http://localhost:8094",
  EGOV_MDMS_CONTEXT_PATH: process.env.EGOV_MDMS_CONTEXT_PATH || "/egov-mdms-service/v1",
  EGOV_MDMS_SEARCH_ENPOINT: process.env.EGOV_MDMS_SEARCH_ENPOINT || "/_search",
  LOGGING_LEVEL: process.env.LOGGING_LEVEL || "debug"

};
exports.default = envVariables;
//# sourceMappingURL=envVariables.js.map