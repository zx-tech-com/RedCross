package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 捋一下关系
 * @author Daryl
 *
 */
public class TopicComent implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String comentTime;
	private String content;
	private Boolean isTopcoment;
	private Topic topic;
	private Customer customer;
	private TopicComent topicComent1;
	private List<TopicComent> topicComents1;
	private TopicComent topicComent2;
	private List<TopicComent> topicComents2;

	public TopicComent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentTime() {
		return this.comentTime;
	}

	public void setComentTime(String comentTime) {
		this.comentTime = comentTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsTopcoment() {
		return this.isTopcoment;
	}

	public void setIsTopcoment(Boolean isTopcoment) {
		this.isTopcoment = isTopcoment;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TopicComent getTopicComent1() {
		return this.topicComent1;
	}

	public void setTopicComent1(TopicComent topicComent1) {
		this.topicComent1 = topicComent1;
	}

	public List<TopicComent> getTopicComents1() {
		return this.topicComents1;
	}

	public void setTopicComents1(List<TopicComent> topicComents1) {
		this.topicComents1 = topicComents1;
	}

	public TopicComent addTopicComents1(TopicComent topicComents1) {
		getTopicComents1().add(topicComents1);
		topicComents1.setTopicComent1(this);

		return topicComents1;
	}

	public TopicComent removeTopicComents1(TopicComent topicComents1) {
		getTopicComents1().remove(topicComents1);
		topicComents1.setTopicComent1(null);

		return topicComents1;
	}

	public TopicComent getTopicComent2() {
		return this.topicComent2;
	}

	public void setTopicComent2(TopicComent topicComent2) {
		this.topicComent2 = topicComent2;
	}

	public List<TopicComent> getTopicComents2() {
		return this.topicComents2;
	}

	public void setTopicComents2(List<TopicComent> topicComents2) {
		this.topicComents2 = topicComents2;
	}

	public TopicComent addTopicComents2(TopicComent topicComents2) {
		getTopicComents2().add(topicComents2);
		topicComents2.setTopicComent2(this);

		return topicComents2;
	}

	public TopicComent removeTopicComents2(TopicComent topicComents2) {
		getTopicComents2().remove(topicComents2);
		topicComents2.setTopicComent2(null);

		return topicComents2;
	}

	@Override
	public String toString() {
		return "TopicComent [id=" + id + ", comentTime=" + comentTime + ", content=" + content + ", isTopcoment="
				+ isTopcoment + ", topic=" + topic + ", customer=" + customer + ", topicComent1=" + topicComent1
				+ ", topicComents1=" + topicComents1 + ", topicComent2=" + topicComent2 + ", topicComents2="
				+ topicComents2 + "]";
	}

}