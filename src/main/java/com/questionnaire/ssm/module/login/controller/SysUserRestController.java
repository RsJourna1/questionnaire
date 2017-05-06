package com.questionnaire.ssm.module.login.controller;

import com.questionnaire.ssm.module.generated.mapper.UserMapper;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.pojo.ResponsePkt;
import com.questionnaire.ssm.module.global.util.ResultUtil;
import com.questionnaire.ssm.module.login.pojo.LoginVO;
import com.questionnaire.ssm.module.login.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 郑晓辉 on 2017/4/24.
 * Description: 支持android登录
 */
@RestController
@RequestMapping(value = "/userLoginRestful")
public class SysUserRestController {
    /**
     * restful风格的登录
     *
     * @param loginVO 用户信息
     * @param request http请求
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponsePkt login(LoginVO loginVO, HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String errorMessage = UserUtil.subjectLogin(subject, loginVO, request);

        if (null != errorMessage) {
            return ResultUtil.error(CodeForVOEnum.VALIDA_FAIL.getCode(), CodeForVOEnum.VALIDA_FAIL.getMessage());
        }
        subject.getSession().setAttribute("userTel", loginVO.getUserTel());
        request.getSession().setAttribute("userTel", loginVO.getUserTel());
        return ResultUtil.success();
    }


    private final static Logger logger = LoggerFactory.getLogger(SysUserRestController.class);
}
