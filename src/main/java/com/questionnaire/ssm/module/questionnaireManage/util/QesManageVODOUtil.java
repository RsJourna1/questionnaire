package com.questionnaire.ssm.module.questionnaireManage.util;

import com.questionnaire.ssm.module.generated.pojo.AnswerDetail;
import com.questionnaire.ssm.module.generated.pojo.QuestionWithBLOBs;
import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.exception.UserValidaException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.questionnaireManage.enums.QuestionTypeEnum;
import com.questionnaire.ssm.module.questionnaireManage.pojo.*;
import com.questionnaire.ssm.module.researchManage.pojo.AnswerDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:用于前台VO实体与后台数据库实体类相互转换的工具类
 */
public class QesManageVODOUtil {

    /**
     * 提取创建问卷视图中实体数据到数据库问卷实体
     *
     * @param questionnaireVO 问卷信息
     * @return
     * @throws Exception
     */
    public static Questionnaire toQuestionnaireDO(CreateQesVO questionnaireVO) throws Exception {
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setQuestionnaireTitle(questionnaireVO.getQuestionnaireTitle());
        questionnaire.setQuestionnaireSubtitle(questionnaireVO.getQuestionnaireSubtitle());
        questionnaire.setQuestionnaireDescription(questionnaireVO.getQuestionnaireDescription());
        questionnaire.setIsDone(questionnaireVO.getDone());
        questionnaire.setIsTemplate(questionnaireVO.getTemplate());

        questionnaire.setIsShare(false);
        questionnaire.setIsVisible(true);
        questionnaire.setIsRelease(false);

        //创建问卷时间
        questionnaire.setCreateTime(new Date());
        try {
            questionnaire.setCreateUser(UserValidationUtil.getUserTel(logger));
        } catch (NullPointerException e) {
            throw new UserValidaException(CodeForVOEnum.NOT_LOGIN);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UserValidaException(CodeForVOEnum.UNKNOWN_ERROR);
        }

        return questionnaire;
    }

    /**
     * 将数据库中问卷实体的数据包装到 展示问卷视图的实体中去
     *
     * @param questionnaireDO
     * @return
     * @throws Exception
     */
    public static DisplayQesVO toDisplayQuestionnaireVO(Questionnaire questionnaireDO)
            throws Exception {
        DisplayQesVO displayQesVO = new DisplayQesVO();

        displayQesVO.setQuestionnaireId(questionnaireDO.getQuestionnaireId());

        if (questionnaireDO.getQuestionnaireTitle() != null) {
            displayQesVO.setQuestionnaireTitle(questionnaireDO.getQuestionnaireTitle());
        }
        if (questionnaireDO.getQuestionnaireSubtitle() != null) {
            displayQesVO.setQuestionnaireSubtitle(questionnaireDO.getQuestionnaireSubtitle());
        }
        if (questionnaireDO.getQuestionnaireDescription() != null) {
            displayQesVO.setQuestionnaireDescription(questionnaireDO.getQuestionnaireDescription());
        }
        return displayQesVO;
    }

    /**
     * 将数据库中问题数据包装到 展示问卷视图的实体中
     *
     * @param question
     * @return
     * @throws Exception
     */
    public static QuestionVO toQuestionVO(QuestionWithBLOBs question, List<QuestionOptionVO> questionOptionVOList) throws Exception {
        QuestionVO questionVO = new QuestionVO();

        //用于上传答卷信息时保存数据使用
        questionVO.setQuestionId(question.getQuestionId());

        if (question.getQuestionContext() != null) {
            questionVO.setQuestionContext(question.getQuestionContext());
        }
        if (question.getQuestionDescription() != null) {
            questionVO.setQuestionDescription(question.getQuestionDescription());
        }
        if (questionOptionVOList.size() > 0) {
            questionVO.setOptions(questionOptionVOList);
        }
        /*数据库中不为空字段*/
        questionVO.setQuestionType(parse2VOQuestionType(question.getQuestionType()));
        questionVO.setMust(question.getIsMust());
        return questionVO;
    }

