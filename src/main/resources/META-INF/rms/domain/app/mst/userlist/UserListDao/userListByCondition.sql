select
	u.user_id
	,u.user_nm
	,u.email
	,u.approve_user_id1
	,u.approve_user_nm1
	,u.approve_user_id2
	,u.approve_user_nm2
	,u.approve_user_id3
	,u.approve_user_nm3
	,mr1.role_nm as role_nm1
	,mr2.role_nm as role_nm2
	,mr3.role_nm as role_nm3
from
	v_m_user u
	left join v_m_user_role mr1
		on u.user_id = mr1.user_id
		and mr1.role = 'ROLE_APPLY'
	left join v_m_user_role mr2
		on u.user_id = mr2.user_id
		and mr2.role = 'ROLE_APPROVE'
	left join v_m_user_role mr3
		on u.user_id = mr3.user_id
		and mr3.role = 'ROLE_ADMIN'
where
/*%if @isNotEmpty(condition.userId) */
	u.user_id = /* condition.userId */'user01'
/*%end */

/*%if @isNotEmpty(condition.userNm) */
and u.user_nm like /* @infix(condition.userNm) */'申請者０１'
/*%end */

and u.del_flg = 0