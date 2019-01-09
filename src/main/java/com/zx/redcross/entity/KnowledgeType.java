package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

public class KnowledgeType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String kname;
	private List<Knowledge> knowledges;

	public KnowledgeType() {
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

	public String getKname() {
		return this.kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	public List<Knowledge> getKnowledges() {
		return this.knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

	public Knowledge addKnowledge(Knowledge knowledge) {
		getKnowledges().add(knowledge);
		knowledge.setKnowledgeType(this);

		return knowledge;
	}

	public Knowledge removeKnowledge(Knowledge knowledge) {
		getKnowledges().remove(knowledge);
		knowledge.setKnowledgeType(null);

		return knowledge;
	}

	@Override
	public String toString() {
		return "KnowledgeType [id=" + id + ", description=" + description + ", kname=" + kname + ", knowledges="
				+ knowledges + "]";
	}

}