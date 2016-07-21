select
	u.user_id
	,u.user_nm
	,u.email
	,mr1.role_nm as role_nm1
	,mr2.role_nm as role_nm2
	,mr3.role_nm as role_nm3
from
	m_user u
	left join v_m_user_role mr1
		on u.user_id = mr1.user_id
		and mr1.role_id = 1
	left join v_m_user_role mr2
		on u.user_id = mr2.user_id
		and mr2.role_id = 2
	left join v_m_user_role mr3
		on u.user_id = mr3.user_id
		and mr3.role_id = 3
where
/*%if @isNotEmpty(condition.userId) */
	u.user_id = /* condition.userId */'user01'
/*%end */

/*%if @isNotEmpty(condition.userNm) */
and u.user_nm like /* @infix(condition.userNm) */'申請者０１'
/*%end */

and u.del_flg = 0