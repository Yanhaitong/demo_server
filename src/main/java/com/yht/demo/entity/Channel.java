package com.yht.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 渠道表
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_channel")
public class Channel extends Model<Channel> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键（子渠道号）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主渠道号
     */
    private Integer shareId;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 是否有效（0:无效，1:有效）
     */
    private Integer isValid;

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
