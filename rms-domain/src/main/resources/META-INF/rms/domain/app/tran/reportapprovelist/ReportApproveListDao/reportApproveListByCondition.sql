select
    /*^ condition.approveUserId */'user07' as approve_user_id
  , A.apply_user_id
  , A.apply_user_nm
  , A.target_ym
  , A.apply_date
  , A.department_rnm
  , A.status
  , A.status_nm
  , A.approve_user_id1
  , A.approve_user_nm1
  , A.approve_user_id2
  , A.approve_user_nm2
  , A.approve_user_id3
  , A.approve_user_nm3
  , A.approve_user_id4
  , A.approve_user_nm4
from
  v_t_report A
where
  A.del_flg = 0
  and A.target_ym = /* condition.targetYm */'201606'
  and /* condition.approveUserId */'user07' in (
          A.approve_user_id1,
          A.approve_user_id2,
          A.approve_user_id3,
          A.approve_user_id4
      )
order by
  apply_user_id
