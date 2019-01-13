package com.exam.vo;

import java.util.List;

import com.exam.vo.base.BaseConditionVo;

public class ArticleConditionVo extends BaseConditionVo {
	private Integer categoryId;
	private Integer tagId;
	private Integer status;
	private Boolean top;
	private Boolean recommended;
	private Boolean slider;
	private Boolean original;
	private Boolean random;
	private Boolean recentFlag;
	private Boolean sliderFlag;
	private List<Long> tagIds;
	private String keywords;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
	}

	public Boolean getSlider() {
		return slider;
	}

	public void setSlider(Boolean slider) {
		this.slider = slider;
	}

	public Boolean getOriginal() {
		return original;
	}

	public void setOriginal(Boolean original) {
		this.original = original;
	}

	public Boolean getRandom() {
		return random;
	}

	public void setRandom(Boolean random) {
		this.random = random;
	}

	public Boolean getRecentFlag() {
		return recentFlag;
	}

	public void setRecentFlag(Boolean recentFlag) {
		this.recentFlag = recentFlag;
	}

	public Boolean getSliderFlag() {
		return sliderFlag;
	}

	public void setSliderFlag(Boolean sliderFlag) {
		this.sliderFlag = sliderFlag;
	}

	public List<Long> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Long> tagIds) {
		this.tagIds = tagIds;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}

