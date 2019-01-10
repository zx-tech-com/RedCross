package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

public class TopicType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String tname;
	private String timg;
	private List<Topic> topics;

	public TopicType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	

	public String getTimg() {
		return timg;
	}

	public void setTimg(String timg) {
		this.timg = timg;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setTopicType(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setTopicType(null);

		return topic;
	}

	@Override
	public String toString() {
		return "TopicType [id=" + id + ", description=" + description + ", tname=" + tname + ", timg=" + timg
				+ ", topics=" + topics + "]";
	}

}