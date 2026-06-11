package com.heima.ymhaicodermother.service.impl;

import com.heima.ymhaicodermother.service.ScreenshotService;
import com.heima.ymhaicodeuser.innerservice.InnerScreenshotService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class InnerScreenshotServiceImpl implements InnerScreenshotService {

    @Resource
    private ScreenshotService screenshotService;

    @Override
    public String generateAndUploadScreenshot(String webUrl) {
        return screenshotService.generateAndUploadScreenshot(webUrl);
    }
}
