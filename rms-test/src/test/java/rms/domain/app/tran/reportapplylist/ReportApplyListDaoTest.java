package rms.domain.app.tran.reportapplylist;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.SpringWebApplication;
import rms.common.utils.PageInfo;
import rms.common.utils.SelectOptionsUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class ReportApplyListDaoTest {

    /* テスト対象 */
    @Autowired
    ReportApplyListDao dao;

    @BeforeClass
    public static void beforeAll() {
        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        application.setWebEnvironment(false); // 内臓tomcatの起動を抑制
    }

    @Test
    public void test_reportApplyListByCondition() {

        // パラメータ
        final ReportApplyListDto condition = new ReportApplyListDto();
        condition.setApplyUserId("user01");
        final SelectOptions options = SelectOptionsUtils.get(new PageInfo());

        // テスト実行
        List<ReportApplyListResultEntity> resultList = dao.reportApplyListByCondition(condition, options);

        // テスト結果確認
        assertThat(resultList.get(0).getApplyUserId(), is("user01"));
        assertThat(resultList.get(0).getTargetYm(), is(201609));
        assertThat(resultList.get(1).getApplyUserId(), is("user01"));
        assertThat(resultList.get(1).getTargetYm(), is(201608));
    }

}
