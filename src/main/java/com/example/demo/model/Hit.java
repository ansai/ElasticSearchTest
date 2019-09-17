package com.example.demo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Elastic Search Response Json object
 * @author anagasai
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_id", "_index", "_score", "_source", "_type" })
public class Hit implements Serializable {

	private static final long serialVersionUID = 1531099868301635706L;

	@JsonProperty("_id")
	private String id;
	@JsonProperty("_index")
	private String index;
	@JsonProperty("_score")
	private Double score;
	@JsonProperty("_source")
	private Source source;
	@JsonProperty("_type")
	private String type;

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("_index")
	public String getIndex() {
		return index;
	}

	@JsonProperty("_index")
	public void setIndex(String index) {
		this.index = index;
	}

	@JsonProperty("_score")
	public Double getScore() {
		return score;
	}

	@JsonProperty("_score")
	public void setScore(Double score) {
		this.score = score;
	}

	@JsonProperty("_source")
	public Source getSource() {
		return source;
	}

	@JsonProperty("_source")
	public void setSource(Source source) {
		this.source = source;
	}

	@JsonProperty("_type")
	public String getType() {
		return type;
	}

	@JsonProperty("_type")
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("index", index).append("score", score)
				.append("source", source).append("type", type).toString();
	}

}
