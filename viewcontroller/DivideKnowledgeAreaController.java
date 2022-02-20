/*
 * 役割分割の設定をする画面のコントローラークラス
 */
package mizutani_lab.groupcreater.viewcontroller;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import mizutani_lab.groupcreater.model.DivideRoleData;
import mizutani_lab.groupcreater.model.ProjectModel;

public class DivideKnowledgeAreaController implements Initializable{

	@FXML
	private TableView<DivideRoleData> table01;				//知識エリアの表
	@FXML
	private TableColumn<DivideRoleData, String> kAreaCol;		//知識エリアの列
	@FXML
	private TableColumn<DivideRoleData, String> groupCol;		//グループの列
	@FXML
	private ComboBox<String> comboBox;						//分割数を指定するコンボボックス
	@FXML
	private Button setGroupButton;					//完了ボタン
	@FXML
	RadioButton textminingResult;					//テキストマイニングを選択するラジオボタン
	@FXML
	RadioButton selfSet;							//自分で編集を選択するラジオボタン

	public static boolean divideFlag2;					//true:二回目以降の遷移  false:最初の遷移


	@FXML
	final ToggleGroup group = new ToggleGroup();	//ラジオボタンのトグルグループ

	//グループを指定するコンボボックス
	private ComboBox<String> tableComboBox1 = new ComboBox<String>();
	private ComboBox<String> tableComboBox2 = new ComboBox<String>();
	private ComboBox<String> tableComboBox3 = new ComboBox<String>();
	private ComboBox<String> tableComboBox4 = new ComboBox<String>();
	private ComboBox<String> tableComboBox5 = new ComboBox<String>();
	private ComboBox<String> tableComboBox6 = new ComboBox<String>();
	private ComboBox<String> tableComboBox7 = new ComboBox<String>();
	private ComboBox<String> tableComboBox8 = new ComboBox<String>();
	private ComboBox<String> tableComboBox9 = new ComboBox<String>();

	//コンボボックスに値を入れる
	private ObservableList<String> comboBoxData = FXCollections.observableArrayList("3", "4","5");
	private ObservableList<String> tableComboBoxData3 = FXCollections.observableArrayList("1","2","3", "0");
	private ObservableList<String> tableComboBoxData4 = FXCollections.observableArrayList("1","2","3","4", "0");
	private ObservableList<String> tableComboBoxData5 = FXCollections.observableArrayList("1","2","3", "4", "5", "0");

	private ObservableList<DivideRoleData> books1 = FXCollections.observableArrayList(
			new DivideRoleData("統合管理", tableComboBox1),
			new DivideRoleData("リスク管理", tableComboBox2),
			new DivideRoleData("スコープ管理",  tableComboBox3),
			new DivideRoleData("品質管理",  tableComboBox4),
			new DivideRoleData("スケジュール管理",  tableComboBox5),
			new DivideRoleData("コスト管理",  tableComboBox6),
			new DivideRoleData("コミュニケーション管理",  tableComboBox7),
			new DivideRoleData("調達管理",  tableComboBox8),
			new DivideRoleData("人的資源管理",  tableComboBox9)
			);

