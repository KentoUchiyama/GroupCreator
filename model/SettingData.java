package mizutani_lab.groupcreater.model;

import java.io.Serializable;

import javafx.scene.control.RadioButton;

public class SettingData implements Serializable{

	public int numberOfGroup;
	public int numberOfMenber;
	public String persTest;
	public String comment;
	public RadioButton rb = new RadioButton();
}
