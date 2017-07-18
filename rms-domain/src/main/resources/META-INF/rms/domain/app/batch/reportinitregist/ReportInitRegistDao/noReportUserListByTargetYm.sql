select
  u.*
from
  m_user u
where
  u.del_flg = 0
  and u.user_id not in (
        select r.apply_user_id
        from t_report r
        where r.target_ym = /* targetYm */201607
      )
  and u.user_id in (
        select ur.user_id
        from m_user_role ur
        where ur.role = 'ROLE_APPLY'
      )
order by
  u.user_id
