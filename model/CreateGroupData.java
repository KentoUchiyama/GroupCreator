package mizutani_lab.groupcreater.model;

import java.util.ArrayList;

public class CreateGroupData{

	public double groupEvalVariance;					//編成案ごとの分散を保持する
	public ArrayList<StudentResult> personalEval = new ArrayList<StudentResult>();			//個人の評価値を保持する
	public ArrayList<StudentData> studentList = new ArrayList<StudentData>();				//編成案を保持する
	public ArrayList<EachGroupResult> eg = new ArrayList<EachGroupResult>();
}
