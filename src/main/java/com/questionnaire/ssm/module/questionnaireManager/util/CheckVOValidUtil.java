package com.questionnaire.ssm.module.questionnaireManager.util;

import com.questionnaire.ssm.module.questionnaireManager.pojo.CreateQuestionnaireVO;

/**
 * Created by 郑晓辉 on 2017/3/24.
 * Description:校验前台传输数据有效的工具类
 */
public class CheckVOValidUtil {

    public static boolean createQuestionnaireVOValid(CreateQuestionnaireVO questionnaireVO) throws Exception {
        if (null == questionnaireVO.getQuestionnaireTitle()
                || "".equals(questionnaireVO.getQuestionnaireTitle().trim())) {
            return false;
        }
        return true;
    }
}