package com.questionnaire.ssm.module.generated.pojo;

public class Question {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_id
     *
     * @mbggenerated
     */
    private Long questionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_type
     *
     * @mbggenerated
     */
    private String questionType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_description
     *
     * @mbggenerated
     */
    private String questionDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.is_must
     *
     * @mbggenerated
     */
    private Boolean isMust;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_id
     *
     * @return the value of question.question_id
     *
     * @mbggenerated
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_id
     *
     * @param questionId the value for question.question_id
     *
     * @mbggenerated
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_type
     *
     * @return the value of question.question_type
     *
     * @mbggenerated
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_type
     *
     * @param questionType the value for question.question_type
     *
     * @mbggenerated
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_description
     *
     * @return the value of question.question_description
     *
     * @mbggenerated
     */
    public String getQuestionDescription() {
        return questionDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_description
     *
     * @param questionDescription the value for question.question_description
     *
     * @mbggenerated
     */
    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription == null ? null : questionDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.is_must
     *
     * @return the value of question.is_must
     *
     * @mbggenerated
     */
    public Boolean getIsMust() {
        return isMust;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.is_must
     *
     * @param isMust the value for question.is_must
     *
     * @mbggenerated
     */
    public void setIsMust(Boolean isMust) {
        this.isMust = isMust;
    }
}