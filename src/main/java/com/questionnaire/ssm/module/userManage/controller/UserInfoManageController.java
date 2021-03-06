package com.questionnaire.ssm.module.userManage.controller;

import com.questionnaire.ssm.module.generated.pojo.User;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.userManage.pojo.MyInfoVO;
import com.questionnaire.ssm.module.userManage.pojo.NewUserInfo;
import com.questionnaire.ssm.module.userManage.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 郑晓辉 on 2017/5/1.
 * Description: 用户信息管理
 */
@Controller
@RequestMapping(value = "/userInfoManage")
public class UserInfoManageController {

    /**
     * 获取修改个人信息的视图
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getChangeInfoView")
    public String getChangeInfoView() throws Exception {
        return "userManage/changeMyInfo";
    }

    @PostMapping(value = "/getMyInfo")
    @ResponseBody
    public MyInfoVO getMyInfo() throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        return userInfoService.getMyInfo(userTel);
    }

    @PostMapping(value = "/changeMyInfo")
    public String changeMyInfo(NewUserInfo newUserInfo) throws Exception {
        String userTel = UserValidationUtil.getUserTel(logger);
        userInfoService.changeMyInfo(userTel,newUserInfo);
        return "userManage/changeMyInfoOK";
    }

    private UserInfoService userInfoService;
    private final static Logger logger = LoggerFactory.getLogger(UserInfoManageController.class);

    @Autowired
    public UserInfoManageController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
