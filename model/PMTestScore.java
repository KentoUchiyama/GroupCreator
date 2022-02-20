/**
 * 学生のPMテストの点数
 */
package mizutani_lab.groupcreater.model;

import java.io.Serializable;

public class PMTestScore implements Serializable{

	public double integrationScore;	//統合管理の点数
	public double riskScore;			//リスク管理の点数
	public double scheduleScore;		//スケジュール管理の点数
	public double scopeScore;			//スコープ管理の点数
	public double qualityScore;		//品質管理の点数
	public double communicationScore;	//コミュニケーション管理の点数
	public double costScore;			//コスト管理の点数
	public double procurementScore;	//調達管理の点数
	public double humanResourceScore;	//人的資源管理の点数
	public double totalScore;			//PMテストの合計点数
}
