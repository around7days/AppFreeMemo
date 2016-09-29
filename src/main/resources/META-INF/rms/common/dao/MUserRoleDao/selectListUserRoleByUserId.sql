select
  /*%expand*/*
from
  m_user_role
where
  user_id = /* userId */'user01'
order by
  role