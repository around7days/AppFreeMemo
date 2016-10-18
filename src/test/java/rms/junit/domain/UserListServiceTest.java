package rms.junit.domain;

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
    public void test1() {
        UserListDtoCondition condition = new UserListDtoCondition();
        condition.setUserId("user01");

        SearchResultDto<UserListEntityResult> resultDto = service.search(condition, new PageInfo());

        UserListEntityResult result = resultDto.getResultList().get(0);
        assertEquals(result.getUserNm(), "申請者０１");
    }
}
