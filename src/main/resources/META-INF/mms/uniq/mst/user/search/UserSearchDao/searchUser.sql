select
	A.user_id
	,A.user_nm
	,A.email
from
	m_user A
where
/*%if @isNotEmpty(form.userId) */
	A.user_id = /* form.userId */'user01'
/*%end */
/*%if @isNotEmpty(form.userNm) */
and A.user_nm like /* @infix(form.userNm) */'ユーザー０１'
/*%end */
and A.del_flg = 0