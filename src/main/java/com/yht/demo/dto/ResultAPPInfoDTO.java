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

    @ApiModelProperty(name = "cityList", value = "城市列表", required = true)
    private List<String> cityList;

    @ApiModelProperty(name = "navigationTabList", value = "首页导航列表", required = true)
    private ResultNavigationTabDTO navigationTabList;

    @ApiModelProperty(name = "searchConditionsList", value = "搜索条件", required = true)
    private ResultSearchConditionsDTO searchConditionsList;

}