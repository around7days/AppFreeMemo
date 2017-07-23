package rms.common.abstracts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rms.common.utils.ProjectProperties;

/**
 * AbstractController
 * @author
 */
public abstract class AbstractBatch {

    /** application.properties */
    @Autowired
    protected ProjectProperties properties;

    /**
     * バッチ実行
     * @param args
     * @throws Exception
     */
    public abstract void execute(List<String> args) throws Exception;

    /**
     * バッチIDの取得
     * @return
     */
    protected abstract String getBatchId();
}
