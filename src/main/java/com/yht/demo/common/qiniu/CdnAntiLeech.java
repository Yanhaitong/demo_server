package com.yht.demo.common.qiniu;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * cdn配置
 *
 * @author w
 */
public class CdnAntiLeech {

    /**
     * 生成资源基于CDN时间戳防盗链的访问外链
     *
     * @param url
     * @param encryptKey
     * @param durationInSeconds
     * @return
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String getAntiLeechAccessUrlBasedOnTimestamp(String url, String encryptKey, int durationInSeconds)
            throws MalformedURLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        URL urlObj = new URL(url);
        String path = urlObj.getPath();

        long timestampNow = System.currentTimeMillis() / 1000 + durationInSeconds;
        String expireHex = Long.toHexString(timestampNow);

        String toSignStr = String.format("%s%s%s", encryptKey, path, expireHex);
        String signedStr = md5ToLower(toSignStr);

        String signedUrl = null;
        if (urlObj.getQuery() != null) {
            signedUrl = String.format("%s&sign=%s&t=%s", url, signedStr, expireHex);
        } else {
            signedUrl = String.format("%s?sign=%s&t=%s", url, signedStr, expireHex);
        }
        return signedUrl;
    }

    public static String getUrlSecurity(String urlPre, String encryptKey, String fileKey, int durationInSeconds) {
        // cdn 配置的基于时间戳防盗链的加密字符串，cdn 配置完成后会得到
//		String encryptKey = "258867bb93983e8ae6a950b9988836a9bbbb05ab";
        // 待加密链接
//		String fileKey = "1479871036458019079.png";
        String encodedFileKey;
        String signedUrl = "";
        if (fileKey.indexOf("/") > -1) {
            fileKey = fileKey.substring(fileKey.lastIndexOf("/") + 1);
        }
        if (fileKey.indexOf("?") > -1) {
            fileKey = fileKey.substring(0, fileKey.indexOf("?"));
        }
        try {
            // 考虑到文件名称会有中文，所以需要做urlencode
            encodedFileKey = fileKey;//URLEncoder.encode(fileKey, "utf-8");
            String urlToSign = String.format(urlPre + "%s", encodedFileKey);
            signedUrl = CdnAntiLeech.getAntiLeechAccessUrlBasedOnTimestamp(urlToSign, encryptKey, durationInSeconds);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return signedUrl;
    }

    public static String getUrl(String urlPre, String encryptKey, String fileKey, int durationInSeconds) {
        // cdn 配置的基于时间戳防盗链的加密字符串，cdn 配置完成后会得到
//		String encryptKey = "258867bb93983e8ae6a950b9988836a9bbbb05ab";
        // 待加密链接
//		String fileKey = "1479871036458019079.png";
        String encodedFileKey;
        String signedUrl = "";
        if (fileKey.indexOf("/") > -1) {
            fileKey = fileKey.substring(fileKey.lastIndexOf("/") + 1);
        }
        if (fileKey.indexOf("?") > -1) {
            fileKey = fileKey.substring(0, fileKey.indexOf("?"));
        }
        // 考虑到文件名称会有中文，所以需要做urlencode
        encodedFileKey = fileKey;//URLEncoder.encode(fileKey, "utf-8");
        String urlToSign = String.format(urlPre + "%s", encodedFileKey);
        signedUrl = urlToSign;
        return signedUrl;
    }

    private static String md5ToLower(String src) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(src.getBytes("utf-8"));
        byte[] md5Bytes = digest.digest();
        return Hex.encodeHexString(md5Bytes);
    }
}