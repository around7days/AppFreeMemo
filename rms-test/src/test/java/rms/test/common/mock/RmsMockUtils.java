package rms.test.common.mock;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import rms.common.utils.RmsMailTest;

/**
 * MockUtilsクラス
 * @author
 */
public class RmsMockUtils {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RmsMockUtils.class);

    /**
     * ダミーファイルのモック作成
     * @param type
     * @return
     */
    public static MultipartFile getDummyMultipartFile(RmsMockFileType type) {

        MultipartFile file;
        try {
            InputStream stream = RmsMockUtils.class.getClassLoader().getResource(type.getResourcePath()).openStream();
            file = new MockMultipartFile("dummy", type.getFileNm(), null, stream);
        } catch (IOException e) {
            logger.warn("create mock file faild.", e);
            file = null;
        }

        return file;
    }

    /**
     * ダミーファイルパスの取得
     * @param type
     * @return
     */
    public static String getDummyFilePath(RmsMockFileType type) {
        String filePath = RmsMailTest.class.getClassLoader().getResource(type.getResourcePath()).getPath();
        return filePath;
    }
}
