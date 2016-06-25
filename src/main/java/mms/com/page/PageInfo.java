package mms.com.page;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ページ情報
 * @author
 */
public class PageInfo {
    private static final int DEFAULT_LIMIT = 5;

    /** 表示ページ */
    private int page = 1;

    /** 1ページ表示件数 */
    private int limit = DEFAULT_LIMIT;

    /** 表示開始No */
    private int startNo = 1;

    /** 表示終了No */
    private int endNo;

    /** 合計表示件数 */
    private Long totalSize;

    /** 前ページ有無 */
    private boolean hasPrev;

    /** 次ページ有無 */
    private boolean hasNext;

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 表示ページを取得します。
     * @return 表示ページ
     */
    public int getPage() {
        return page;
    }

    /**
     * 表示ページを設定します。
     * @param page 表示ページ
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 1ページ表示件数を取得します。
     * @return 1ページ表示件数
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 1ページ表示件数を設定します。
     * @param limit 1ページ表示件数
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 表示開始Noを取得します。
     * @return 表示開始No
     */
    public int getStartNo() {
        return startNo;
    }

    /**
     * 表示開始Noを設定します。
     * @param startNo 表示開始No
     */
    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }

    /**
     * 表示終了Noを取得します。
     * @return 表示終了No
     */
    public int getEndNo() {
        return endNo;
    }

    /**
     * 表示終了Noを設定します。
     * @param endNo 表示終了No
     */
    public void setEndNo(int endNo) {
        this.endNo = endNo;
    }

    /**
     * 合計表示件数を取得します。
     * @return 合計表示件数
     */
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * 合計表示件数を設定します。
     * @param totalSize 合計表示件数
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * 前ページ有無を取得します。
     * @return 前ページ有無
     */
    public boolean isHasPrev() {
        return hasPrev;
    }

    /**
     * 前ページ有無を設定します。
     * @param hasPrev 前ページ有無
     */
    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    /**
     * 次ページ有無を取得します。
     * @return 次ページ有無
     */
    public boolean isHasNext() {
        return hasNext;
    }

    /**
     * 次ページ有無を設定します。
     * @param hasNext 次ページ有無
     */
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

}
