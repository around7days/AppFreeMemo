package rms.batch.app;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.batch.app.BatchMain;
import rms.common.exception.BusinessException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBatchApplication.class)
public class BatchMainTest {

    @Autowired
    BatchMain batchMain;

    @MockBean
    ApplicationArguments arguments;

    @Test
    public void test_call_パラメータエラー() throws Exception {
        try {
            batchMain.call();
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("パラメータにバッチIDが設定されていません"));
            return;
        }

        fail();
    }

    @Test
    public void test_call_バッチID不正() throws Exception {

        final String batchId = "dummy";

        // モック定義-----------------------------------------------------
        final List<String> mock = Arrays.asList("batch", batchId);
        given(this.arguments.getNonOptionArgs()).willReturn(mock);
        // ---------------------------------------------------------------

        try {
            batchMain.call();
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("バッチIDが不正です"));
            return;
        }

        fail();
    }

    @Test
    public void test_call_B001実行_成功() throws Exception {

        final String batchId = "B001";

        // モック定義-----------------------------------------------------
        final List<String> mock = Arrays.asList("batch", batchId);
        given(this.arguments.getNonOptionArgs()).willReturn(mock);
        // ---------------------------------------------------------------

        // 正常終了
        batchMain.call();
    }

}
