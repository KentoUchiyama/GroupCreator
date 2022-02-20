package mizutani_lab.groupcreater.viewcontroller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import mizutani_lab.groupcreater.model.ProjectModel;

public class ShowStudentController implements Initializable{

	@FXML
	private Button rbtn;
	@FXML
	private TextArea textArea;

	private String str = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		for(int i = 0; i < ProjectModel.getInstance().pd.studentData.size(); i++){
			str += ProjectModel.getInstance().pd.studentData.get(i).id + "\t"
				+ ProjectModel.getInstance().pd.studentData.get(i).name + "\n";
			textArea.setText(str);
		}
		textArea.setEditable(false);
	}

	@FXML
	public void btnRbtn_onClick(MouseEvent e){
		try {
			//画面遷移
			Stage newStage = new Stage();
	        Window window = rbtn.getScene().getWindow();
	        newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/InputParameters.fxml"));
			Scene scene = new Scene(root,470,543);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			rbtn.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
