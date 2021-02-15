CREATE TABLE public.fire_noc_data_details
(
  department_id character varying(128) NOT NULL,
  department_name character varying(128),
  kiosk_details character varying(128),
  kiosk_registerid character varying(128),
  no_of_attachment character varying(128),
  offline_ref_no character varying(128),
  payment_date character varying(128),
  payment_mode character varying(128),
  reference_no character varying(128),
  registration_id character varying(128),
  service_id character varying(128),
  service_name character varying(128),
  sub_version character varying(128),
  version_no character varying(128),
  amount character varying(128),
  appl_id character varying(128),
  appl_ref_no character varying(128),
  submission_date character varying(128),
  submission_location character varying(128),
  submission_mode character varying(128),
  applied_by character varying(128),
  base_service_id character varying(128),
  enclosure_details jsonb,
  attribute_details jsonb,
  is_active boolean,
  created_by character varying(64),
  created_time bigint,
  last_modified_by character varying(64),
  last_modified_time bigint,
  CONSTRAINT constraint_name UNIQUE (department_id)
);


CREATE TABLE public.fire_noc_execution_data_details
(
  service_id character varying(128) NOT NULL,
  appl_id character varying(128),
  current_process_id character varying(128),
  task_id character varying(128),
  task_name character varying(128),
  user_name character varying(128),
  task_type character varying(128),
  pull_user_id character varying(128),
  action_taken character varying(128),
  action_no character varying(128),
  callback_curr_proc_id character varying(128),
  executed_time character varying(128),
  received_time character varying(128),
  payment_mode character varying(128),
  payment_ref_no character varying(128),
  payment_date character varying(128),
  amount character varying(128),
  remarks character varying(128),
  user_detail jsonb,
  official_form_details jsonb,
  applicant_task_details jsonb,
  is_active boolean,
  created_by character varying(64),
  created_time bigint,
  last_modified_by character varying(64),
  last_modified_time bigint
)