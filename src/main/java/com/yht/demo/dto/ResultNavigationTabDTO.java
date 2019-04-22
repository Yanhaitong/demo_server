package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "导航返回参数")
@Data
public class ResultNavigationTabDTO {

    @ApiModelProperty(name = "navigatorId", value = "导航ID", required = true)
    private String navigatorId;

    @ApiModelProperty(name = "navigatorIndex", value = "导航序号", required = true)
    private String navigatorIndex;

    @ApiModelProperty(name = "navigatorName", value = "导航名称", required = true)
    private String navigatorName;

   /* @ApiModelProperty(name = "navigatorTagName", value = "导航标记名称", required = true)
    private String navigatorTagName;

    @ApiModelProperty(name = "navigatorTagColor", value = "导航标记颜色", required = true)
    private String navigatorTagColor;

    @ApiModelProperty(name = "navigatorIcon", value = "导航图标", required = true)
    private String navigatorIcon;*/
}