    /**
     * 提取问卷中题目的题目信息以及题目的选项数据信息
     *
     * @param voQuestions 问卷题目信息
     * @return
     * @throws Exception
     */
    public static List<QuestionWithBLOBs> toQuestionMultiDO(List<QuestionVO> voQuestions) throws Exception {
        List<QuestionWithBLOBs> questionWithBLOBsList = new ArrayList<>();

        QuestionWithBLOBs questionWithBLOBs = null;

        for (QuestionVO currentVOQuestion : voQuestions) {

            questionWithBLOBs = new QuestionWithBLOBs();
            questionWithBLOBs.setQuestionContext(currentVOQuestion.getQuestionContext());
            questionWithBLOBs.setQuestionDescription(currentVOQuestion.getQuestionDescription());
            questionWithBLOBs.setIsMust(currentVOQuestion.getMust());
            /*需要将汉字问题类型装换为数据库中的类型编码*/
            questionWithBLOBs.setQuestionType(parse2DOQuestionType(currentVOQuestion.getQuestionType()));

            /*需要进行数据库操作，有数据库返回的自增值或者已有值来完成赋值*/
            //question.setQuestionId();

            List<QuestionOptionVO> options = currentVOQuestion.getOptions();

            //questionType已经转化为数据库中问题类型编码
            questionWithBLOBs.setOptionString(toOptionsString(options, questionWithBLOBs.getQuestionType()));

            questionWithBLOBsList.add(questionWithBLOBs);
        }

        return questionWithBLOBsList;
    }

    /**
     * 获取答案详细数据库实体类
     *
     * @param answerDetailVO 视图中答案详细实体
     * @return
     * @throws Exception
     */
    public static AnswerDetail toAnswerDetailDO(AnswerDetailVO answerDetailVO) throws Exception {
        AnswerDetail answerDetail = new AnswerDetail();
        answerDetail.setQuestionId(answerDetailVO.getQuestionId());
        //获取汉字形式文本的数据库表示值
        String questionCode = parse2DOQuestionType(answerDetailVO.getQuestionType());
        if (answerDetailVO.getAnswer().size() > 0) {
            answerDetail.setAnswerString(toAnswerString(answerDetailVO.getAnswer(), questionCode));
        } else {
            //该题用户未作答，默认设置为‘未回答’
            answerDetail.setAnswerString(CONSTANT.getNullAnswerString());
        }
        return answerDetail;
    }

    /**
     * 将数据库中的问题类型代码转化为 汉字表达形式
     *
     * @param typeCode
     * @return
     * @throws Exception
     */
    public static String parse2VOQuestionType(String typeCode) throws Exception {
        typeCode = typeCode.trim();
        if (QuestionTypeEnum.SINGLE_LINE_BLANK.getCode().equals(typeCode)) {
            return QuestionTypeEnum.SINGLE_LINE_BLANK.getQuestionType();
        }
        if (QuestionTypeEnum.MULTI_LINE_BLANK.getCode().equals(typeCode)) {
            return QuestionTypeEnum.MULTI_LINE_BLANK.getQuestionType();
        }
        if (QuestionTypeEnum.SINGLE_CHOICE.getCode().equals(typeCode)) {
            return QuestionTypeEnum.SINGLE_CHOICE.getQuestionType();
        }
        if (QuestionTypeEnum.MULTIPLE_CHOICE.getCode().equals(typeCode)) {
            return QuestionTypeEnum.MULTIPLE_CHOICE.getQuestionType();
        }
        if (QuestionTypeEnum.DROP_SELECTION.getCode().equals(typeCode)) {
            return QuestionTypeEnum.DROP_SELECTION.getQuestionType();
        }
        if (QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode().equals(typeCode)) {
            return QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getQuestionType();
        }
        if (QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode().equals(typeCode)) {
            return QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getQuestionType();
        }
        if (QuestionTypeEnum.SHORT_ANSWER.getCode().equals(typeCode)) {
            return QuestionTypeEnum.SHORT_ANSWER.getQuestionType();
        }
        return QuestionTypeEnum.UNKNOWN_TYPE.getQuestionType();
    }

