package com.example.demo.model;

import java.io.Serializable;

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
@JsonPropertyOrder({ "failed", "skipped", "successful", "total" })
public class Shards implements Serializable {

	private static final long serialVersionUID = -476204706028688611L;

	@JsonProperty("failed")
	private Integer failed;
	@JsonProperty("skipped")
	private Integer skipped;
	@JsonProperty("successful")
	private Integer successful;
	@JsonProperty("total")
	private Integer total;

	@JsonProperty("failed")
	public Integer getFailed() {
		return failed;
	}

	@JsonProperty("failed")
	public void setFailed(Integer failed) {
		this.failed = failed;
	}

	@JsonProperty("skipped")
	public Integer getSkipped() {
		return skipped;
	}

	@JsonProperty("skipped")
	public void setSkipped(Integer skipped) {
		this.skipped = skipped;
	}

	@JsonProperty("successful")
	public Integer getSuccessful() {
		return successful;
	}

	@JsonProperty("successful")
	public void setSuccessful(Integer successful) {
		this.successful = successful;
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
		return new ToStringBuilder(this).append("failed", failed).append("skipped", skipped)
				.append("successful", successful).append("total", total).toString();
	}

}
