package com.yht.demo.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 经理face++认证表
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("amaldar_certification")
public class AmaldarCertification extends Model<AmaldarCertification> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;

    /**
     * 当前登录用户ID
     */
    private Integer userId;

    /**
     * 经理认证状态（0:未开始， 1:身份OCR已认证，2:人脸识别已认证，3:工作信息已认证）
     */
    private Integer status;

    /**
     * 身份证姓名
     */
    private String name;

    /**
     * 身份证人像面扫描次数
     */
    private Integer scanCountPortrait;

    /**
     * 身份证国徽面扫描次数
     */
    private Integer scanCountEmblem;

    /**
     * 身份证地址
     */
    private String idCardAddress;

    /**
     * 身份证有效期
     */
    private String idCardValidity;

    /**
     * 身份证出生日期
     */
    private String idCardBirth;

    /**
     * 民族
     */
    private String idCardNationality;

    /**
     * 身份证显示性别
     */
    private String idCardGender;

    /**
     * 发证机关
     */
    private String issuingAgencies;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     * 身份证人像面地址url
     */
    private String idCardPortrait;

    /**
     * 身份证国徽面地址url
     */
    private String idCardEmblem;

    /**
     * 活体识别图片地址url
     */
    private String livingIdentifyImage;

    /**
     * idCard的人脸置信度
     */
    @TableField("confidence_idCard")
    private String confidenceIdcard;

    /**
     * idCard的置信度阈值
     */
    @TableField("thresholds_idCard")
    private String thresholdsIdcard;

    /**
     * ref1的人脸置信度
     */
    private String confidenceRef1;

    /**
     * ref1的置信度阈值
     */
    private String thresholdsRef1;

    /**
     * 身份认证异常备注
     */
    private String identityAuthError;

    /**
     * 与公司LOGO合影URL
     */
    private String companyLogo;

    /**
     * 公司工牌或名片URL
     */
    private String companyWorkCard;

    /**
     * 公司营业执照URL
     */
    private String companyLicense;

    /**
     * 公司劳动合同URL
     */
    private String laborContract;

    /**
     * 认证时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否已删除（0:否，1:是）
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
