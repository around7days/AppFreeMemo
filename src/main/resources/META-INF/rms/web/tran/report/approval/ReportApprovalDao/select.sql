select
  R.applicant_id
  , (select U.user_nm from M_USER U where R.applicant_id = U.user_id) as applicant_nm
  , R.target_ym
  , left(R.target_ym, 4) as target_year
  , right(R.target_ym, 2) as target_month
  , R.applicant_date
  , R.status
  , (select C.code_nm from M_CODE C where C.code_kbn = 'A001' and R.status = C.code) as status_nm
  , R.approver1_id
  , (select U.user_nm from M_USER U where R.approver1_id = U.user_id) as approver1_nm
  , R.approver2_id
  , (select U.user_nm from M_USER U where R.approver2_id = U.user_id) as approver2_nm
  , R.approver3_id
  , (select U.user_nm from M_USER U where R.approver3_id = U.user_id) as approver3_nm
  , R.file_path
  , R.version
from
  t_report R
where
    R.applicant_id = /* form.applicantId */'user01'
and R.target_ym = /* form.targetYm */'201607'