	@FXML
	private void handleComboBoxAction(MouseEvent e) {
		if(group.getSelectedToggle() == textminingResult){
			if(comboBox.getSelectionModel().getSelectedItem().equals("3") || comboBox.getSelectionModel().getSelectedItem().equals("4") ||comboBox.getSelectionModel().getSelectedItem().equals("5")){
				rbtnTextminingResult_onClick(e);
			}
		}
		if(group.getSelectedToggle() == selfSet){
			if(comboBox.getSelectionModel().getSelectedItem().equals("3") || comboBox.getSelectionModel().getSelectedItem().equals("4") ||comboBox.getSelectionModel().getSelectedItem().equals("5")){
				rbtnSelfSet_onClick(e);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ

		// Init ComboBox
		comboBox.setItems(comboBoxData);
		comboBox.setValue("3");

		rbtnTextminingResult_onClick(null);

		//列と属性の対応付け
		kAreaCol.setCellValueFactory(new PropertyValueFactory<>("kArea"));
		groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));



		textminingResult.setDisable(false);
		selfSet.setDisable(false);

		if(divideFlag2 == false){		//最初の遷移
			textminingResult.setSelected(true);

		}else {							//二回目以降の遷移
			if(ProjectModel.getInstance().pd.dr.r.getId().equals("textminingResult")){
				textminingResult.setSelected(true);
			}else{
				selfSet.setSelected(true);
			}
			comboBox.setValue(""+ProjectModel.getInstance().pd.dr.divideRole);
			tableComboBox1.setValue(""+ProjectModel.getInstance().pd.dr.integrationGroup);
			tableComboBox2.setValue(""+ProjectModel.getInstance().pd.dr.riskGroup);
			tableComboBox3.setValue(""+ProjectModel.getInstance().pd.dr.scopeGroup);
			tableComboBox4.setValue(""+ProjectModel.getInstance().pd.dr.qualityGroup);
			tableComboBox5.setValue(""+ProjectModel.getInstance().pd.dr.scheduleGroup);
			tableComboBox6.setValue(""+ProjectModel.getInstance().pd.dr.costGroup);
			tableComboBox7.setValue(""+ProjectModel.getInstance().pd.dr.communicationGroup);
			tableComboBox8.setValue(""+ProjectModel.getInstance().pd.dr.procurementGroup);
			tableComboBox9.setValue(""+ProjectModel.getInstance().pd.dr.humanResourceGroup);
		}
		divideFlag2 = true;
	}

	@FXML
	private void rbtnTextminingResult_onClick(MouseEvent e){

		if(comboBox.getValue().equals("3")){

			tableComboBox1.setItems(tableComboBoxData3);
			tableComboBox2.setItems(tableComboBoxData3);
			tableComboBox3.setItems(tableComboBoxData3);
			tableComboBox4.setItems(tableComboBoxData3);
			tableComboBox5.setItems(tableComboBoxData3);
			tableComboBox6.setItems(tableComboBoxData3);
			tableComboBox7.setItems(tableComboBoxData3);
			tableComboBox8.setItems(tableComboBoxData3);
			tableComboBox9.setItems(tableComboBoxData3);

			tableComboBox1.setValue("1");
			tableComboBox2.setValue("2");
			tableComboBox3.setValue("3");
			tableComboBox4.setValue("3");
			tableComboBox5.setValue("3");
			tableComboBox6.setValue("3");
			tableComboBox7.setValue("3");
			tableComboBox8.setValue("3");
			tableComboBox9.setValue("3");
		}

		else if(comboBox.getValue().equals("4")){

			tableComboBox1.setItems(tableComboBoxData4);
			tableComboBox2.setItems(tableComboBoxData4);
			tableComboBox3.setItems(tableComboBoxData4);
			tableComboBox4.setItems(tableComboBoxData4);
			tableComboBox5.setItems(tableComboBoxData4);
			tableComboBox6.setItems(tableComboBoxData4);
			tableComboBox7.setItems(tableComboBoxData4);
			tableComboBox8.setItems(tableComboBoxData4);
			tableComboBox9.setItems(tableComboBoxData4);

			tableComboBox1.setValue("1");
			tableComboBox2.setValue("2");
			tableComboBox3.setValue("4");
			tableComboBox4.setValue("4");
			tableComboBox5.setValue("3");
			tableComboBox6.setValue("3");
			tableComboBox7.setValue("4");
			tableComboBox8.setValue("3");
			tableComboBox9.setValue("4");
		}

		else if(comboBox.getValue().equals("5")){

			tableComboBox1.setItems(tableComboBoxData5);
			tableComboBox2.setItems(tableComboBoxData5);
			tableComboBox3.setItems(tableComboBoxData5);
			tableComboBox4.setItems(tableComboBoxData5);
			tableComboBox5.setItems(tableComboBoxData5);
			tableComboBox6.setItems(tableComboBoxData5);
			tableComboBox7.setItems(tableComboBoxData5);
			tableComboBox8.setItems(tableComboBoxData5);
			tableComboBox9.setItems(tableComboBoxData5);

			tableComboBox1.setValue("1");
			tableComboBox2.setValue("2");
			tableComboBox3.setValue("5");
			tableComboBox4.setValue("5");
			tableComboBox5.setValue("3");
			tableComboBox6.setValue("4");
			tableComboBox7.setValue("5");
			tableComboBox8.setValue("4");
			tableComboBox9.setValue("4");
		}
		table01.setItems(books1);
	}

