package com.example.demo.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Elastic Search Response Class
 * 
 * @author anagasai
 *
 */
public class ESResponse implements Serializable {

	@JsonProperty("_shards")
	private Shards shards;
	@JsonProperty("hits")
	private Hits hits;
	@JsonProperty("timed_out")
	private Boolean timedOut;
	@JsonProperty("took")
	private Integer took;
	private static final long serialVersionUID = -3364992917982132553L;

	@JsonProperty("_shards")
	public Shards getShards() {
		return shards;
	}

	@JsonProperty("_shards")
	public void setShards(Shards shards) {
		this.shards = shards;
	}

	@JsonProperty("hits")
	public Hits getHits() {
		return hits;
	}

	@JsonProperty("hits")
	public void setHits(Hits hits) {
		this.hits = hits;
	}

	@JsonProperty("timed_out")
	public Boolean getTimedOut() {
		return timedOut;
	}

	@JsonProperty("timed_out")
	public void setTimedOut(Boolean timedOut) {
		this.timedOut = timedOut;
	}

	@JsonProperty("took")
	public Integer getTook() {
		return took;
	}

	@JsonProperty("took")
	public void setTook(Integer took) {
		this.took = took;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("shards", shards).append("hits", hits).append("timedOut", timedOut)
				.append("took", took).toString();
	}

}
