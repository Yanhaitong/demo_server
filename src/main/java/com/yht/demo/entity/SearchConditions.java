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
 * app搜索条件
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_search_conditions")
public class SearchConditions extends Model<SearchConditions> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 选择方式（0:单选，1:多选）
     */
    private Integer selectType;

    /**
     * 搜索类型（0:收入形式，1:客户资质）
     */
    private Integer searchType;

    /**
     * 是否有效（0:无效，1:有效）
     */
    private Integer isValid;

    /**
     * 搜索名称
     */
    private String name;

    /**
     * 客户端Id
     */
    private String clientId;

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
