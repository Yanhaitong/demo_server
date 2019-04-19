package com.yht.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * app首页导航选项卡
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("navigation_tab")
public class NavigationTab extends Model<NavigationTab> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 是否有效（0:有效，1:无效）
     */
    private Integer isValid;

    /**
     * 导航栏索引
     */
    private Integer navigatorIndex;

    /**
     * 导航栏名称
     */
    private String navigatorName;

    /**
     * 导航栏标签名
     */
    private String navigatorTagName;

    /**
     * 导航栏标签颜色
     */
    private String navigatorTagColor;

    /**
     * 导航栏标签
     */
    private String navigatorIcon;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除（0:未删除  1:已删除）
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
