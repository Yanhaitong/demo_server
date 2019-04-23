package com.yht.demo.service.impl;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yht.demo.common.*;
import com.yht.demo.common.face.constant.FacePlusContst;
import com.yht.demo.common.face.util.FacePlusUtil;
import com.yht.demo.common.qiniu.QiniuBussiness;
import com.yht.demo.dto.*;
import com.yht.demo.entity.AmaldarAuth;
import com.yht.demo.entity.User;
import com.yht.demo.mapper.AmaldarAuthMapper;
import com.yht.demo.mapper.UserMapper;
import com.yht.demo.service.IAmaldarAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 经理face++认证表 服务实现类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Service
public class AmaldarAuthServiceImpl extends BaseServiceImpl implements IAmaldarAuthService {

    @Autowired
    private AmaldarAuthMapper amaldarAuthMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getAmaldarAuthInfo(ParameterUserInfoDTO parameterUserInfoDTO) {
        //判断当前用户是否已认证
        String userId = RedisUtils.getUserIdByToken(parameterUserInfoDTO.getToken());
        if (StringUtils.isEmpty(userId)) {
            return Result.error(500, MsgConstant.USER_ID_IS_NULL);
        }
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.error(500, MsgConstant.USER_INFO_IS_NULL);
        }

        AmaldarAuth amaldarAuth = amaldarAuthMapper.getAmaldarAuthInfoByUserId(user.getId());
        ResultAmaldarAuthInfoDTO resultAmaldarAuthInfoDTO = new ResultAmaldarAuthInfoDTO();
        if (amaldarAuth == null) {//经理未认证
            return Result.success(resultAmaldarAuthInfoDTO);
        } else {//经理认证中，经理审核中，经理认证通过，经理认证未通过
            resultAmaldarAuthInfoDTO.setStatus(String.valueOf(amaldarAuth.getStatus()));
            resultAmaldarAuthInfoDTO.setWorkCity(amaldarAuth.getWorkCity());
            resultAmaldarAuthInfoDTO.setCompanyLicenseUrl("1");
            resultAmaldarAuthInfoDTO.setCompanyLogoUrl("1");
            resultAmaldarAuthInfoDTO.setCompanyWorkCardUrl("1");
            resultAmaldarAuthInfoDTO.setLaborContractUrl("1");
            return Result.success(resultAmaldarAuthInfoDTO);
        }
    }

    @Override
    public Result idCardValidation(ParameterIdCardDTO parameterIdCardDTO) {
        return null;
    }

    @Override
    public Result getBizToken(ParameterUserInfoDTO parameterUserInfoDTO) {
        return null;
    }

    @Override
    public Result getVerifyResult(ParameterUserInfoDTO parameterUserInfoDTO) {
        return null;
    }

    @Override
    public Result companyAuth(ParameterBaseDTO parameterBaseDTO) {
        return null;
    }

    @Override
    public Result getUploadCredentials(ParameterBaseDTO parameterBaseDTO) {

        String bucket = parameterBaseDTO.getClientName();
        // 密钥配置
        Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY, Constant.QINIU_SECRET_KEY);
        // 要上传的空间
        if (bucket == null || "".equals(bucket.trim())) {
            bucket = Constant.QINIU_ICON_BUCKET;
        }
        //上传到七牛后保存的文件名
        String companyLogoKey = bucket + "companyLogo" + "_" + System.currentTimeMillis() + ".jpg";
        String companyWorkCardKey = bucket + "companyWorkCard" + "_" + System.currentTimeMillis() + ".jpg";
        String companyLicenseKey = bucket + "companyLicense" + "_" + System.currentTimeMillis() + ".jpg";
        String laborContractKey = bucket + "laborContract" + "_" + System.currentTimeMillis() + ".jpg";
        //上传到七牛云的token
        String companyLogoToken = auth.uploadToken(bucket, companyLogoKey, 300, new StringMap());
        String companyWorkCardToken = auth.uploadToken(bucket, companyWorkCardKey, 300, new StringMap());
        String companyLicenseToken = auth.uploadToken(bucket, companyLicenseKey, 300, new StringMap());
        String laborContractToken = auth.uploadToken(bucket, laborContractKey, 300, new StringMap());

        ResultQiNiuCredentialsDTO resultQiNiuCredentialsDTO = new ResultQiNiuCredentialsDTO();
        resultQiNiuCredentialsDTO.setCompanyLicenseKey(companyLicenseKey);
        resultQiNiuCredentialsDTO.setCompanyLicenseToken(companyLicenseToken);
        resultQiNiuCredentialsDTO.setCompanyLogoKey(companyLogoKey);
        resultQiNiuCredentialsDTO.setCompanyLogoToken(companyLogoToken);
        resultQiNiuCredentialsDTO.setCompanyWorkCardKey(companyWorkCardKey);
        resultQiNiuCredentialsDTO.setCompanyWorkCardToken(companyWorkCardToken);
        resultQiNiuCredentialsDTO.setLaborContractKey(laborContractKey);
        resultQiNiuCredentialsDTO.setLaborContractToken(laborContractToken);
        return Result.success(resultQiNiuCredentialsDTO);
    }


    /**
     * 获取getBizToken
     *
     * @param idCardName
     * @param idCardNumber
     * @param fileByteArr
     * @return
     * @throws Exception
     */
    private String getBizToken(String idCardName, String idCardNumber, byte[] fileByteArr) throws Exception {
        Map<String, String> textMap = new HashMap<String, String>();
        Map<String, byte[]> fileMap = new HashMap<String, byte[]>();
        textMap.put("sign", FacePlusUtil.genSign(FacePlusContst.API_KEY, FacePlusContst.API_SECRET, 0L));
        textMap.put("sign_version", "hmac_sha1");
        textMap.put("liveness_type", "meglive");
        textMap.put("comparison_type", "1");
        textMap.put("idcard_name", idCardName);
        textMap.put("idcard_number", idCardNumber);
        fileMap.put("image_ref1", fileByteArr);
        String contentType = "application/octet-stream";
        return FacePlusUtil.formUpload(FacePlusContst.TOKEN_URL, textMap, fileMap, contentType);
    }


    /**
     * 获取人脸识别结果
     *
     * @param bizToken
     * @param file
     * @return
     * @throws Exception
     */
    private String getVerify(String bizToken, MultipartFile file) throws Exception {
        Map<String, String> textMap = new HashMap<String, String>();
        Map<String, byte[]> fileMap = new HashMap<String, byte[]>();
        textMap.put("sign", FacePlusUtil.genSign(FacePlusContst.API_KEY, FacePlusContst.API_SECRET, 0L));
        textMap.put("sign_version", "hmac_sha1");
        textMap.put("biz_token", bizToken);
        fileMap.put("meglive_data", file.getBytes());
        String contentType = "application/octet-stream";
        return FacePlusUtil.formUpload(FacePlusContst.VERIFY_URL, textMap, fileMap, contentType);
    }

    /**
     * 上传身份证图片
     *
     * @param mobileNo
     * @param client
     * @param file
     * @param side
     * @return
     * @throws IOException
     */
    private String updateIdCardImage(String mobileNo, String client, MultipartFile file, int side) throws IOException {
        //User user = userService.getUserByMobileNo(mobileNo, 2,client);
        Integer userId = 123;
        //设置好账号的ACCESS_KEY和SECRET_KEY
        String ACCESS_KEY = Constant.QINIU_ACCESS_KEY;
        String SECRET_KEY = Constant.QINIU_SECRET_KEY;
        //要上传的空间
        String bucketname = Constant.QINIU_CAINIAO_BUCKET;

        if (0 == side) {//身份证正面
            //上传到七牛后保存的文件名
            long timestamp = System.currentTimeMillis();
            String key_idCard_positive_image = "key_idCard_positive_image" + mobileNo + timestamp + ".png";
            //密钥配置
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String idCard_positive_token = auth.uploadToken(bucketname, key_idCard_positive_image);
            //上传图片
            String portraitUrl = QiniuBussiness.uploadImgByByteArray(file.getBytes(), idCard_positive_token, key_idCard_positive_image);
            log.info(userId + "七牛上传身份证头像图片名:" + portraitUrl);

            return key_idCard_positive_image;
        } else {//身份证反面
            //上传到七牛后保存的文件名
            long timestamp = System.currentTimeMillis();
            String key_idCard_reverse_image = "key_idCard_reverse_image" + mobileNo + timestamp + ".png";
            //密钥配置
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String idCard_reverse_token = auth.uploadToken(bucketname, key_idCard_reverse_image);
            //上传图片
            String portraitUrl = QiniuBussiness.uploadImgByByteArray(file.getBytes(), idCard_reverse_token, key_idCard_reverse_image);
            log.info(userId + "七牛上传身份证头像图片名:" + portraitUrl);

            return key_idCard_reverse_image;
        }
    }

    /**
     * 上传活体识别的图片
     *
     * @param mobile_no
     * @param userId
     * @param bytes
     * @return
     * @throws IOException
     */
    private String updateLivingIdentifyImage(String mobile_no, int userId, byte[] bytes) throws IOException {
        //设置好账号的ACCESS_KEY和SECRET_KEY
        String ACCESS_KEY = Constant.QINIU_ACCESS_KEY;
        String SECRET_KEY = Constant.QINIU_SECRET_KEY;
        //要上传的空间
        String bucketname = Constant.QINIU_CAINIAO_BUCKET;

        //上传到七牛后保存的文件名
        long timestamp = System.currentTimeMillis();
        String living_identify_image = "living_identify_image" + mobile_no + timestamp + ".png";
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String living_identify_token = auth.uploadToken(bucketname, living_identify_image);
        //上传图片
        String portraitUrl = QiniuBussiness.uploadImgByByteArray(bytes, living_identify_token, living_identify_image);
        log.info(userId + "七牛上传活体识别头像图片名:" + portraitUrl);

        return living_identify_image;
    }

    /**
     * 上传身份证头像的图片
     *
     * @param mobile_no
     * @param bytes
     * @return
     * @throws IOException
     */
    private String updateIdCardPortrait(String mobile_no, byte[] bytes) throws IOException {
        //设置好账号的ACCESS_KEY和SECRET_KEY
        String ACCESS_KEY = Constant.QINIU_ACCESS_KEY;
        String SECRET_KEY = Constant.QINIU_SECRET_KEY;
        //要上传的空间
        String bucketname = Constant.QINIU_CAINIAO_BUCKET;

        //上传到七牛后保存的文件名
        long timestamp = System.currentTimeMillis();
        String id_card_portrait_image = "id_card_portrait_image" + mobile_no + timestamp + ".png";
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String id_card_portrait_token = auth.uploadToken(bucketname, id_card_portrait_image);
        //上传图片
        String portraitUrl = QiniuBussiness.uploadImgByByteArray(bytes, id_card_portrait_token, id_card_portrait_image);
        log.info("七牛上传身份证头像图片名:" + portraitUrl);

        return id_card_portrait_image;
    }
}
