package com.example.demo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Elastic Search Response Json Object
 * @author anagasai
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "answer", "category", "faq_id", "question" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source implements Serializable {

	private static final long serialVersionUID = 4847553443938184272L;

	@JsonProperty("answer")
	private String answer;
	@JsonProperty("category")
	private String category;
	@JsonProperty("faqId")
	private String faqId;
	@JsonProperty("question")
	private String question;

	@JsonProperty("answer")
	public String getAnswer() {
		return answer;
	}

	@JsonProperty("answer")
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
	}

	@JsonProperty("faqId")
	public String getFaqId() {
		return faqId;
	}

	@JsonProperty("faqId")
	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	@JsonProperty("question")
	public String getQuestion() {
		return question;
	}

	@JsonProperty("question")
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("answer", answer).append("category", category).append("faqId", faqId)
				.append("question", question).toString();
	}

}