	@FXML
	private void rbtnSelfSet_onClick(MouseEvent e){

		if(comboBox.getValue().equals("3")){

			tableComboBox1.setItems(tableComboBoxData3);
			tableComboBox2.setItems(tableComboBoxData3);
			tableComboBox3.setItems(tableComboBoxData3);
			tableComboBox4.setItems(tableComboBoxData3);
			tableComboBox5.setItems(tableComboBoxData3);
			tableComboBox6.setItems(tableComboBoxData3);
			tableComboBox7.setItems(tableComboBoxData3);
			tableComboBox8.setItems(tableComboBoxData3);
			tableComboBox9.setItems(tableComboBoxData3);

			tableComboBox1.setValue("1");
			tableComboBox2.setValue("1");
			tableComboBox3.setValue("1");
			tableComboBox4.setValue("1");
			tableComboBox5.setValue("1");
			tableComboBox6.setValue("1");
			tableComboBox7.setValue("1");
			tableComboBox8.setValue("1");
			tableComboBox9.setValue("1");
		}

		else if(comboBox.getValue().equals("4")){

			tableComboBox1.setItems(tableComboBoxData4);
			tableComboBox2.setItems(tableComboBoxData4);
			tableComboBox3.setItems(tableComboBoxData4);
			tableComboBox4.setItems(tableComboBoxData4);
			tableComboBox5.setItems(tableComboBoxData4);
			tableComboBox6.setItems(tableComboBoxData4);
			tableComboBox7.setItems(tableComboBoxData4);
			tableComboBox8.setItems(tableComboBoxData4);
			tableComboBox9.setItems(tableComboBoxData4);

			tableComboBox1.setValue("1");
			tableComboBox2.setValue("1");
			tableComboBox3.setValue("1");
			tableComboBox4.setValue("1");
			tableComboBox5.setValue("1");
			tableComboBox6.setValue("1");
			tableComboBox7.setValue("1");
			tableComboBox8.setValue("1");
			tableComboBox9.setValue("1");


		} else if(comboBox.getValue().equals("5")){

			tableComboBox1.setItems(tableComboBoxData5);
			tableComboBox2.setItems(tableComboBoxData5);
			tableComboBox3.setItems(tableComboBoxData5);
			tableComboBox4.setItems(tableComboBoxData5);
			tableComboBox5.setItems(tableComboBoxData5);
			tableComboBox6.setItems(tableComboBoxData5);
			tableComboBox7.setItems(tableComboBoxData5);
			tableComboBox8.setItems(tableComboBoxData5);
			tableComboBox9.setItems(tableComboBoxData5);

			tableComboBox1.setValue("1");
			tableComboBox2.setValue("1");
			tableComboBox3.setValue("1");
			tableComboBox4.setValue("1");
			tableComboBox5.setValue("1");
			tableComboBox6.setValue("1");
			tableComboBox7.setValue("1");
			tableComboBox8.setValue("1");
			tableComboBox9.setValue("1");
			}
		table01.setItems(books1);
	}

	@FXML
	private void btnsetGroup_onClick(MouseEvent e){		//確定ボタンが押されたときの処理
		RadioButton rb = new RadioButton();
		try {
			if(textminingResult.isSelected()==true){
				rb.setId("textminingResult");
			}else{
				rb.setId("selfSet");
			}
			//設定した値をモデルに渡す
			ProjectModel.getInstance().setDivideRole(rb, Integer.parseInt(comboBox.getValue()),
					Integer.parseInt(tableComboBox1.getValue()),
					Integer.parseInt(tableComboBox2.getValue()),
					Integer.parseInt(tableComboBox3.getValue()),
					Integer.parseInt(tableComboBox4.getValue()),
					Integer.parseInt(tableComboBox5.getValue()),
					Integer.parseInt(tableComboBox6.getValue()),
					Integer.parseInt(tableComboBox7.getValue()),
					Integer.parseInt(tableComboBox8.getValue()),
					Integer.parseInt(tableComboBox9.getValue())
					);

			//画面遷移
			Stage newStage = new Stage();
	        Window window = setGroupButton.getScene().getWindow();
	        newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/InputParameters.fxml"));
			Scene scene = new Scene(root,470,543);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			setGroupButton.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
