package rms.common.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * application.propertiesクラス<br>
 */
@Component
public class ProjectProperties {

    /** HTML5入力チェック有無[true:チェックしない false:チェックする] */
    @Value("${app.html5.novalidate}")
    private Boolean html5Novalidate;
    /** 一時フォルダパス */
    @Value("${app.temporary.storage}")
    private String temporaryStorage;
    /** 月報格納フォルダパス */
    @Value("${app.report.storage}")
    private String reportStorage;
    /** 月報提出可能日 */
    @Value("${app.report.apply.possible.day}")
    private Integer reportApplyPossibleDay;
    /** 1ページ表示件数（デフォルト） */
    @Value("${app.page.limit.default}")
    private Integer PageLimitDefault;
    /** CSSテーマ（デフォルト） */
    @Value("${app.css.theme.default}")
    private String cssThemeDefault;

    public Boolean getHtml5Novalidate() {
        return html5Novalidate;
    }

    public String getTemporaryStorage() {
        return temporaryStorage;
    }

    public String getReportStorage() {
        return reportStorage;
    }

    public Integer getReportApplyPossibleDay() {
        return reportApplyPossibleDay;
    }

    public Integer getPageLimitDefault() {
        return PageLimitDefault;
    }

    public String getCssThemeDefault() {
        return cssThemeDefault;
    }
}