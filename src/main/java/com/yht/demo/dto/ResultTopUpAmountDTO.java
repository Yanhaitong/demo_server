package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "获取充值金额信息")
@Data
public class ResultTopUpAmountDTO {

    @ApiModelProperty(name = "price", value = "充值金额", required = true)
    private String price;

    @ApiModelProperty(name = "mili", value = "获得的米粒", required = true)
    private String mili;

    @ApiModelProperty(name = "sort", value = "排序", required = true)
    private Integer sort;

}