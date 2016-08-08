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
    A.apply_user_id
  , A.apply_user_nm
  , A.target_ym
  , A.apply_date
  , A.publish_flg
  , A.publish_nm
  , A.status
  , A.status_nm
  , case
      when A.approve_user_id1 = /* condition.approveUserId */'user07' then
        case A.status
          when 'Y01' then '2'         -- 入力中
          when 'Y02' then '3'         -- 入力済み
          when 'Y03' then '3'         -- 入力済み
          when '100' then '3'         -- 入力済み
          else            '1'         -- 依頼待ち
        end
      when A.approve_user_id2 = /* condition.approveUserId */'user07' then
        case A.status
          when 'Y01' then '1'         -- 依頼待ち
          when 'Y02' then '2'         -- 入力中
          when 'Y03' then '3'         -- 入力済み
          when '100' then '3'         -- 入力済み
          else            '1'         -- 依頼待ち
        end
      when A.approve_user_id3 = /* condition.approveUserId */'user07' then
        case A.status
          when 'Y01' then '1'         -- 依頼待ち
          when 'Y02' then '1'         -- 依頼待ち
          when 'Y03' then '2'         -- 入力中
          when '100' then '3'         -- 入力済み
          else            '1'         -- 依頼待ち
        end
    end as approve_report_status -- 承認状況（承認者視点）
  , A.approve_user_id1
  , A.approve_user_nm1
  , A.approve_user_id2
  , A.approve_user_nm2
  , A.approve_user_id3
  , A.approve_user_nm3
  , A.file_path
from
  v_t_report A
where
  A.del_flg = 0
  and A.target_ym = /* condition.targetYm */'201606'
  and /* condition.approveUserId */'user07' in (A.approve_user_id1, A.approve_user_id2, A.approve_user_id3)


union all

--
-- 月報未提出
--
select
    M.user_id
  , M.user_nm
  , null
  , null
  , null
  , null
  , 'XXX'
  , (select A001.code_nm from m_code A001 where A001.code_kbn = 'A001' and A001.code = 'XXX')
  , '1' -- 承認状況（承認者視点） 依頼待ち
  , M.approve_user_id1
  , M.approve_user_nm1
  , M.approve_user_id2
  , M.approve_user_nm2
  , M.approve_user_id3
  , M.approve_user_nm3
  , null
from
  v_m_user M
where
  M.del_flg = 0
  and /* condition.approveUserId */'user07' in (M.approve_user_id1, M.approve_user_id2, M.approve_user_id3)
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