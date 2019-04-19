package com.yht.demo.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * APP用户表
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色（0:贷款用户， 1:信贷经理）
     */
    private Integer roleId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String mobileNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 经理状态（0:未认证，1:审核中，2:认证通过，3:认证未通过，4:账户冻结）
     */
    private Integer status;

    /**
     * 性别（0:女性，1:男性）
     */
    private Integer sex;

    /**
     * 头像url
     */
    private String iconUrl;

    /**
     * 余额
     */
    private String balance;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端类型（0:ios，1:android）
     */
    private Integer clientType;

    /**
     * 客户端版本号
     */
    private String clientVersion;

    /**
     * 经理等级id
     */
    private Integer levelId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除（0:未删除  1:已删除）
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
