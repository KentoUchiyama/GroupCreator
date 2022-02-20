/*
 * スタート画面のコントローラークラス
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import mizutani_lab.groupcreater.model.ProjectModel;

public class TopPageController implements Initializable{

	@FXML
	private Button createButton;		//「プロジェクト作成」ボタン
	@FXML
	private Button selectButton;		//「プロジェクト選択」ボタン

	final FileChooser fc = new FileChooser();	//FileChooserクラスのインスタンス

	SetWeightPageController s = new SetWeightPageController();
	DivideKnowledgeAreaController d = new DivideKnowledgeAreaController();
	InputParametersController ip = new InputParametersController();

	@FXML
    public void btnCreate_onClick(MouseEvent e){		//「プロジェクト作成」ボタンが押されたときの処理

		try {
			Stage newStage = new Stage();
	        Window window = createButton.getScene().getWindow();
	        newStage.initOwner(window);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/CreateProject.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
			createButton.getScene().getWindow().hide();
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
    public void btnSelect_onClick(MouseEvent e) {		//「プロジェクト選択」ボタンが押されたときの処理

		fc.setTitle("スタディファイルの選択");					//ファイルダイアログのタイトルを指定
		fc.getExtensionFilters().add(new ExtensionFilter("スタディファイル","*xml"));

		File importFile = fc.showOpenDialog(null);		//ファイルダイアログを出力

		if (importFile != null){				//ファイルが選択されたときの処理

			ProjectModel.projectFile= importFile.getPath().toString();
			ProjectModel.getInstance().loadProjectFile(ProjectModel.projectFile);

			try {
				s.weightFlag2 = true;
				d.divideFlag2 = true;
				ip.inputFlag = true;

				//分析パラメータ入力画面へ遷移
				Stage newStage = new Stage();
				Window window = selectButton.getScene().getWindow();
				newStage.initOwner(window);
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/InputParameters.fxml"));
				Scene scene = new Scene(root,470,543);
				scene.getStylesheets().add(getClass().getResource("../main/application.css").toExternalForm());
				selectButton.getScene().getWindow().hide();
				newStage.setScene(scene);
				newStage.setResizable(false);
				newStage.show();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
		}else{ return; }		//ダイアログを閉じたとき(ファイルが選択されなかったとき)
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		s.weightFlag2 = false;
		d.divideFlag2 = false;
		ip.inputFlag = false;
	}

}
