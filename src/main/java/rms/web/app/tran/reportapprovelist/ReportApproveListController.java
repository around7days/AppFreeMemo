package rms.web.app.tran.reportapprovelist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import rms.common.auth.UserInfo;
import rms.common.consts.MessageEnum;
import rms.common.dto.SearchResultDto;
import rms.common.utils.BeanUtils;
import rms.common.utils.FileUtils;
import rms.common.utils.PageInfo;
import rms.domain.app.shared.dto.ReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportapprovelist.ReportApproveListDtoCondition;
import rms.domain.app.tran.reportapprovelist.ReportApproveListEntityResult;
import rms.domain.app.tran.reportapprovelist.ReportApproveListService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportapprovelist.ReportApproveListForm.BulkDownload;
import rms.web.app.tran.reportapprovelist.ReportApproveListForm.Search;
import rms.web.app.tran.reportapproveregist.ReportApproveRegistController;
import rms.web.app.tran.reportapproveregistbulk.ReportApproveRegistBulkController;

/**
 * 月報承認状況一覧画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = ReportApproveListForm.class)
public class ReportApproveListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApproveListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportApproveList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportapprovelist";

    /** 月報情報取得サービス */
    @Autowired
    ReportApproveListService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    SharedReportFileService sharedReportFileService;

    /**
     * 月報承認状況一覧画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportApproveListForm setupForm() {
        return new ReportApproveListForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(ReportApproveListForm form,
                       Model model) {
        return PAGE_URL;
    }

    /**
     * 検索処理
     * @param form
     * @param userInfo
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(@Validated(Search.class) ReportApproveListForm form,
                         BindingResult bindingResult,
                         @AuthenticationPrincipal UserInfo userInfo,
                         Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 検索結果・ページ情報の初期化
        int pageLimit = Integer.MAX_VALUE; // 件数を無制限に設定
        form.setPageInfo(new PageInfo(pageLimit));
        form.setResultList(null);

        // 検索条件の生成
        ReportApproveListDtoCondition condition = new ReportApproveListDtoCondition();
        BeanUtils.copyProperties(form.getCondition(), condition);
        condition.setApproveUserId(userInfo.getUserId());

        // 検索処理
        SearchResultDto<ReportApproveListEntityResult> resultDto = service.search(condition, form.getPageInfo());
        if (resultDto.getResultList().isEmpty()) {
            // 「検索結果が見つかりません」
            bindingResult.reject(MessageEnum.error006.name());
            return PAGE_URL;
        }

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        return PAGE_URL;
    }

    /**
     * 再検索処理
     * @param form
     * @param userInfo
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(ReportApproveListForm form,
                           @AuthenticationPrincipal UserInfo userInfo,
                           Model model) {
        logger.debug("フォーム情報 -> {}", form);

        if (form.getPageInfo().getTotalSize() == 0) {
            // 検索実績なしのため、再検索を行わない
            return PAGE_URL;
        }

        // 検索条件の生成
        ReportApproveListDtoCondition condition = new ReportApproveListDtoCondition();
        BeanUtils.copyProperties(form.getCondition(), condition);
        condition.setApproveUserId(userInfo.getUserId());

        // 検索処理
        SearchResultDto<ReportApproveListEntityResult> resultDto = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        return PAGE_URL;
    }

    /**
     * 前ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pagePrev")
    public String pagePrev(ReportApproveListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 次ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pageNext")
    public String pageNext(ReportApproveListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 一括承認ボタン押下処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "bulkApprove")
    public String bulkApprove() {
        return redirect(ReportApproveRegistBulkController.MAPPING_URL, "init");
    }

    /**
     * 月報DL処理
     * @param form
     * @param index
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "download")
    public String download(ReportApproveListForm form,
                           @PathVariable int index,
                           HttpServletResponse response,
                           Model model) throws IOException {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportApproveListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報ファイルダウンロード情報生成
        ReportFileDto dto = sharedReportFileService.getReportFileDownloadInfo(entity.getApplyUserId(),
                                                                              entity.getApplyUserNm(),
                                                                              entity.getTargetYm());
        // 月報ダウンロード
        FileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());

        return null;
    }

    /**
     * 月報一括DL処理
     * @param form
     * @param bindingResult
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "bulkDownload")
    public String bulkDownload(@Validated(BulkDownload.class) ReportApproveListForm form,
                               BindingResult bindingResult,
                               HttpServletResponse response,
                               Model model) throws IOException {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 選択した月報indexの取得
        Integer[] checks = form.getReportDLCheck();

        // 月報ファイル一覧の生成
        List<String> applyUserIdList = new ArrayList<>();
        List<String> applyUserNmList = new ArrayList<>();
        List<Integer> targetYmList = new ArrayList<>();
        for (int i : checks) {
            ReportApproveListEntityResult entity = form.getResultList().get(i);
            applyUserIdList.add(entity.getApplyUserId());
            applyUserNmList.add(entity.getApplyUserNm());
            targetYmList.add(entity.getTargetYm());
        }

        // 月報ファイル一括ダウンロード情報生成
        ReportFileDto dto = sharedReportFileService.createReportFileBulkDownloadInfo(applyUserIdList,
                                                                                     applyUserNmList,
                                                                                     targetYmList,
                                                                                     checks.length);
        // 月報ダウンロード
        FileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());

        return null;
    }

    /**
     * 選択処理
     * @param form
     * @param index
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "select")
    public String select(ReportApproveListForm form,
                         @PathVariable int index,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportApproveListEntityResult entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        // 月報承認画面
        return redirect(ReportApproveRegistController.MAPPING_URL + "/" + entity.getApplyUserId() + "/"
                        + entity.getTargetYm(), "init");
    }

    /**
     * 戻る処理
     * @param sessionStatus
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back(SessionStatus sessionStatus) {
        // セッション破棄
        sessionStatus.setComplete();
        return redirect(MenuController.MAPPING_URL);
    }

}
