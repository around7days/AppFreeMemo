package rms.test.junit.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.common.dto.SearchResultDto;
import rms.common.utils.PageInfo;
import rms.domain.app.mst.userlist.UserListDtoCondition;
import rms.domain.app.mst.userlist.UserListEntityResult;
import rms.domain.app.mst.userlist.UserListService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserListServiceTest {

    @Autowired
    UserListService service;

    @Test
    public void ユーザ検索_1件() {
        UserListDtoCondition condition = new UserListDtoCondition();
        condition.setUserId("user01");

        SearchResultDto<UserListEntityResult> resultDto = service.search(condition, new PageInfo());

        UserListEntityResult result = resultDto.getResultList().get(0);
        assertThat(result.getUserNm(), is("申請者０１"));
    }

    @Test
    public void ユーザ検索_該当なし() {
        UserListDtoCondition condition = new UserListDtoCondition();
        condition.setUserId("userXX");

        SearchResultDto<UserListEntityResult> resultDto = service.search(condition, new PageInfo());
        assertThat(resultDto.getCount(), is(0));
    }

}
