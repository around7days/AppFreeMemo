SELECT
    A.user_id as 'key'
  , A.user_nm as 'value'
FROM
  m_user A
  inner join m_user_role B
    on A.user_id = B.user_id
    and B.role_id = 1
WHERE
  A.del_flg = 0
ORDER BY
  A.user_id
