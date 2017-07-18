select
  /*%expand*/*
from
  m_user_approve_flow
where
  user_id = /* userId */'a'
order by
  user_id, approve_seq
