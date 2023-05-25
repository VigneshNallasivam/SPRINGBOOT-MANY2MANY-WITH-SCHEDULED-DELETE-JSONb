package com.spring.map.model;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent implements Serializable
{
	@Column(name = "agent_name")
	private String agentName;
	
	@Column(name = "agent_bank_name")
	private String agentBankName;
	
	@Column(name = "agent_main_branch_location")
	private String agentMainBranchLocation;
	
	@Column(name = "agent_current_work_location")
	private String agentCurrentWorkLocation;
}
