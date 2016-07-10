select
	A.user_id
	,A.user_nm
	,A.email
	,A.applicant_kbn
	,A.approver_kbn
	,A.admin_kbn
from
	m_user A
where
/*%if @isNotEmpty(form.userId) */
	A.user_id = /* form.userId */'user01'
/*%end */

/*%if @isNotEmpty(form.userNm) */
and A.user_nm like /* @infix(form.userNm) */'ユーザ０１'
/*%end */

/*%if @isNotEmpty(form.applicantKbn) */
and A.applicant_kbn = /* form.applicantKbn */'1'
/*%end */

/*%if @isNotEmpty(form.approvalKbn) */
and A.approval_kbn = /* form.approvalKbn */'1'
/*%end */

/*%if @isNotEmpty(form.adminKbn) */
and A.admin_kbn = /* form.adminKbn */'1'
/*%end */

and A.del_flg = 0