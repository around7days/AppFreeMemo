package rms.test.junit.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import rms.common.base.BusinessException;
import rms.common.dao.MUserRoleDao;
import rms.common.dao.VMUserDao;
import rms.common.entity.VMUser;
import rms.domain.app.mst.userregist.UserRegistDto;
import rms.domain.app.mst.userregist.UserRegistService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistServiceTest {

    @Autowired
    UserRegistService service;

    @Autowired
    VMUserDao vMUserDao;

    @Autowired
    MUserRoleDao mUserRoleDao;

    @Test
    public void ユーザ情報取得() {

        UserRegistDto dto = service.getUserInfo("user01");

        assertThat(dto.getUserId(), is("user01"));
        assertThat(dto.getPassword(), is("pass"));
        assertThat(dto.getUserNm(), is("申請者０１"));
        assertThat(dto.getEmail(), is("xxx@xxx.xx"));
        assertThat(dto.getDepartmentId(), is("1"));
        assertThat(dto.getApproveUserId1(), is("user06"));
        assertThat(dto.getApproveUserId2(), is("user07"));
        assertThat(dto.getApproveUserId3(), is("user08"));
        assertThat(dto.getRoleApplyFlg(), is("1"));
        assertThat(dto.getRoleApproveFlg(), is(nullValue()));
        assertThat(dto.getRoleAdminFlg(), is(nullValue()));
    }

    @Test
    public void ユーザ登録_重複チェックエラー() {

        UserRegistDto dto = new UserRegistDto();
        dto.setUserId("user01");

        try {
            service.regist(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("ユーザIDが重複しています"));
            return;
        }

        fail();
    }

    @Test
    public void ユーザ登録_承認ルートチェックエラー_承認者の重複している() {

        try {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId("XXXXXXXXX");
            dto.setRoleApplyFlg("1");
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user07");

            service.regist(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("承認者１～３に同じ承認者は設定できません"));
            return;
        }

        fail();
    }

    @Test
    public void ユーザ登録_承認ルートチェックエラー_役割が申請者で承認者３が設定されていない() {

        try {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId("XXXXXXXXX");
            dto.setRoleApplyFlg("1");
            dto.setApproveUserId1("");
            dto.setApproveUserId2("");
            dto.setApproveUserId3("");

            service.regist(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("役割が申請者の場合、承認者３は必須です"));
            return;
        }

        fail();
    }

    @Test
    public void ユーザ更新_承認ルートチェックエラー_承認者の重複している() {

        try {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId("XXXXXXXXX");
            dto.setRoleApplyFlg("1");
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user07");

            service.update(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("承認者１～３に同じ承認者は設定できません"));
            return;
        }

        fail();
    }

    @Test
    public void ユーザ更新_承認ルートチェックエラー_役割が申請者で承認者３が設定されていない() {

        try {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId("XXXXXXXXX");
            dto.setRoleApplyFlg("1");
            dto.setApproveUserId1("");
            dto.setApproveUserId2("");
            dto.setApproveUserId3("");

            service.update(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("役割が申請者の場合、承認者３は必須です"));
            return;
        }

        fail();
    }

    @WithUserDetails(value = "user11", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test(expected = OptimisticLockException.class)
    public void ユーザ更新_楽観的排他エラー() throws Exception {

        UserRegistDto dto = new UserRegistDto();
        dto.setUserId("user01");
        dto.setRoleApplyFlg("1");
        dto.setApproveUserId1("user06");
        dto.setApproveUserId2("user07");
        dto.setApproveUserId3("user08");
        dto.setVersion(Integer.MAX_VALUE);

        // 楽観的排他エラーが発生する
        service.update(dto);

        fail();
    }

    @WithUserDetails(value = "user11", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void ユーザ登録処理() throws Exception {

        String userId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS"));

        // ユーザ登録処理
        {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId(userId);
            dto.setPassword("pass");
            dto.setUserNm("テストユーザー");
            dto.setEmail("aaa@bbb.com");
            dto.setDepartmentId("1");
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user08");
            dto.setRoleApplyFlg("1");
            dto.setRoleApproveFlg("1");
            dto.setRoleAdminFlg("1");
            service.regist(dto);
        }

        // ユーザ登録情報を取得して正常に値が入っているか確認
        {
            VMUser vMUser = vMUserDao.selectById(userId);

            assertThat(vMUser.getUserId(), is(userId));
            assertThat(vMUser.getUserNm(), is("テストユーザー"));
            assertThat(vMUser.getPassword(), is("pass"));
            assertThat(vMUser.getEmail(), is("aaa@bbb.com"));
            assertThat(vMUser.getDepartmentId(), is("1"));
            assertThat(vMUser.getApproveUserId1(), is("user06"));
            assertThat(vMUser.getApproveUserId2(), is("user07"));
            assertThat(vMUser.getApproveUserId3(), is("user08"));

            assertThat(mUserRoleDao.existsById(userId, "ROLE_APPLY"), is(true));
            assertThat(mUserRoleDao.existsById(userId, "ROLE_APPROVE"), is(true));
            assertThat(mUserRoleDao.existsById(userId, "ROLE_ADMIN"), is(true));
        }
    }

    @WithUserDetails(value = "user11", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void ユーザ更新処理() throws Exception {

        String userId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS"));

        // 仮のユーザ登録処理
        {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId(userId);
            dto.setPassword("pass");
            dto.setUserNm("テストユーザー");
            dto.setEmail("");
            dto.setDepartmentId("");
            dto.setApproveUserId1("");
            dto.setApproveUserId2("");
            dto.setApproveUserId3("");
            dto.setRoleApplyFlg("");
            dto.setRoleApproveFlg("");
            dto.setRoleAdminFlg("");
            service.regist(dto);
        }

        // ユーザ更新処理
        {
            UserRegistDto dto = new UserRegistDto();
            dto.setUserId(userId);
            dto.setUserNm("テストユーザーＸＸＸＸ");
            dto.setEmail("aaa@bbb.com");
            dto.setDepartmentId("1");
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user08");
            dto.setRoleApplyFlg("1");
            dto.setRoleApproveFlg("1");
            dto.setRoleAdminFlg("1");
            dto.setVersion(0);
            service.update(dto);
        }

        // ユーザ登録情報を取得して正常に値が入っているか確認
        {
            VMUser vMUser = vMUserDao.selectById(userId);

            assertThat(vMUser.getUserId(), is(userId));
            assertThat(vMUser.getUserNm(), is("テストユーザーＸＸＸＸ"));
            assertThat(vMUser.getEmail(), is("aaa@bbb.com"));
            assertThat(vMUser.getDepartmentId(), is("1"));
            assertThat(vMUser.getApproveUserId1(), is("user06"));
            assertThat(vMUser.getApproveUserId2(), is("user07"));
            assertThat(vMUser.getApproveUserId3(), is("user08"));

            assertThat(mUserRoleDao.existsById(userId, "ROLE_APPLY"), is(true));
            assertThat(mUserRoleDao.existsById(userId, "ROLE_APPROVE"), is(true));
            assertThat(mUserRoleDao.existsById(userId, "ROLE_ADMIN"), is(true));

        }
    }

}
