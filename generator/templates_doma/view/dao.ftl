<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.DaoDesc です -->
<#import "/lib.ftl" as lib>
<#if lib.copyright??>
${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

<#list importNames as importName>
import ${importName};
</#list>
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * ${simpleName}クラス
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
@Dao<#if configClassSimpleName??>(config = ${configClassSimpleName}.class)</#if>
@ConfigAutowireable
public interface ${simpleName} {

    /**
     * 1件取得
     * @return the <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName} entity
     */
    @Select
    <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName} selectById(String dummy);

    /**
     * 1件取得
     * @param options
     * @return the <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName} entity
     */
    @Select
    <#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityDesc.simpleName} selectById(String dummy, SelectOptions options);

}