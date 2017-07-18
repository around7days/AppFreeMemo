select
    A.apply_user_id
  , A.apply_user_nm
  , A.target_ym
  , A.apply_date
  , A.department_rnm
  , A.approve_user_id1
  , A.approve_user_nm1
  , A.approve_user_id2
  , A.approve_user_nm2
  , A.approve_user_id3
  , A.approve_user_nm3
  , A.approve_user_id4
  , A.approve_user_nm4
  , A.file_path
  , A.status
  , A.status_nm
from
  v_t_report A
where
  A.del_flg = 0
/*%if condition.targetYm != null */
and A.target_ym = /* condition.targetYm */'201606'
/*%end */
order by
  A.target_ym desc
  , A.apply_user_id