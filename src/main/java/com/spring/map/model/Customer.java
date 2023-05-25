package com.spring.map.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer implements Serializable //CHILD-CLASS
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="customer_acc_no")
	private String customerAccNo;

	
	//Mostly Use Uni-Directional in CHILD CLASS
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "banks_customers",
	joinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
	private List<Bank> bankList;
}



