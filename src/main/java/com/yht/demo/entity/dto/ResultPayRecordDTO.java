package com.yht.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "返回支付记录详细参数")
@Data
public class ResultPayRecordDTO {

    @ApiModelProperty(name = "type", value = "经理流水", required = true)
    private String type;

    @ApiModelProperty(name = "money", value = "流水金额", required = true)
    private String money;

    @ApiModelProperty(name = "createTime", value = "流水记录时间", required = true)
    private String createTime;

}
