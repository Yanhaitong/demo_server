package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "返回用户详细参数")
@Data
public class ResultAPPInfoDTO {

    @ApiModelProperty(name = "navigationTabList", value = "首页导航列表", required = true)
    private List<ResultNavigationTabDTO> navigationTabList;

}