package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "返回轮播图信息参数")
@Data
public class ResultBannerDTO {

    @ApiModelProperty(name = "id", value = "手机号", required = true)
    private String id;

    @ApiModelProperty(name = "title", value = "标题", required = true)
    private String title;

    @ApiModelProperty(name = "bannerDetailUrl", value = "轮播图详情URL", required = true)
    private String bannerDetailUrl;

    @ApiModelProperty(name = "coverUrl", value = "封面图URL", required = true)
    private String coverUrl;

}