/*
 * 分析パラメータ入力画面のコントローラークラス
 *
 */
package mizutani_lab.groupcreater.viewcontroller;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import mizutani_lab.groupcreater.model.ProjectModel;

public class InputParametersController implements Initializable, Serializable{

	@FXML
	private ComboBox<String> comboBox;			//性格検査を選択するコンボボックス

	//リストに性格検査の種類を入れる
	private ObservableList<String> comboBoxData = FXCollections.observableArrayList("FFS","BigFive");

	@FXML
	private Button gotoKnowledgeArea;				//「知識エリアと適性の関連付け」ボタン
	@FXML
	private Button gotoDivide;						//「知識エリアの分割」ボタン
	@FXML
	private RadioButton groupRadioButton;			//「グループ数」ラジオボタン
	@FXML
	private RadioButton menberRadioButton;			//「メンバー数」ラジオボタン
	@FXML
	private TextField groupTextField;				//「グループ数」テキスト
	@FXML
	private TextField menberTextField;				//「メンバー数」テキスト
	@FXML
	private Button createGroupButton;				//「編成開始」ボタン
	@FXML
	private Label lbl;								//入力されていない項目があったときに表示するラベル
	@FXML
	private TextField comment;
	@FXML
	private Button historyButton;
	@FXML
	private Button saveButton;
	@FXML
	private Label saveLbl;				//データを保存したときに表示するラベル
	@FXML
	private Button showStudentBtn;

	SetWeightPageController s = new SetWeightPageController();
	DivideKnowledgeAreaController d = new DivideKnowledgeAreaController();

	public static boolean inputFlag;			//true:二回目以降の遷移    false:最初の遷移

	@FXML
	private void btnSave_onClick(MouseEvent e){

		saveLbl.setText("データが保存されました");

		//項目がすべて入力されている場合は内容を保存
		if(/*comment.getText().equals("")
				||*/(groupRadioButton.isSelected()==true&&groupTextField.getText().equals(""))
				||(menberRadioButton.isSelected()==true&&menberTextField.getText().equals(""))
				||comboBox.getValue()==null){

			}else{
				if(groupRadioButton.isSelected()==true){
					ProjectModel.getInstance().setData(Integer.parseInt(groupTextField.getText()), 0, comboBox.getValue(), comment.getText(), "group");
				}
				if(menberRadioButton.isSelected()==true){
					ProjectModel.getInstance().setData(0, Integer.parseInt(menberTextField.getText()), comboBox.getValue(), comment.getText(), "menber");
				}
			}
		ProjectModel.getInstance().saveProjectData(ProjectModel.directoryAddress);
	}

