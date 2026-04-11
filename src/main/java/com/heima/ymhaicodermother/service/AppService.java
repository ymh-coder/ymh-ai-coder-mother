package com.heima.ymhaicodermother.service;

import com.heima.ymhaicodermother.model.dto.app.AppQueryRequest;
import com.heima.ymhaicodermother.model.entity.User;
import com.heima.ymhaicodermother.model.vo.AppVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.heima.ymhaicodermother.model.entity.App;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层。
 *
 * @author <a href="https://github.com/ymh-coder">程序员ymh-coder</a>
 */
public interface AppService extends IService<App> {

    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 应用部署
     *
     * @param appId     应用 ID
     * @param loginUser 登录用户
     * @return 可访问的部署地址
     */
    String deployApp(Long appId, User loginUser);

    void generateAppScreenshotAsync(Long appId, String appUrl);

    /**
     * 获取应封装类
     *
     * @param app
     * @return
     */
    AppVO getAppVO(App app);


    /**
     * 获取应用列表封装类
     *
     * @param appList
     * @return
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 构造应用查询条件
     *
     * @param appQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);
}
