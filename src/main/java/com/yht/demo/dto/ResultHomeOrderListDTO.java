package com.yht.demo.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
@ApiModel(value = "返回首页订单列表数据")
@Data
public class ResultHomeOrderListDTO {

    @ApiModelProperty(name = "orderList", value = "首页订单详情数据列表", required = true)
    private IPage<ResultOrderListDTO> orderList;

    @ApiModelProperty(name = "bannerList", value = "轮播图数据列表", required = true)
    private List<ResultBannerDTO> bannerList;

}