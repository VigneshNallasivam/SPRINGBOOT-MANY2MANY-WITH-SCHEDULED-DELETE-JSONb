package com.spring.map.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="bank_table")
@Data
@NoArgsConstructor
@ToString
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Bank implements Serializable //PARENT - CLASS
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="bank_ifsc")
	private String bankIFSC;

	@Column(name="bank_branch")
	private String bankBranch;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private Agent agent;

	public Bank(String bankName, String bankIFSC, String bankBranch, Agent agent) 
	{
		this.bankName = bankName;
		this.bankIFSC = bankIFSC;
		this.bankBranch = bankBranch;
		this.agent = agent;
	}
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER,mappedBy = "bankList")
	private List<Customer> customerList;

}
