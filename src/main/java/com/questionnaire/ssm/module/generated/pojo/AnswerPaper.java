package com.questionnaire.ssm.module.generated.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class AnswerPaper {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.answer_paper_id
     *
     * @mbggenerated
     */
    private Long answerPaperId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.questionnaire_id
     *
     * @mbggenerated
     */
    private Long questionnaireId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.submit_user_tel
     *
     * @mbggenerated
     */
    private String submitUserTel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.mission_id
     *
     * @mbggenerated
     */
    private Long missionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.longitude
     *
     * @mbggenerated
     */
    private BigDecimal longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.latitude
     *
     * @mbggenerated
     */
    private BigDecimal latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.fill_answer_time
     *
     * @mbggenerated
     */
    private Date fillAnswerTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.submit_time
     *
     * @mbggenerated
     */
    private Date submitTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column answer_paper.is_submit
     *
     * @mbggenerated
     */
    private Byte isSubmit;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.answer_paper_id
     *
     * @return the value of answer_paper.answer_paper_id
     *
     * @mbggenerated
     */
    public Long getAnswerPaperId() {
        return answerPaperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.answer_paper_id
     *
     * @param answerPaperId the value for answer_paper.answer_paper_id
     *
     * @mbggenerated
     */
    public void setAnswerPaperId(Long answerPaperId) {
        this.answerPaperId = answerPaperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.questionnaire_id
     *
     * @return the value of answer_paper.questionnaire_id
     *
     * @mbggenerated
     */
    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.questionnaire_id
     *
     * @param questionnaireId the value for answer_paper.questionnaire_id
     *
     * @mbggenerated
     */
    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.submit_user_tel
     *
     * @return the value of answer_paper.submit_user_tel
     *
     * @mbggenerated
     */
    public String getSubmitUserTel() {
        return submitUserTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.submit_user_tel
     *
     * @param submitUserTel the value for answer_paper.submit_user_tel
     *
     * @mbggenerated
     */
    public void setSubmitUserTel(String submitUserTel) {
        this.submitUserTel = submitUserTel == null ? null : submitUserTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.mission_id
     *
     * @return the value of answer_paper.mission_id
     *
     * @mbggenerated
     */
    public Long getMissionId() {
        return missionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.mission_id
     *
     * @param missionId the value for answer_paper.mission_id
     *
     * @mbggenerated
     */
    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.longitude
     *
     * @return the value of answer_paper.longitude
     *
     * @mbggenerated
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.longitude
     *
     * @param longitude the value for answer_paper.longitude
     *
     * @mbggenerated
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.latitude
     *
     * @return the value of answer_paper.latitude
     *
     * @mbggenerated
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.latitude
     *
     * @param latitude the value for answer_paper.latitude
     *
     * @mbggenerated
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.fill_answer_time
     *
     * @return the value of answer_paper.fill_answer_time
     *
     * @mbggenerated
     */
    public Date getFillAnswerTime() {
        return fillAnswerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.fill_answer_time
     *
     * @param fillAnswerTime the value for answer_paper.fill_answer_time
     *
     * @mbggenerated
     */
    public void setFillAnswerTime(Date fillAnswerTime) {
        this.fillAnswerTime = fillAnswerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.submit_time
     *
     * @return the value of answer_paper.submit_time
     *
     * @mbggenerated
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.submit_time
     *
     * @param submitTime the value for answer_paper.submit_time
     *
     * @mbggenerated
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column answer_paper.is_submit
     *
     * @return the value of answer_paper.is_submit
     *
     * @mbggenerated
     */
    public Byte getIsSubmit() {
        return isSubmit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column answer_paper.is_submit
     *
     * @param isSubmit the value for answer_paper.is_submit
     *
     * @mbggenerated
     */
    public void setIsSubmit(Byte isSubmit) {
        this.isSubmit = isSubmit;
    }
}