    /**
     * 转换汉字问题类型文本到数据库问题类型代码
     *
     * @param typeString
     * @return
     * @throws Exception
     */
    private static String parse2DOQuestionType(String typeString) throws Exception {
        typeString = typeString.trim();
        if (QuestionTypeEnum.SINGLE_LINE_BLANK.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.SINGLE_LINE_BLANK.getCode();
        }
        if (QuestionTypeEnum.MULTI_LINE_BLANK.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.MULTI_LINE_BLANK.getCode();
        }
        if (QuestionTypeEnum.SINGLE_CHOICE.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.SINGLE_CHOICE.getCode();
        }
        if (QuestionTypeEnum.MULTIPLE_CHOICE.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.MULTIPLE_CHOICE.getCode();
        }
        if (QuestionTypeEnum.DROP_SELECTION.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.DROP_SELECTION.getCode();
        }
        if (QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode();
        }
        if (QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode();
        }
        if (QuestionTypeEnum.SHORT_ANSWER.getQuestionType().equals(typeString)) {
            return QuestionTypeEnum.SHORT_ANSWER.getCode();
        }
        return QuestionTypeEnum.UNKNOWN_TYPE.getCode();
    }

    /**
     * 将选项条目转换为选项条目字符串
     *
     * @param optionVOList     VO视图的问题选项
     * @param questionTypeCode 数据库中问题类型编码
     * @return
     * @throws Exception
     */
    private static String toOptionsString(List<QuestionOptionVO> optionVOList, String questionTypeCode) throws Exception {
        int optionSize = optionVOList.size();
        StringBuilder optionStrBuilder = new StringBuilder();
        for (int optionOrder = 0; optionOrder < optionSize; optionOrder++) {
            optionStrBuilder.append(optionVOList.get(optionOrder).getOption());
            if (optionSize != optionOrder + 1) {
                //需要根据题目类型采用不同的切割符号
                if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())) {
                    optionStrBuilder.append(QuestionTypeEnum.SINGLE_CHOICE.getDivideStr());
                }
            }
        }
        return optionStrBuilder.toString();
    }

    /**
     * 将答卷中的答案装为 答案String类型
     *
     * @param answerVOStringList "视图"中答卷答案文本
     * @param questionTypeCode   数据库中问题类型编码
     * @return
     * @throws Exception
     */
    public static String toAnswerString(List<String> answerVOStringList, String questionTypeCode) throws Exception {
        int optionSize = answerVOStringList.size();
        StringBuilder optionStrBuilder = new StringBuilder();
        for (int optionOrder = 0; optionOrder < optionSize; optionOrder++) {
            optionStrBuilder.append(answerVOStringList.get(optionOrder));
            if (optionSize != optionOrder + 1) {
                //需要根据题目类型采用不同的切割符号
                if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                        || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())) {
                    optionStrBuilder.append(QuestionTypeEnum.SINGLE_CHOICE.getDivideStr());
                }
            }
        }
        return optionStrBuilder.toString();
    }

    /**
     * 将数据库选项字符串切割成正常的选项信息
     *
     * @param optionString
     * @return
     * @throws Exception
     */
    public static List<QuestionOptionVO> toOptionsItem(String optionString, String questionTypeCode) throws Exception {
        List<QuestionOptionVO> questionOptionVOList = new ArrayList<>();
        QuestionOptionVO questionOptionVO = null;

        String[] options = null;
        if (questionTypeCode.equals(QuestionTypeEnum.SINGLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTIPLE_CHOICE.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SINGLE_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.MULTI_LINE_BLANK.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.DROP_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_SINGLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.PICTURE_MULTIPLE_SELECTION.getCode())
                || questionTypeCode.equals(QuestionTypeEnum.SHORT_ANSWER.getCode())) {
            options = optionString.split("\\|\\|");
        }
        if (options == null) {
            return null;
        }

        for (int order = 0; order < options.length; order++) {
            questionOptionVO = new QuestionOptionVO();
            questionOptionVO.setOptionOrder(order);
            questionOptionVO.setOption(options[order]);
            questionOptionVOList.add(order, questionOptionVO);
        }
        return questionOptionVOList;
    }


    private final static Logger logger = LoggerFactory.getLogger(QesManageVODOUtil.class);
}
