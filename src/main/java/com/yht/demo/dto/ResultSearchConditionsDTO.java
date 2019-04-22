package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "返回搜索参数")
@Data
public class ResultSearchConditionsDTO {

    @ApiModelProperty(name = "selectType", value = "选择方式（0:单选，1:多选）", required = true)
    private String selectType;

    @ApiModelProperty(name = "searchType", value = "搜索类型", required = true)
    private String searchType;

    @ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;
}
