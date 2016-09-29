select
  /*%expand*/*
from
  m_code
where
  code_kbn = /* codeKbn */'A001'
  and del_flg = 0
order by
  code
