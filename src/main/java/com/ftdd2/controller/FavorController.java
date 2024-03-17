package com.ftdd2.controller;


import com.ftdd2.common.vo.Result;
import com.ftdd2.service.IFavorService;
import com.ftdd2.service.IUsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author moying
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/favor")
public class FavorController {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private IFavorService favorService;

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("收藏列表查询")
    @GetMapping("/list")
    public Result<?> getFavorPage(@RequestParam int pageNo,
                                  @RequestParam int pageSize) {
        Map<String,Object> data=usersService.getFavorList(pageNo,pageSize);
        return Result.success(data);
    }

    /**
     *
     * @param jdNo
     * @return
     */
    @ApiOperation("用户操作（收藏）")
    @PutMapping("/{jdNo}")
    public Result<?>setFavor(@PathVariable String jdNo){
        int choice= favorService.setFavor(jdNo);
        if(choice==0)
        {
            return Result.success("已删除该收藏");
        }
        return Result.success("收藏成功!");
    }
}
