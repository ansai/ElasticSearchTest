package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Elastic Search Response Json Object
 * @author anagasai
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "hits", "max_score", "total" })
public class Hits implements Serializable {

	private static final long serialVersionUID = -8351391070473978695L;

	@JsonProperty("hits")
	private List<Hit> hits = null;
	@JsonProperty("max_score")
	private Double maxScore;
	@JsonProperty("total")
	private Integer total;

	@JsonProperty("hits")
	public List<Hit> getHits() {
		return hits;
	}

	@JsonProperty("hits")
	public void setHits(List<Hit> hits) {
		this.hits = hits;
	}

	@JsonProperty("max_score")
	public Double getMaxScore() {
		return maxScore;
	}

	@JsonProperty("max_score")
	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("hits", hits).append("maxScore", maxScore).append("total", total)
				.toString();
	}

}
