package mizutani_lab.groupcreater.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupingResult implements Serializable{

	public String date;				//編成日時、履歴用
	public ArrayList<EachGroupResult> eg = new ArrayList<EachGroupResult>();
	public DivideRoleData dr;
	public WeightData w;
	public SettingData sData;
	public double groupEvalVariance;
}
