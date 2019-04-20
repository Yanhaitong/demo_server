package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.service.INavigationTabService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.yht.demo.common.BaseController;

/**
 * <p>
 * app首页导航选项卡 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/navigationTab")
public class NavigationTabController extends BaseController {

    @Autowired
    private INavigationTabService navigationTabService;

}

