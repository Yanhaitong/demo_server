package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "返回搜索参数")
@Data
public class ResultSearchConditionsDTO {

    @ApiModelProperty(name = "selectType", value = "选择方式（0:单选，1:多选）", required = true)
    private String selectType;

    @ApiModelProperty(name = "searchId", value = "搜索Id", required = true)
    private String searchId;

    @ApiModelProperty(name = "searchTitle", value = "搜索标题", required = true)
    private String searchTitle;

    @ApiModelProperty(name = "searchName", value = "搜索名称", required = true)
    private String searchName;

}
