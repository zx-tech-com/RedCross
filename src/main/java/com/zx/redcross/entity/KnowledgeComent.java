package com.zx.redcross.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 捋一下关系
 * @author Daryl
 *
 */
public class KnowledgeComent implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String comentTime;
	private Integer KnowledgeId;
	private Integer customerId;
	private String  content;
	private Boolean isTopcoment;
	private Integer comentId;
	private Integer topcomentId;
	private Knowledge knowledge;
	private Customer customer;
	private KnowledgeComent knowledgeComent1;
	private List<KnowledgeComent> knowledgeComents1;
	private KnowledgeComent knowledgeComent2;
	private List<KnowledgeComent> knowledgeComents2;

	public KnowledgeComent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getComentTime() {
		return comentTime;
	}

	public void setComentTime(String comentTime) {
		this.comentTime = comentTime;
	}

	public Boolean getIsTopcoment() {
		return isTopcoment;
	}

	public void setIsTopcoment(Boolean isTopcoment) {
		this.isTopcoment = isTopcoment;
	}


	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public KnowledgeComent getKnowledgeComent1() {
		return this.knowledgeComent1;
	}

	public void setKnowledgeComent1(KnowledgeComent knowledgeComent1) {
		this.knowledgeComent1 = knowledgeComent1;
	}

	public List<KnowledgeComent> getKnowledgeComents1() {
		return this.knowledgeComents1;
	}

	public void setKnowledgeComents1(List<KnowledgeComent> knowledgeComents1) {
		this.knowledgeComents1 = knowledgeComents1;
	}

	public KnowledgeComent addKnowledgeComents1(KnowledgeComent knowledgeComents1) {
		getKnowledgeComents1().add(knowledgeComents1);
		knowledgeComents1.setKnowledgeComent1(this);

		return knowledgeComents1;
	}

	public KnowledgeComent removeKnowledgeComents1(KnowledgeComent knowledgeComents1) {
		getKnowledgeComents1().remove(knowledgeComents1);
		knowledgeComents1.setKnowledgeComent1(null);

		return knowledgeComents1;
	}

	public KnowledgeComent getKnowledgeComent2() {
		return this.knowledgeComent2;
	}

	public void setKnowledgeComent2(KnowledgeComent knowledgeComent2) {
		this.knowledgeComent2 = knowledgeComent2;
	}

	public List<KnowledgeComent> getKnowledgeComents2() {
		return this.knowledgeComents2;
	}

	public void setKnowledgeComents2(List<KnowledgeComent> knowledgeComents2) {
		this.knowledgeComents2 = knowledgeComents2;
	}

	public KnowledgeComent addKnowledgeComents2(KnowledgeComent knowledgeComents2) {
		getKnowledgeComents2().add(knowledgeComents2);
		knowledgeComents2.setKnowledgeComent2(this);

		return knowledgeComents2;
	}

	public KnowledgeComent removeKnowledgeComents2(KnowledgeComent knowledgeComents2) {
		getKnowledgeComents2().remove(knowledgeComents2);
		knowledgeComents2.setKnowledgeComent2(null);

		return knowledgeComents2;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}
	
	public Integer getKnowledgeId() {
		return KnowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		KnowledgeId = knowledgeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getComentId() {
		return comentId;
	}

	public void setComentId(Integer comentId) {
		this.comentId = comentId;
	}

	public Integer getTopcomentId() {
		return topcomentId;
	}

	public void setTopcomentId(Integer topcomentId) {
		this.topcomentId = topcomentId;
	}

	@Override
	public String toString() {
		return "KnowledgeComent [id=" + id + ", comentTime=" + comentTime + ", KnowledgeId=" + KnowledgeId
				+ ", customerId=" + customerId + ", content=" + content + ", isTopcoment=" + isTopcoment + ", comentId="
				+ comentId + ", topcomentId=" + topcomentId + ", knowledge=" + knowledge + ", customer=" + customer
				+ ", knowledgeComent1=" + knowledgeComent1 + ", knowledgeComents1=" + knowledgeComents1
				+ ", knowledgeComent2=" + knowledgeComent2 + ", knowledgeComents2=" + knowledgeComents2 + "]";
	}

	
	
}