package com.erp.controller.employee;


import com.erp.ai.AiRouterService;
import com.erp.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@Api(tags = "AI related controller")
@Slf4j
public class AiController {

    private final AiRouterService router;


    @GetMapping("/ask")
    public Result<Map<String, Object>> ask(@RequestParam String q) {
        return Result.success(router.ask(q));
    }
}
