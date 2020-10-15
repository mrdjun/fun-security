package com.fun.client.controller;


import com.fun.client.result.R;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * 校验时令牌放在 header 里
     */
    @Secured("ROLE_USER")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/findAll")
    public R<?> findAll() {
        return R.success("查询列表成功！");
    }
}

