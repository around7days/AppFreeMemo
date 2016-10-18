package rms.junit.common;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.common.base.BusinessException;
import rms.common.consts.MessageEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessExceptionTest {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(BusinessExceptionTest.class);

    @Test
    public void メッセージ取得_Enum_引数なし() {
        BusinessException e = new BusinessException(MessageEnum.info001);
        printLog(e);
        assertEquals(e.getErrorMessage(), "登録が完了しました");
    }

    @Test
    public void メッセージ取得_Enum_引数あり() {
        BusinessException e = new BusinessException(MessageEnum.error001, "ユーザID");
        printLog(e);
        assertEquals(e.getErrorMessage(), "ユーザIDが重複しています");
    }

    @Test
    public void メッセージ取得_値を直接() {
        BusinessException e = new BusinessException("登録が完了しました");
        printLog(e);
        assertEquals(e.getErrorMessage(), "登録が完了しました");
    }

    private void printLog(BusinessException e) {
        logger.debug("e.getErrorCode()       -> {}", e.getErrorCode());
        logger.debug("e.getErrorMessage()    -> {}", e.getErrorMessage());
    }

}
