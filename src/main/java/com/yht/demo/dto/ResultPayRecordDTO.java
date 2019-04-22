package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "返回支付记录详细参数")
@Data
public class ResultPayRecordDTO {

    @ApiModelProperty(name = "type", value = "经理流水（0:充值，1:退单，2:抢单消费，3:手动修改）", required = true)
    private String type;

    @ApiModelProperty(name = "money", value = "流水金额", required = true)
    private String money;

    @ApiModelProperty(name = "createTime", value = "流水记录时间", required = true)
    private String createTime;

}
