SELECT
    A.applicant_id
  , A.applicant_nm
  , A.target_ym
  , A.application_date
  , A.publish_flg
  , A.publish_nm
  , A.status
  , A.status_nm
  , A.approver1_id
  , A.approver1_nm
  , A.approver2_id
  , A.approver2_nm
  , A.approver3_id
  , A.approver3_nm
  , A.file_path
FROM
  v_t_report A
WHERE
  A.del_flg = 0
/*%if @isNotEmpty(condition.applicantId) */
and A.applicant_id = /* condition.applicantId */'user01'
/*%end */
/*%if @isNotEmpty(condition.applicantNm) */
and A.applicant_nm like /* @infix(condition.applicantNm) */'%申請者%'
/*%end */
/*%if @isNotEmpty(condition.targetYm) */
and A.target_ym = /* condition.targetYm */'201606'
/*%end */
ORDER BY
  A.target_ym
  , A.applicant_id