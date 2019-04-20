package com.yht.demo.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("city_")
public class City extends Model<City> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;

    /**
     * 城市名称
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
