package com.questionnaire.ssm.module.global.service.impl;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.service.Add2LibraryService;
import com.questionnaire.ssm.module.questionnaireManage.util.OperateQuestionnaireUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/4/3.
 * Description:
 */
@Service
public class Add2LibraryServiceImpl implements Add2LibraryService {

    /**
     * 从数据库获取正在分享的问卷信息
     *
     * @param questionnaireId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Questionnaire getSharingQesPaperFromDB(Long questionnaireId) throws Exception {
        Questionnaire sharingQesPaper = null;
        try {
            sharingQesPaper = questionnaireMapper.selectByPrimaryKey(questionnaireId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_SELECT_NO_MESSAGE,
                    DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        return sharingQesPaper;
    }

    /**
     * 分享问卷（复制问卷信息并且重新组织问卷-题目对应关系）
     *
     * @param questionnaireId 被操作问卷id
     * @param copiedQesPaper  复制的问卷
     * @return 新生成的问卷id
     * @throws Exception
     */
    @Override
    @Transactional
    public Long Add2PublicOrPrivateLibrary(Long questionnaireId, Questionnaire copiedQesPaper) throws Exception {

        try {
            questionnaireMapper.insertSelective(copiedQesPaper);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL,
                    DBTableEnum.QUESTIONNAIRE.getTableName());
        }
        /*新生成的问卷Id*/
        Long copiedQuestionnaireId = copiedQesPaper.getQuestionnaireId();
        /*开始组织新的问卷-问题映射关系*/
        MappingQuestionnaireQuestionExample mappingQuestionnaireQuestionExample = new MappingQuestionnaireQuestionExample();
        mappingQuestionnaireQuestionExample.createCriteria().andQuestionnaireIdEqualTo(questionnaireId);

        List<MappingQuestionnaireQuestion> mappingQuestionnaireQuestions
                = mappingQuestionnaireQuestionMapper.selectByExample(mappingQuestionnaireQuestionExample);
        MappingQuestionnaireQuestion newMap = null;
        for (MappingQuestionnaireQuestion currentMap : mappingQuestionnaireQuestions) {

            newMap = OperateQuestionnaireUtil.copyMapQesPaperQes(currentMap);
            /*设置新获取的问卷ID*/
            newMap.setQuestionnaireId(copiedQuestionnaireId);
            /*插入新的映射关系*/
            try {
                mappingQuestionnaireQuestionMapper.insertSelective(newMap);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL,
                        DBTableEnum.MAPPING_QUESTIONNAIRE_QUESTION.getTableName());
            }
        }

        return copiedQuestionnaireId;
    }

    private static final Logger logger = LoggerFactory.getLogger(Add2LibraryServiceImpl.class);
    private MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper;
    private QuestionnaireMapper questionnaireMapper;

    @Autowired
    public Add2LibraryServiceImpl(MappingQuestionnaireQuestionMapper mappingQuestionnaireQuestionMapper,
                                  QuestionnaireMapper questionnaireMapper) {
        this.mappingQuestionnaireQuestionMapper = mappingQuestionnaireQuestionMapper;
        this.questionnaireMapper = questionnaireMapper;
    }
}
