SELECT
    A.user_id as 'id'
  , A.user_nm as 'name'
FROM
  m_user A
WHERE
  A.approval_kbn = '1'
ORDER BY
  A.user_id
