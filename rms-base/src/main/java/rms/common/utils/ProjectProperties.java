package rms.common.utils;

import java.time.LocalDate;

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
    /** 月度切替基準日 */
    @Value("${app.switch.month.reference.day}")
    private Integer switchMonthReferenceDay;
    /** 1ページ表示件数（デフォルト） */
    @Value("${app.page.limit.default}")
    private Integer PageLimitDefault;
    /** CSSテーマ（デフォルト） */
    @Value("${app.css.theme.default}")
    private String cssThemeDefault;
    /** システム日付 */
    @Value("${app.sysdate}")
    private String sysdate;
    /** SlackToken */
    @Value("${app.slack.token}")
    private String slackToken;

    public Boolean getHtml5Novalidate() {
        return html5Novalidate;
    }

    public String getTemporaryStorage() {
        return temporaryStorage;
    }

    public String getReportStorage() {
        return reportStorage;
    }

    public Integer getSwitchMonthReferenceDay() {
        return switchMonthReferenceDay;
    }

    public Integer getPageLimitDefault() {
        return PageLimitDefault;
    }

    public String getCssThemeDefault() {
        return cssThemeDefault;
    }

    public LocalDate getSysdate() {
        if (sysdate == null || sysdate.isEmpty()) {
            return LocalDate.now();
        }
        return LocalDate.parse(sysdate);
    }

    public String getSlackToken() {
        return slackToken;
    }
}