package com.yht.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "返回搜索参数")
@Data
public class SearchConditionsReturnDTO {

    @ApiModelProperty(name = "searchType", value = "搜索模块", required = true)
    private String searchType;

    @ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;
}
