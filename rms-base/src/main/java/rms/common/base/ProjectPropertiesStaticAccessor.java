package rms.common.base;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ProjectPropertiesAccessor<br>
 * 通常はProjectPropertiesを使用するが、<br>
 * どうしてもstaticアクセスが必要な時に使用
 */
@Component
public class ProjectPropertiesStaticAccessor {

    /** ProjectProperties */
    public static ProjectProperties properties;

    @Autowired
    private ProjectProperties wiredProperties;

    @PostConstruct
    public void init() {
        ProjectPropertiesStaticAccessor.properties = wiredProperties;
    }
}