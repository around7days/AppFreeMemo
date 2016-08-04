SELECT
    A.apply_user_id
  , A.apply_nm
  , A.target_ym
  , A.apply_date
  , A.publish_flg
  , A.publish_nm
  , A.status
  , A.status_nm
  , A.approve_user_id1
  , A.approve_user_nm1
  , A.approve_user_id2
  , A.approve_user_nm2
  , A.approve_user_id3
  , A.approve_user_nm3
  , A.file_path
FROM
  v_t_report A
WHERE
  A.del_flg = 0
/*%if @isNotEmpty(condition.applyUserId) */
and A.apply_user_id = /* condition.applyUserId */'user01'
/*%end */
/*%if @isNotEmpty(condition.applyNm) */
and A.apply_nm like /* @infix(condition.applyNm) */'%申請者%'
/*%end */
/*%if @isNotEmpty(condition.targetYm) */
and A.target_ym = /* condition.targetYm */'201606'
/*%end */
ORDER BY
  A.target_ym desc
  , A.apply_user_id