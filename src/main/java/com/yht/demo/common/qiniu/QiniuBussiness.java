package com.yht.demo.common.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import com.yht.demo.common.Constant;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class QiniuBussiness {
    private static final Logger log = LoggerFactory.getLogger(QiniuBussiness.class);
    //private static String ACCESS_KEY="NvkFgkqE8DGZdGvVp14v3TWdua0zyCyCBe4WYACE";
    private static String ACCESS_KEY = Constant.QINIU_ACCESS_KEY;
    //private static String SECRET_KEY="UzxjKBynHA2D4Jg13oWTJBZjleTEwQAOGUv4vRei";
    private static String SECRET_KEY = Constant.QINIU_SECRET_KEY;

    public static String getUploadToken(String BUCKET, String KEY) {
        try {
            //密钥配置
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            return auth.uploadToken(BUCKET, KEY);//, 30, new StringMap()
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 七牛上传文件
     *
     * @param bytes
     * @param bucket 空间名
     * @param key    文件名(默认不指定key的情况下，以文件内容的hash值作为文件名)
     * @return
     */
    public static String uploadImgByByteArray(byte[] bytes, String bucket, String key) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(new ByteArrayInputStream(bytes)).scale(0.6f).toOutputStream(outputStream);
            bytes = outputStream.toByteArray();
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            try {
                Response response = uploadManager.put(bytes, key, bucket);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                log.error("七牛上传异常：" + r.toString());
                try {
                    log.error("七牛上传异常详情：" + r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return null;
    }


    public static String uploadImgByUrl(String originalUrl, String BUCKET, String key) {
        log.info("originalUrl:" + originalUrl + ",BUCKET:" + BUCKET + ",key:" + key);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //获取空间管理器
        Configuration cfg = new Configuration(Zone.zone0());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {

            // 要求url可公网正常访问BucketManager.fetch(url, bucketName, key);
            // @param url 网络上一个资源文件的URL
            // @param bucketName 空间名称
            // @param key 空间内文件的key[唯一的]
            FetchRet putret = bucketManager.fetch(originalUrl, BUCKET, key);
            return putret.key;
        } catch (QiniuException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public static String privateDownloadUrl(String url, long expires) {
        try {
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            return auth.privateDownloadUrl(url, expires);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(privateDownloadUrl("https://cainiao.cainiaodk.com/idcardFrontPhoto8676869fcf9045f58c5c5c9110bbd8c6", 10000000));
        //uploadImgByUrl("https://img-blog.csdn.net/20160119111734404","hsuseridcard","test");
    }

}
