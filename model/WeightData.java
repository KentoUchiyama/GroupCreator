package mizutani_lab.groupcreater.model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class WeightData implements Serializable{

	private transient String kArea01;
	private transient String ffsResult1;
	private transient String ffsResult2;
	private transient String ffsResult3;
	private transient String ffsResult4;
	private transient String bigFiveResult1;
	private transient String bigFiveResult2;
	private transient String bigFiveResult3;
	private transient String bigFiveResult4;
	private transient String bigFiveResult5;

	private transient String integration;
	private transient String risk;
	private transient String schedule;
	private transient String scope;
	private transient String quality;
	private transient String humanResource;
	private transient String cost;
	private transient String procurement;
	private transient String communication;
	private transient String kArea02;
	private transient String totalScore;

	private transient String attendanceOfClass01;
	private transient String performanceOfClass01;
	private transient String submissionRateOfClass01;
	private transient String attendanceOfClass02;
	private transient String performanceOfClass02;
	private transient String submissionRateOfClass02;
	private transient String kArea03;

	public ObservableList<WeightData> books1= FXCollections.observableArrayList();
	public ObservableList<WeightData> books2= FXCollections.observableArrayList();
	public ObservableList<WeightData> books3= FXCollections.observableArrayList();

	public WeightData(String kArea01, String ffsResult1, String ffsResult2, String ffsResult3, String ffsResult4, String bigFiveResult1,
			String bigFiveResult2, String bigFiveResult3, String bigFiveResult4, String bigFiveResult5){
		this.ffsResult1 = ffsResult1;
		this.ffsResult2 = ffsResult2;
		this.ffsResult3 = ffsResult3;
		this.ffsResult4 = ffsResult4;
		this.bigFiveResult1 = bigFiveResult1;
		this.bigFiveResult2 = bigFiveResult2;
		this.bigFiveResult3 = bigFiveResult3;
		this.bigFiveResult4 = bigFiveResult4;
		this.bigFiveResult5 = bigFiveResult5;
		this.kArea01 = kArea01;
	}

	public WeightData(String kArea02,String integration, String risk, String schedule, String scope, String quality, String stakeholder,
			String cost, String procurement, String communication, String totalScore){
		this.kArea02 = kArea02;
		this.integration = integration;
		this.risk = risk;
		this.schedule = schedule;
		this.scope = scope;
		this.quality = quality;
		this.humanResource = stakeholder;
		this.cost = cost;
		this.procurement = procurement;
		this.communication = communication;
		this.totalScore = totalScore;
	}

	public WeightData(String kArea03, String attendanceOfClass01, String performanceOfClass01, String submissionRateOfClass01,
			String attendanceOfClass02, String performanceOfClass02, String submissionRateOfClass02){
		this.kArea03 = kArea03;
		this.attendanceOfClass01 = attendanceOfClass01;
		this.performanceOfClass01 = performanceOfClass01;
		this.submissionRateOfClass01 = submissionRateOfClass01;
		this.attendanceOfClass02 = attendanceOfClass02;
		this.performanceOfClass02 = performanceOfClass02;
		this.submissionRateOfClass02 = submissionRateOfClass02;
	}

	public WeightData() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String getKArea01() {
		return kArea01;
	}

	public void setKArea01(String kArea01) {
		this.kArea01 = kArea01;
	}

	public String getFfsResult1() {
		return ffsResult1;
	}

	public void setFfsResult1(String ffsResult1) {
		this.ffsResult1 = ffsResult1;
	}

	public String getFfsResult2() {
		return ffsResult2;
	}

	public void setFfsResult2(String ffsResult2) {
		this.ffsResult2 = ffsResult2;
	}

	public String getFfsResult3() {
		return ffsResult3;
	}

	public void setFfsResult3(String ffsResult3) {
		this.ffsResult3 = ffsResult3;
	}

	public String getFfsResult4() {
		return ffsResult4;
	}

	public void setFfsResult4(String ffsResult4) {
		this.ffsResult4 = ffsResult4;
	}

	public String getBigFiveResult1() {
		return bigFiveResult1;
	}

	public void setBigFiveResult1(String bigFiveResult1) {
		this.bigFiveResult1 = bigFiveResult1;
	}

	public String getBigFiveResult2() {
		return bigFiveResult2;
	}

	public void setBigFiveResult2(String bigFiveResult2) {
		this.bigFiveResult2 = bigFiveResult2;
	}

	public String getBigFiveResult3() {
		return bigFiveResult3;
	}

	public void setBigFiveResult3(String bigFiveResult3) {
		this.bigFiveResult3 = bigFiveResult3;
	}

	public String getBigFiveResult4() {
		return bigFiveResult4;
	}

	public void setBigFiveResult4(String bigFiveResult4) {
		this.bigFiveResult4 = bigFiveResult4;
	}

	public String getBigFiveResult5() {
		return bigFiveResult5;
	}

	public void setBigFiveResult5(String bigFiveResult5) {
		this.bigFiveResult5 = bigFiveResult5;
	}

	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getHumanResource() {
		return humanResource;
	}

	public void setHumanResource(String stakeholder) {
		this.humanResource = stakeholder;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getProcurement() {
		return procurement;
	}

	public void setProcurement(String procurement) {
		this.procurement = procurement;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public String getKArea02() {
		return kArea02;
	}

	public void setKArea02(String kArea02) {
		this.kArea02 = kArea02;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getAttendanceOfClass01() {
		return attendanceOfClass01;
	}

	public void setAttendanceOfClass01(String attendanceOfClass01) {
		this.attendanceOfClass01 = attendanceOfClass01;
	}

	public String getPerformanceOfClass01() {
		return performanceOfClass01;
	}

	public void setPerformanceOfClass01(String performanceOfClass01) {
		this.performanceOfClass01 = performanceOfClass01;
	}

	public String getSubmissionRateOfClass01() {
		return submissionRateOfClass01;
	}

	public void setSubmissionRateOfClass01(String submissionRateOfClass01) {
		this.submissionRateOfClass01 = submissionRateOfClass01;
	}

	public String getAttendanceOfClass02() {
		return attendanceOfClass02;
	}

	public void setAttendanceOfClass02(String attendanceOfClass02) {
		this.attendanceOfClass02 = attendanceOfClass02;
	}

	public String getPerformanceOfClass02() {
		return performanceOfClass02;
	}

	public void setPerformanceOfClass02(String performanceOfClass02) {
		this.performanceOfClass02 = performanceOfClass02;
	}

	public String getSubmissionRateOfClass02() {
		return submissionRateOfClass02;
	}

	public void setSubmissionRateOfClass02(String submissionRateOfClass02) {
		this.submissionRateOfClass02 = submissionRateOfClass02;
	}

	public String getKArea03() {
		return kArea03;
	}

	public void setKArea03(String kArea03) {
		this.kArea03 = kArea03;
	}
}
