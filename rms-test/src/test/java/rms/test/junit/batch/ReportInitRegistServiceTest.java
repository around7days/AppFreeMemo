package rms.test.junit.batch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.domain.app.batch.reportinitregist.ReportInitRegistService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBatchApplication.class)
public class ReportInitRegistServiceTest {

    @Autowired
    ReportInitRegistService service;

    @Test
    public void 月報初期データ登録処理_失敗() {
        Integer targetYm = 209912;
        boolean b = service.regist(targetYm);
        assertThat(b, is(false));
    }

    @Test
    public void 月報初期データ登録処理_成功() {
        Integer targetYm = 201705;
        boolean b = service.regist(targetYm);
        assertThat(b, is(true));
    }

}
