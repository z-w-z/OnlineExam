package com.exam.model;

import java.util.List;

import javax.persistence.Transient;

import com.exam.vo.base.BaseVo;

public class BizArticle extends BaseVo {
	
    private String title;
    private String userId;
    private String author;
    private String coverImage;
    private String qrcodePath;
    private Boolean isMarkdown;
    private String content;
    private String contentMd;
    private Integer top;
    private Integer categoryId;
    private Integer status;
    private Integer recommended;
    private Integer slider;
    private String sliderImg;
    private Integer original;
    private String description;
    private String keywords;
    private Integer comment;
    @Transient
    private Integer lookCount=0;
    @Transient
    private Integer commentCount=0;
    @Transient
    private Integer loveCount=0;
    @Transient
    private Integer newFlag=0;
    @Transient
    List<BizTags> tags;
    @Transient
    BizCategory bizCategory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getQrcodePath() {
        return qrcodePath;
    }

    public void setQrcodePath(String qrcodePath) {
        this.qrcodePath = qrcodePath;
    }

    public Boolean getMarkdown() {
        return isMarkdown;
    }

    public void setMarkdown(Boolean markdown) {
        isMarkdown = markdown;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentMd() {
        return contentMd;
    }

    public void setContentMd(String contentMd) {
        this.contentMd = contentMd;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }

    public Integer getSlider() {
        return slider;
    }

    public void setSlider(Integer slider) {
        this.slider = slider;
    }

    public String getSliderImg() {
        return sliderImg;
    }

    public void setSliderImg(String sliderImg) {
        this.sliderImg = sliderImg;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }

    public Integer getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(Integer newFlag) {
        this.newFlag = newFlag;
    }

    public List<BizTags> getTags() {
        return tags;
    }

    public void setTags(List<BizTags> tags) {
        this.tags = tags;
    }

    public BizCategory getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(BizCategory bizCategory) {
        this.bizCategory = bizCategory;
    }
}
