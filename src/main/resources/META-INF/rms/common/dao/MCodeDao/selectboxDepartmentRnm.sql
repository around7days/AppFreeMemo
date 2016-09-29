select
    c.code as 'key'
  , c.attr1 as 'value'
from
  m_code c
where
  c.code_kbn = 'D001'
  and c.del_flg = 0
order by
  c.code