	//知識エリアと適性の関連付けに移動
	@FXML
	private void btn_knowledgeArea_onClick(MouseEvent e){
		//項目がすべて入力されている場合は内容を保存
		if(/*coment.getText().equals("")
				||*/(groupRadioButton.isSelected()==true&&groupTextField.getText().equals(""))
				||(menberRadioButton.isSelected()==true&&menberTextField.getText().equals(""))
				||comboBox.getValue()==null){

			}else{
				if(groupRadioButton.isSelected()==true){
					ProjectModel.getInstance().setData(Integer.parseInt(groupTextField.getText()), 0, comboBox.getValue(), comment.getText(), "group");
				}
				if(menberRadioButton.isSelected()==true){
					ProjectModel.getInstance().setData(0, Integer.parseInt(menberTextField.getText()), comboBox.getValue(), comment.getText(), "menber");
				}
			}

		try {
			Stage newStage = new Stage();
	        Window window = gotoKnowledgeArea.getScene().getWindow();
	        newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/SetWeightPage.fxml"));
			Scene scene = new Scene(root,923,679);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			gotoKnowledgeArea.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void btnDivide_onClick(MouseEvent e){
		//項目がすべて入力されている場合は内容を保存
		if(/*comment.getText().equals("")
				||*/(groupRadioButton.isSelected()==true&&groupTextField.getText().equals(""))
				||(menberRadioButton.isSelected()==true&&menberTextField.getText().equals(""))
				||comboBox.getValue()==null){
			}else{
				if(groupRadioButton.isSelected()==true){
					ProjectModel.getInstance().setData(Integer.parseInt(groupTextField.getText()), 0, comboBox.getValue(), comment.getText(), "group");
				}
				if(menberRadioButton.isSelected()==true){
					ProjectModel.getInstance().setData(0, Integer.parseInt(menberTextField.getText()), comboBox.getValue(), comment.getText(), "menber");
				}
			}

		try {
			Stage newStage = new Stage();
	        Window window = gotoDivide.getScene().getWindow();
	        newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/DivideKnowledgeArea.fxml"));
			Scene scene = new Scene(root,507,520);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			gotoDivide.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void handleComboBoxAction() {   }

	@FXML
	private void btnStart_onClick(MouseEvent e){

		if(/*comment.getText().equals("")
		||*/(groupRadioButton.isSelected()==true&&groupTextField.getText().equals(""))
		||(menberRadioButton.isSelected()==true&&menberTextField.getText().equals(""))
		||comboBox.getValue()==null){
			lbl.setText("全ての項目を入力してください");
		}else{
			if(groupRadioButton.isSelected()==true){
				ProjectModel.getInstance().setData(Integer.parseInt(groupTextField.getText()), 0, comboBox.getValue(), comment.getText(), "group");
				ProjectModel.getInstance().pd.sData.numberOfGroup = Integer.parseInt(groupTextField.getText());
				ProjectModel.getInstance().pd.sData.numberOfMenber = ProjectModel.getInstance().pd.studentData.size()/Integer.parseInt(groupTextField.getText());
			}
			if(menberRadioButton.isSelected()==true){
				ProjectModel.getInstance().setData(0, Integer.parseInt(menberTextField.getText()), comboBox.getValue(), comment.getText(), "menber");
				ProjectModel.getInstance().pd.sData.numberOfMenber = Integer.parseInt(menberTextField.getText());
				ProjectModel.getInstance().pd.sData.numberOfGroup =  ProjectModel.getInstance().pd.studentData.size()/Integer.parseInt(menberTextField.getText());
			}
			ProjectModel.getInstance().createGroup();
			ProjectModel.getInstance().createResultHTML();
			ProjectModel.getInstance().createSettingDataHTML();
		}
	}

	@FXML
	public void btnShowStudnet_onClick(MouseEvent e){

		//項目がすべて入力されている場合は内容を保存
		if(/*comment.getText().equals("")
		||*/(groupRadioButton.isSelected()==true&&groupTextField.getText().equals(""))
		||(menberRadioButton.isSelected()==true&&menberTextField.getText().equals(""))
		||comboBox.getValue()==null){
		}else{
			if(groupRadioButton.isSelected()==true){
				ProjectModel.getInstance().setData(Integer.parseInt(groupTextField.getText()), 0, comboBox.getValue(), comment.getText(), "group");
			}
			if(menberRadioButton.isSelected()==true){
				ProjectModel.getInstance().setData(0, Integer.parseInt(menberTextField.getText()), comboBox.getValue(), comment.getText(), "menber");
			}
		}
		//インポートした学生のデータを確認
		try {
			Stage newStage = new Stage();
			Window window = showStudentBtn.getScene().getWindow();
			newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/ShowStudent.fxml"));
			Scene scene = new Scene(root,600,435);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			showStudentBtn.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		saveLbl.setText("");

		// Init ComboBox
		comboBox.setItems(comboBoxData);
		lbl.setText("");

		if(inputFlag == false){		//最初の遷移
			//comment.setText("");
			//groupTextField.setText("");
			comboBox.setValue("FFS");
			menberTextField.setEditable(false);

			ProjectModel.getInstance().setData(0, 0, comboBox.getValue(), comment.getText(), "group");

			inputFlag = true;
		}else{						//二回目以降の遷移
			comment.setText(ProjectModel.getInstance().pd.sData.comment);
			comboBox.setValue(ProjectModel.getInstance().pd.sData.persTest);
			if(ProjectModel.getInstance().pd.sData.rb.getId().equals("group")) {
				groupRadioButton.setSelected(true);
				groupTextField.setText(""+ProjectModel.getInstance().pd.sData.numberOfGroup);
				menberTextField.setEditable(false);
			}
			if(ProjectModel.getInstance().pd.sData.rb.getId().equals("menber")) {
				menberRadioButton.setSelected(true);
				menberTextField.setText(""+ProjectModel.getInstance().pd.sData.numberOfMenber);
				groupTextField.setEditable(false);
			}
			comboBox.setValue(ProjectModel.getInstance().pd.sData.persTest);
		}
	}

	@FXML
	private void rbtnGroup_onClick(MouseEvent e){		//「グループ数」のラジオボタンが押されたときの処理
		groupTextField.setEditable(true);
		menberTextField.setEditable(false);
	}

	@FXML
	private void rbtnMenbeer_onClick(MouseEvent e){		//「メンバー数」のラジオボタンが押された時の処理
		menberTextField.setEditable(true);
		groupTextField.setEditable(false);
	}

	public static boolean isInputFlag() {
		return inputFlag;
	}

	public static void setInputFlag(boolean inputFlag) {
		InputParametersController.inputFlag = inputFlag;
	}
}
