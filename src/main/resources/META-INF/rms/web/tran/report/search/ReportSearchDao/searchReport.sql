SELECT
    A.applicant_id
  ,(select U.user_nm from m_user U where A.applicant_id = U.user_id) as applicant_nm
  ,'ソリューション部' as department
  , A.target_ym
  , A.applicant_date
  , A.status
  ,(select A001.code_nm from m_code A001 where A001.code_kbn = 'A001' and A001.code = A.status)  as status_nm
  , A.approver1_id
  ,(select U.user_nm from m_user U where A.approver1_id = U.user_id) as approver1_nm
  , A.approver2_id
  ,(select U.user_nm from m_user U where A.approver2_id = U.user_id) as approver2_nm
  , A.approver3_id
  ,(select U.user_nm from m_user U where A.approver3_id = U.user_id) as approver3_nm
  , A.file_path
FROM
  t_report A
WHERE
  A.del_flg = 0
/*%if @isNotEmpty(form.applicantId) */
	A.applicant_id = /* form.applicantId */'user01'
/*%end */
ORDER BY
  A.applicant_id
  , A.target_ym
