package rms.testutil.mock;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * モックファイル反映エディター
 */
public class RmsMockFileEditor extends PropertyEditorSupport {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RmsMockFileEditor.class);

    private final RmsMockFileType type;

    public RmsMockFileEditor(RmsMockFileType type) {
        this.type = type;
    }

    @Override
    public void setValue(Object obj) {
        MultipartFile mockFile = RmsMockUtils.getDummyMultipartFile(type);
        logger.debug("set mock file -> {}", this.type.getFileNm());
        super.setValue(mockFile);
    }

}
