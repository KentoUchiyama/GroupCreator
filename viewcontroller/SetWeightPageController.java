
/*
 * 重みの設定をする画面のコントローラークラス
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import mizutani_lab.groupcreater.model.ProjectModel;
import mizutani_lab.groupcreater.model.WeightData;

public class SetWeightPageController implements Initializable{

	@FXML
	private TableView<WeightData> table1;		//1番上のテーブル
	@FXML
	private TableColumn<WeightData, String> kArea01Col;				//table1の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> ffsResult1Col;			//table1の2列目のカラム
	@FXML
	private TableColumn<WeightData, String> ffsResult2Col;			//table1の3列目のカラム
	@FXML
	private TableColumn<WeightData, String> ffsResult3Col;
	@FXML
	private TableColumn<WeightData, String> ffsResult4Col;
	@FXML
	private TableColumn<WeightData, String> bigFiveResult1Col;
	@FXML
	private TableColumn<WeightData, String> bigFiveResult2Col;
	@FXML
	private TableColumn<WeightData, String> bigFiveResult3Col;
	@FXML
	private TableColumn<WeightData, String> bigFiveResult4Col;
	@FXML
	private TableColumn<WeightData, String> bigFiveResult5Col;

	@FXML
	private TableView<WeightData> table2;	//真ん中のテーブル
	@FXML
	private TableColumn<WeightData, String> kArea02Col;			//table2の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> integrationCol;		//table2の2列目のカラム
	@FXML
	private TableColumn<WeightData, String> riskCol;			//table2の3列目のカラム
	@FXML
	private TableColumn<WeightData, String> scheduleCol;		//table2の4列目のカラム
	@FXML
	private TableColumn<WeightData, String> scopeCol;			//table2の5列目のカラム
	@FXML
	private TableColumn<WeightData, String> qualityCol;			//table2の6列目のカラム
	@FXML
	private TableColumn<WeightData, String> stakeholderCol;		//table2の7列目のカラム
	@FXML
	private TableColumn<WeightData, String> costCol;			//table2の8列目のカラム
	@FXML
	private TableColumn<WeightData, String> procurementCol;		//table2の9列目のカラム
	@FXML
	private TableColumn<WeightData, String> communicationCol;	//table2の10列目のカラム
	@FXML
	private TableColumn<WeightData, String> totalScoreCol;

	@FXML
	private TableView<WeightData> table3;			//1番下のテーブル
	@FXML
	private TableColumn<WeightData, String> kArea03Col;					//table3の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> attendanceOfClass1Col;		//table3の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> performanceOfClass01Col;	//table3の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> submissionRateOfClass01Col;	//table3の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> attendanceOfClass2Col;		//table3の1列目のカラム
	@FXML
	private TableColumn<WeightData, String>performanceOfClass02Col;		//table3の1列目のカラム
	@FXML
	private TableColumn<WeightData, String> submissionRateOfClass02Col;	//table3の1列目のカラム

	@FXML
	private Label lbl2;

	@FXML
	private Button setWeightButton;		//「確定」ボタン

	public static boolean weightFlag2;					//true:二回目以降の遷移  false;最初の遷移

	//テーブルに表示する内容をリストに格納する
	ObservableList<WeightData> books1 = FXCollections.observableArrayList(
			new WeightData("統合管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("リスク管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("スコープ管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("品質管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("スケジュール管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("コスト管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("コミュニケーション管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("人的資源管理", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("調達管理", "1", "1", "1", "1", "1", "1", "1", "1", "1")
			);

	ObservableList<WeightData> books2 = FXCollections.observableArrayList(
			new WeightData("統合管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("リスク管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("スコープ管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("品質管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("スケジュール管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("コスト管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("コミュニケーション管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("人的資源管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"),
			new WeightData("調達管理", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
			);

	ObservableList<WeightData> books3 = FXCollections.observableArrayList(
			new WeightData("統合管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("リスク管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("スコープ管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("品質管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("スケジュール管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("コスト管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("コミュニケーション管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("人的資源管理", "1", "1", "1", "1", "1", "1"),
			new WeightData("調達管理", "1", "1", "1", "1", "1", "1")
			);

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if(weightFlag2 == false){				//最初の遷移
			//テーブルに表示するリストをセットする
			table1.setItems(books1);
			table2.setItems(books2);
			table3.setItems(books3);

		}else{						//二回目以降の遷移
			//テーブルに表示するリストをセットする
			table1.setItems(ProjectModel.getInstance().pd.w.books1);
			table2.setItems(ProjectModel.getInstance().pd.w.books2);
			table3.setItems(ProjectModel.getInstance().pd.w.books3);
		}
		//テーブルを編集可能にする
		table1.setEditable(true);
		table2.setEditable(true);
		table3.setEditable(true);


		//列と属性の対応付け
		kArea01Col.setCellValueFactory(new PropertyValueFactory<>("kArea01"));
		ffsResult1Col.setCellValueFactory(new PropertyValueFactory<>("ffsResult1"));
		ffsResult2Col.setCellValueFactory(new PropertyValueFactory<>("ffsResult2"));
		ffsResult3Col.setCellValueFactory(new PropertyValueFactory<>("ffsResult3"));
		ffsResult4Col.setCellValueFactory(new PropertyValueFactory<>("ffsResult4"));
		bigFiveResult1Col.setCellValueFactory(new PropertyValueFactory<>("bigFiveResult1"));
		bigFiveResult2Col.setCellValueFactory(new PropertyValueFactory<>("bigFiveResult2"));
		bigFiveResult3Col.setCellValueFactory(new PropertyValueFactory<>("bigFiveResult3"));
		bigFiveResult4Col.setCellValueFactory(new PropertyValueFactory<>("bigFiveResult4"));
		bigFiveResult5Col.setCellValueFactory(new PropertyValueFactory<>("bigFiveResult5"));


		kArea02Col.setCellValueFactory( new PropertyValueFactory<>("kArea02"));
		integrationCol.setCellValueFactory(new PropertyValueFactory<>("integration"));
		riskCol.setCellValueFactory( new PropertyValueFactory<>("risk"));
		scheduleCol.setCellValueFactory(new PropertyValueFactory<>("schedule"));
		scopeCol.setCellValueFactory(new PropertyValueFactory<>("scope"));
		qualityCol.setCellValueFactory(new PropertyValueFactory<>("quality"));
		stakeholderCol.setCellValueFactory(new PropertyValueFactory<>("humanResource"));
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		procurementCol.setCellValueFactory(new PropertyValueFactory<>("procurement"));
		communicationCol.setCellValueFactory(new PropertyValueFactory<>("communication"));
		totalScoreCol.setCellValueFactory(new PropertyValueFactory<>("totalScore"));


		kArea03Col.setCellValueFactory( new PropertyValueFactory<>("kArea03"));
		attendanceOfClass1Col.setCellValueFactory( new PropertyValueFactory<>("attendanceOfClass01"));
		performanceOfClass01Col.setCellValueFactory( new PropertyValueFactory<>("performanceOfClass01"));
		submissionRateOfClass01Col.setCellValueFactory( new PropertyValueFactory<>("submissionRateOfClass01"));
		attendanceOfClass2Col.setCellValueFactory( new PropertyValueFactory<>("attendanceOfClass02"));
		performanceOfClass02Col.setCellValueFactory( new PropertyValueFactory<>("performanceOfClass02"));
		submissionRateOfClass02Col.setCellValueFactory( new PropertyValueFactory<>("submissionRateOfClass02"));


		//セルをテキストフィールドのセルに入れ替える   ///setRowFactoryが使えるかも
		//setRowFactory(Callback<TableView<S>,TableRow<S>> value)
		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> ffsResult1Factory
			 = TextFieldTableCell.forTableColumn();
		ffsResult1Col.setCellFactory(ffsResult1Factory);
		ffsResult1Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setFfsResult1(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> ffsResult2Factory
		 = TextFieldTableCell.forTableColumn();
		ffsResult2Col.setCellFactory(ffsResult2Factory);
		ffsResult2Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setFfsResult2(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> ffsResult3Factory
		 = TextFieldTableCell.forTableColumn();
		ffsResult3Col.setCellFactory(ffsResult3Factory);
		ffsResult3Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setFfsResult3(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> ffsResult4Factory
		 = TextFieldTableCell.forTableColumn();
		ffsResult4Col.setCellFactory(ffsResult4Factory);
		ffsResult4Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setFfsResult4(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> bigFiveResult1Factory
		 = TextFieldTableCell.forTableColumn();
		bigFiveResult1Col.setCellFactory(bigFiveResult1Factory);
		bigFiveResult1Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setBigFiveResult1(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> bigFiveResult2Factory
		 = TextFieldTableCell.forTableColumn();
		bigFiveResult2Col.setCellFactory(bigFiveResult2Factory);
		bigFiveResult2Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setBigFiveResult2(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> bigFiveResult3Factory
		 = TextFieldTableCell.forTableColumn();
		bigFiveResult3Col.setCellFactory(bigFiveResult3Factory);
		bigFiveResult3Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setBigFiveResult3(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> bigFiveResult4Factory
		 = TextFieldTableCell.forTableColumn();
		bigFiveResult4Col.setCellFactory(bigFiveResult4Factory);
		bigFiveResult4Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setBigFiveResult4(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>> bigFiveResult5Factory
		 = TextFieldTableCell.forTableColumn();
		bigFiveResult5Col.setCellFactory(bigFiveResult5Factory);
		bigFiveResult5Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
		WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
		book.setBigFiveResult5(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>integrationFactory
		= TextFieldTableCell.forTableColumn();
		integrationCol.setCellFactory(integrationFactory);
		integrationCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setIntegration(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>riskFactory
		= TextFieldTableCell.forTableColumn();
		riskCol.setCellFactory(riskFactory);
		riskCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setRisk(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>scheduleFactory
		= TextFieldTableCell.forTableColumn();
		scheduleCol.setCellFactory(scheduleFactory);
		scheduleCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setSchedule(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>scopeFactory
		= TextFieldTableCell.forTableColumn();
		scopeCol.setCellFactory(scopeFactory);
		scopeCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setScope(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>qualityFactory
		= TextFieldTableCell.forTableColumn();
		qualityCol.setCellFactory(qualityFactory);
		qualityCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setQuality(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>stakeholderFactory
		= TextFieldTableCell.forTableColumn();
		stakeholderCol.setCellFactory(stakeholderFactory);
		stakeholderCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setHumanResource(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>costFactory
		= TextFieldTableCell.forTableColumn();
		costCol.setCellFactory(costFactory);
		costCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setCost(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>procurementFactory
		= TextFieldTableCell.forTableColumn();
		procurementCol.setCellFactory(procurementFactory);
		procurementCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setProcurement(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>communicationFactory
		= TextFieldTableCell.forTableColumn();
		communicationCol.setCellFactory(communicationFactory);
		communicationCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setCommunication(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>totalFactory
		= TextFieldTableCell.forTableColumn();
		totalScoreCol.setCellFactory(totalFactory);
		totalScoreCol.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setTotalScore(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>attendanceOfClass1Factory
		= TextFieldTableCell.forTableColumn();
		attendanceOfClass1Col.setCellFactory(attendanceOfClass1Factory);
		attendanceOfClass1Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setAttendanceOfClass01(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>performanceOfClass1Factory
		= TextFieldTableCell.forTableColumn();
		performanceOfClass01Col.setCellFactory(performanceOfClass1Factory);
		performanceOfClass01Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setPerformanceOfClass01(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>submissionRateOfClass1Factory
		= TextFieldTableCell.forTableColumn();
		submissionRateOfClass01Col.setCellFactory(submissionRateOfClass1Factory);
		submissionRateOfClass01Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setSubmissionRateOfClass01(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>attendanceOfClass2Factory
		= TextFieldTableCell.forTableColumn();
		attendanceOfClass2Col.setCellFactory(attendanceOfClass2Factory);
		attendanceOfClass2Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setAttendanceOfClass02(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>performanceOfClass2Factory
		= TextFieldTableCell.forTableColumn();
		performanceOfClass02Col.setCellFactory(performanceOfClass2Factory);
		performanceOfClass02Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setPerformanceOfClass02(event.getNewValue());
		});

		Callback<TableColumn<WeightData, String>, TableCell<WeightData, String>>submissionRateOfClass2Factory
		= TextFieldTableCell.forTableColumn();
		submissionRateOfClass02Col.setCellFactory(submissionRateOfClass2Factory);
		submissionRateOfClass02Col.setOnEditCommit((TableColumn.CellEditEvent<WeightData, String> event) -> {
			WeightData book = event.getTableView().getItems().get(event.getTablePosition().getRow());
			book.setSubmissionRateOfClass02(event.getNewValue());
		});
	}

	@FXML
	private void btnSetWeight_onClick(MouseEvent e){		//「確定」ボタンが押されたときの処理
		if(weightFlag2==false){
			ProjectModel.getInstance().setWeight(books1, books2, books3);
		}else{
			ProjectModel.getInstance().setWeight(ProjectModel.getInstance().pd.w.books1, ProjectModel.getInstance().pd.w.books2,
					ProjectModel.getInstance().pd.w.books3);
		}
		weightFlag2 = true;
		try {
			Stage newStage = new Stage();
	        Window window = setWeightButton.getScene().getWindow();
	        newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/InputParameters.fxml"));
			Scene scene = new Scene(root,470,543);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			setWeightButton.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
