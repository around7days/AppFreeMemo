package rms.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メール関連Beanクラス
 * @author
 */
public class RmsMailInfo {

    /** logger */
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(RmsMailInfo.class);

    private InternetAddress fromAddr;
    private List<InternetAddress> toAddrs = new ArrayList<>();
    private List<InternetAddress> ccAddrs = new ArrayList<>();
    private List<InternetAddress> bccAddrs = new ArrayList<>();
    private String subject;
    private String text;
    private String fileNm;
    private File file;

    public InternetAddress getFromAddr() {
        return fromAddr;
    }

    public RmsMailInfo setFromAddr(String fromAddr) throws AddressException {
        this.fromAddr = new InternetAddress(fromAddr);
        return this;
    }

    public InternetAddress[] getToAddrs() {
        return toAddrs.toArray(new InternetAddress[toAddrs.size()]);
    }

    public RmsMailInfo addToAddr(String toAddrs) throws AddressException {
        this.toAddrs.add(new InternetAddress(toAddrs));
        return this;
    }

    public InternetAddress[] getCcAddrs() {
        return ccAddrs.toArray(new InternetAddress[ccAddrs.size()]);
    }

    public RmsMailInfo addCcAddr(String ccAddrs) throws AddressException {
        this.ccAddrs.add(new InternetAddress(ccAddrs));
        return this;
    }

    public InternetAddress[] getBccAddrs() {
        return bccAddrs.toArray(new InternetAddress[bccAddrs.size()]);
    }

    public RmsMailInfo addBccAddr(String bccAddrs) throws AddressException {
        this.bccAddrs.add(new InternetAddress(bccAddrs));
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public RmsMailInfo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getText() {
        return text;
    }

    public RmsMailInfo setText(String text) {
        this.text = text;
        return this;
    }

    public String getFileNm() {
        return fileNm;
    }

    public RmsMailInfo setFileNm(String fileNm) {
        this.fileNm = fileNm;
        return this;
    }

    public File getFile() {
        return file;
    }

    public RmsMailInfo setFilePath(String filePath) {
        this.file = new File(filePath);
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
