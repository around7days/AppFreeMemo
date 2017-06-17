/*----------------------------------------------------------
union使用時のlimit対策として全体をサブクエリとして囲む
----------------------------------------------------------*/
select AAA.*
from
(
--
-- 月報提出済み
--
select
    /*^ condition.approveUserId */'user07' as approve_user_id
  , A.apply_user_id
  , A.apply_user_nm
  , A.target_ym
  , A.apply_date
  , A.publish_flg
  , A.publish_flg_nm
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
  , A.file_path
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


union all

--
-- 月報未提出
--
select
    /*^ condition.approveUserId */'user07' as approve_user_id
  , M.user_id
  , M.user_nm
  , null
  , null
  , null
  , null
  , 'AAA'
  , (select A001.code_nm from m_code A001 where A001.code_kbn = 'A001' and A001.code = 'AAA')
  , M.approve_user_id1
  , M.approve_user_nm1
  , M.approve_user_id2
  , M.approve_user_nm2
  , M.approve_user_id3
  , M.approve_user_nm3
  , M.approve_user_id4
  , M.approve_user_nm4
  , null
from
  v_m_user M
where
  M.del_flg = 0
  and /* condition.approveUserId */'user07' in (
          M.approve_user_id1,
          M.approve_user_id2,
          M.approve_user_id3,
          M.approve_user_id4
      )
  and not exists (
        select
            'X'
        from
          t_report X
        where
          X.apply_user_id = M.user_id
          and X.target_ym = /* condition.targetYm */'201606'
          and X.del_flg = 0
      )
order by
  apply_user_id
) AAA