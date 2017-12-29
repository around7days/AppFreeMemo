package rms.domain.app.mst.userlist;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.SpringWebApplication;
import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class UserListServiceTest {

    @Autowired
    UserListService service;

    @BeforeClass
    public static void beforeAll() {
        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        application.setWebEnvironment(false); // 内臓tomcatの起動を抑制
    }

    @Test
    public void test_search_ユーザ検索_1件() {
        UserListDto condition = new UserListDto();
        condition.setUserId("user01");

        SearchResultDto<UserListResultEntity> resultDto = service.search(condition, new PageInfo(Integer.MAX_VALUE));

        UserListResultEntity result = resultDto.getResultList().get(0);
        assertThat(result.getUserId(), is("user01"));
        assertThat(result.getUserNm(), is("申請者０１"));
        assertThat(result.getEmail(), is("xxx@xxx.xx"));
        assertThat(result.getDepartmentRnm(), is("1SOL"));
        assertThat(result.getApproveUserId1(), is("user06"));
        assertThat(result.getApproveUserId2(), is("user07"));
        assertThat(result.getApproveUserId3(), is("user08"));
        assertThat(result.getApproveUserId4(), is("user09"));
        assertThat(result.getRoleNm(), is("申請者"));
    }

    @Test
    public void test_search_ユーザ検索_該当なし() {
        UserListDto condition = new UserListDto();
        condition.setUserId("userXX");

        SearchResultDto<UserListResultEntity> resultDto = service.search(condition, new PageInfo(Integer.MAX_VALUE));

        assertThat(resultDto.getCount(), is(Long.valueOf(0)));
    }

}
