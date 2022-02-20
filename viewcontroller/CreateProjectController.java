/*
 * プロジェクト作成画面のコントローラークラス
 *
 */

package mizutani_lab.groupcreater.viewcontroller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import mizutani_lab.groupcreater.model.ProjectModel;

public class CreateProjectController extends AnchorPane implements Initializable{

	final FileChooser fc = new FileChooser();				//FileChooserクラスのインスタンス
	final DirectoryChooser dc = new DirectoryChooser();		//DirectoryChooserクラスのインスタンス

	@FXML
	private Button gotoInputParameters;		//完了ボタン
	@FXML
	private Button analysisFileDialog;		//分析ファイルダイアログを開くボタン
	@FXML
	private Button saveAddressDialog;		//保存先フォルダダイアログを開くボタン
	@FXML
	public TextField analysisFile;			//分析ファイルのパスを表示するテキストボックス
	@FXML
	private TextField saveAddress;			//保存先フォルダのパスを表示するテキストボックス
	@FXML
	private TextField projectName;		//プロジェクト名を入力するテキストボックス
	@FXML
	private Label label;					//入力がされていない項目があるときに表示するラベル

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ

		//projectName.setText("SampleProject");
		//研究室
		//analysisFile.setText("C:\\Users\\utiyama\\Desktop\\グループ自動編成システム(卒業研究)\\GroupCreater\\sample\\importFile\\dataSample.txt");
		//saveAddress.setText("C:\\Users\\utiyama\\Documents");

		//自宅
		//analysisFile.setText("C:\\Users\\master\\Desktop\\dataSample.txt");
		//saveAddress.setText("C:\\Users\\master\\Documents");

		label.setText("");
	}

	//「完了」ボタンが押されたときの処理
	@FXML
	public void btn_Ok_onClick(MouseEvent e){

		//入力チェック
		if(analysisFile.getText().equals("")||saveAddress.getText().equals("")||projectName.getText().equals("")){
			label.setText("全ての項目を入力してください");
		} else {

			ProjectModel.csvAddress = analysisFile.getText();
			ProjectModel.directoryAddress = saveAddress.getText();
			ProjectModel.projectFile = projectName.getText();

			if(ProjectModel.csvAddress != null){
				boolean r = ProjectModel.getInstance().loadImportData(ProjectModel.csvAddress);
				if(r == false)return;
			}

			//分析パラメータ入力画面へ遷移
			try {
				Stage newStage = new Stage();
		        Window window = gotoInputParameters.getScene().getWindow();
		        newStage.initOwner(window);
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/InputParameters.fxml"));
				Scene scene = new Scene(root,470,543);
				scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
				gotoInputParameters.getScene().getWindow().hide();
				newStage.setScene(scene);
				newStage.setResizable(false);
				newStage.show();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	//分析ファイルの参照ボタンを押したときの処理
	@FXML
	public void btnAnalysisFileDialog_onClick(MouseEvent e){

		fc.setTitle("ファイルの選択");					//ファイルダイアログのタイトルを指定
		fc.getExtensionFilters().add(new ExtensionFilter("テキスト(タブ区切り)","*txt"));

		File importFile = fc.showOpenDialog(null);		//ファイルダイアログを出力

		//ファイルダイアログからファイルが選択されたときの処理
		if (importFile != null) {
			analysisFile.setText(importFile.getPath().toString());
        }
	}

	//保存先ファイルの参照ボタンを押したときの処理
	@FXML
	public void btnSaveAddressDialog_onClick(MouseEvent e){
		//フォルダダイアログを出力
		File importFile = dc.showDialog(null);

		//フォルダダイアログからフォルダが選択されたときの処理
		if (importFile != null) {
			saveAddress.setText(importFile.getPath().toString());
        }
	}
}
