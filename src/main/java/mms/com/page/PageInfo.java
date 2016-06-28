package mms.com.page;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ページ情報
 * @author
 */
public class PageInfo {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PageInfo.class);

    /** 1ページ表示件数（デフォルト） */
    private static final int DEFAULT_LIMIT = 5;

    /** 1ページ表示件数 */
    private int limit = DEFAULT_LIMIT;

    /** 表示ページ */
    private int page = 1;

    /** 合計表示件数 */
    private int totalSize = 0;

    /*-----------------------------------------------------------------------*/
    /**
     * 1ページ表示件数を設定します。
     * @param limit 1ページ表示件数
     */
    public void setLimit(int limit) {
        if (limit < 1) {
            logger.error("limit to {} can not be assigned", limit);
            throw new RuntimeException("limit to " + limit + "can not be assigned");
        }
        this.limit = limit;
    }

    /**
     * 表示ページを設定します。
     * @param page 表示ページ
     */
    public void setPage(int page) {
        if (page < 1) {
            logger.error("page to {} can not be assigned", page);
            throw new RuntimeException("page to " + page + "can not be assigned");
        }
        this.page = page;
    }

    /**
     * 合計表示件数を設定します。
     * @param totalSize 合計表示件数
     */
    public void setTotalSize(Long totalSize) {
        // TODO intの上限を超えるとエラーが発生する。
        setTotalSize(new Integer(totalSize.toString()));
    }

    /**
     * 合計表示件数を設定します。
     * @param totalSize 合計表示件数
     */
    public void setTotalSize(int totalSize) {
        if (totalSize < 0) {
            logger.warn("totalSize to {} can not be assigned", totalSize);
            throw new RuntimeException("totalSize to " + totalSize + "can not be assigned");
        }
        this.totalSize = totalSize;
    }

    /**
     * 前ページに移動します。
     */
    public void prev() {
        setPage(this.page - 1);
    }

    /**
     * 次ページに移動します。
     */
    public void next() {
        setPage(this.page + 1);
    }

    /*-----------------------------------------------------------------------*/

    /**
     * 1ページ表示件数を取得します。
     * @return 1ページ表示件数
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 表示ページを取得します。
     * @return 表示ページ
     */
    public int getPage() {
        if (totalSize <= 0) {
            // データ件数が0件の場合
            return 1;
        } else if (limit * (page - 1) > totalSize - 1) {
            // データ件数が減り、現在ページを表示できない場合、表示ページを最終ページとして返却
            return getTotalPage();
        } else {
            // 上記以外
            return page;
        }
    }

    /**
     * 総ページ数を取得する。
     * @return 総ページ数
     */
    public int getTotalPage() {
        BigDecimal value = new BigDecimal(totalSize);
        value = value.divide(new BigDecimal(limit), BigDecimal.ROUND_UP);
        return value.intValue();
    }

    /**
     * 表示開始Noを取得します。
     * @return 表示開始Index
     */
    public int getStartIndex() {
        if (totalSize <= 0) {
            return 0;
        }
        return (getPage() * limit) - (limit - 1);
    }

    /**
     * 表示終了Noを取得します。
     * @return 表示終了Index
     */
    public int getEndIndex() {
        if (totalSize < (getPage() * limit)) {
            return totalSize;
        }
        return getPage() * limit;
    }

    /**
     * 合計表示件数を取得します。
     * @return 合計表示件数
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * 前ページ有無を取得します。
     * @return 前ページ有無
     */
    public boolean getHasPrev() {
        if (getPage() <= 1) {
            return false;
        }
        return true;
    }

    /**
     * 次ページ有無を取得します。
     * @return 次ページ有無
     */
    public boolean getHasNext() {
        if (getPage() >= getTotalPage()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
