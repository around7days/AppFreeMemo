package rms.domain.app.mst.userlist;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringWebApplication;
import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;
import rms.domain.app.mst.userlist.UserListDtoCondition;
import rms.domain.app.mst.userlist.UserListEntityResult;
import rms.domain.app.mst.userlist.UserListService;

@RunWith(Enclosed.class)
public class UserListServiceTest {

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringWebApplication.class)
    public static class searchInterface {

        @Autowired
        UserListService service;

        @Test
        public void ユーザ検索_1件() {
            UserListDtoCondition condition = new UserListDtoCondition();
            condition.setUserId("user01");

            SearchResultDto<UserListEntityResult> resultDto = service.search(condition,
                                                                             new PageInfo(Integer.MAX_VALUE));

            UserListEntityResult result = resultDto.getResultList().get(0);
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
        public void ユーザ検索_該当なし() {
            UserListDtoCondition condition = new UserListDtoCondition();
            condition.setUserId("userXX");

            SearchResultDto<UserListEntityResult> resultDto = service.search(condition,
                                                                             new PageInfo(Integer.MAX_VALUE));

            assertThat(resultDto.getCount(), is(Long.valueOf(0)));
        }

    }

}
