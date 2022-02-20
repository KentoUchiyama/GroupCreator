package mizutani_lab.groupcreater.model;

import java.io.Serializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class DivideRoleData implements Serializable{

	public RadioButton r;
	public int divideRole;
	public int integrationGroup;
	public int riskGroup;
	public int scopeGroup;
	public int qualityGroup;
	public int communicationGroup;
	public int scheduleGroup;
	public int costGroup;
	public int procurementGroup;
	public int humanResourceGroup;

	private transient String kArea;
	private transient ComboBox<?> group;

	public DivideRoleData(String kArea, ComboBox<?> group){
		this.kArea = kArea;
		this.group = group;
	}

	public DivideRoleData(){      }

	public int getDivideRole() {
		return divideRole;
	}

	public void setDivideRole(int divideRole) {
		this.divideRole = divideRole;
	}

	public int getIntegrationGroup() {
		return integrationGroup;
	}

	public void setIntegrationGroup(int integrationGroup) {
		this.integrationGroup = integrationGroup;
	}

	public int getRiskGroup() {
		return riskGroup;
	}

	public void setRiskGroup(int riskGroup) {
		this.riskGroup = riskGroup;
	}

	public int getScopeGroup() {
		return scopeGroup;
	}

	public void setScopeGroup(int scopeGroup) {
		this.scopeGroup = scopeGroup;
	}

	public int getQualityGroup() {
		return qualityGroup;
	}

	public void setQualityGroup(int qualityGroup) {
		this.qualityGroup = qualityGroup;
	}

	public int getCommunicationGroup() {
		return communicationGroup;
	}

	public void setCommunicationGroup(int communicationGroup) {
		this.communicationGroup = communicationGroup;
	}

	public int getScheduleGroup() {
		return scheduleGroup;
	}

	public void setScheduleGroup(int scheduleGroup) {
		this.scheduleGroup = scheduleGroup;
	}

	public int getCostGroup() {
		return costGroup;
	}

	public void setCostGroup(int costGroup) {
		this.costGroup = costGroup;
	}

	public int getProcurementGroup() {
		return procurementGroup;
	}

	public void setProcurementGroup(int procurementGroup) {
		this.procurementGroup = procurementGroup;
	}

	public int getHumanResourceGroup() {
		return humanResourceGroup;
	}

	public void setHumanResourceGroup(int humanResourceGroup) {
		this.humanResourceGroup = humanResourceGroup;
	}

	public String getKArea() {
		return kArea;
	}

	public void setKArea(String kArea) {
		this.kArea = kArea;
	}

	public ComboBox<?> getGroup() {
		return group;
	}

	public void setGroup(ComboBox<?> group) {
		this.group = group;
	}

}
