package rms.domain.app.system.login;

import rms.common.entity.TInfomation;

/**
 * ログイン画面サービスインタフェース
 * @author
 */
public interface LoginService {

    /**
     * お知らせ情報取得処理
     * @return
     */
    public TInfomation getInfomation();
}
