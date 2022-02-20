package mizutani_lab.groupcreater.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;

public class ProjectModel implements Serializable{

	private static ProjectModel singleton = new ProjectModel();

	public static String csvAddress;					//csvファイルのパス
	public static String projectFile;
	public static String directoryAddress;

	public ProjectData pd = new ProjectData();
	public GroupingResult gr = new GroupingResult();
	public CreateGroupData cg = new CreateGroupData();

	HashMap<String,StudentData> map = new HashMap<String,StudentData>();

	public int[] c = new int[20];

	private ProjectModel(){  }
	public static ProjectModel getInstance(){
		return singleton;
	}

	//データを保存する
	public boolean saveProjectData(String fileName){
		try{
			if(directoryAddress != null){						//スタディを作成したとき
				ObjectOutputStream objOutStream = new ObjectOutputStream( new FileOutputStream(directoryAddress+"/"+projectFile+".xml"));
				XMLEncoder xmlEncoder = new XMLEncoder(objOutStream);
				//objOutStream.writeObject(pd);
				xmlEncoder.writeObject(pd);
				xmlEncoder.close();
				objOutStream.close();
			}else{												//スタディを選択したとき
				ObjectOutputStream objOutStream = new ObjectOutputStream( new FileOutputStream(projectFile));
				XMLEncoder xmlEncoder = new XMLEncoder(objOutStream);
				//objOutStream.writeObject(pd);
				xmlEncoder.writeObject(pd);
				xmlEncoder.close();
				objOutStream.close();
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	//学生の情報を読み込む
	public boolean loadImportData(String importFileName){
		pd = new ProjectData();
		int i = 0;
		//ファイルの読み込み
		try {
			File csv = new File(importFileName); // CSVデータファイル
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv),"SJIS"));

			String line = "";
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
		       String[] col = line.split("\t");

		       StudentData sd = new StudentData();
		       //タブ区切りテキストの内容を受け取る
		       sd.id = col[0];
		       sd.name = col[1];
		       sd.role = "";

		       //学籍番号とリストの場所の対応付け(指定した学籍番号の学生の情報がリストの何番目にあるかわかる)
		       map.put(sd.id, sd);
		       i++;

		       PersonalTestData pt = new PersonalTestData();
		       pt.ffsResult = Integer.parseInt(col[2]);
		       pt.bigFiveResult1 = Integer.parseInt(col[3]);
		       pt.bigFiveResult2 = Integer.parseInt(col[4]);
		       pt.bigFiveResult3 = Integer.parseInt(col[5]);
		       pt.bigFiveResult4 = Integer.parseInt(col[6]);
		       pt.bigFiveResult5 = Integer.parseInt(col[7]);

		       PMTestScore pm = new PMTestScore();
		       pm.totalScore = Double.parseDouble(col[8]);
		       pm.integrationScore = Double.parseDouble(col[9]);
		       pm.riskScore = Double.parseDouble(col[10]);
		       pm.scopeScore = Double.parseDouble(col[11]);
		       pm.qualityScore = Double.parseDouble(col[12]);
		       pm.scheduleScore = Double.parseDouble(col[13]);
		       pm.communicationScore = Double.parseDouble(col[14]);
		       pm.procurementScore = Double.parseDouble(col[15]);
		       pm.costScore = Double.parseDouble(col[16]);
		       pm.humanResourceScore = Double.parseDouble(col[17]);

		       AttendanceRate ar = new AttendanceRate();
		       Performance p = new Performance();
		       SubmissionRate sr = new SubmissionRate();
		       ar.attendanceRate1 = Double.parseDouble(col[18]);
		       p.performance1 = Double.parseDouble(col[19]);
		       sr.submissionRate1 = Double.parseDouble(col[20]);
		       ar.attendanceRate2 = Double.parseDouble(col[21]);
		       p.performance2 = Double.parseDouble(col[22]);
		       sr.submissionRate2 = Double.parseDouble(col[23]);

		       sd.pt = pt;
		       sd.pmTestData = pm;
		       sd.ar = ar;
		       sd.p = p;
		       sd.sr = sr;

		       pd.studentData.add(sd);
			}
			br.close();
			return true;
		} catch (FileNotFoundException en) {
	      // Fileオブジェクト生成時の例外捕捉
	      en.printStackTrace();
	      return false;
	    } catch (IOException en) {
	      // BufferedReaderオブジェクトのクローズ時の例外捕捉
	      en.printStackTrace();
	      return false;
	    }
	}

	//役割分割設定の値をモデルに渡す
	public boolean setDivideRole(RadioButton r, int a,int b,int c,int d,int e,int f,int g,int h, int i, int j){
		try{

			DivideRoleData dr = new DivideRoleData();
			dr.r = r;
			dr.divideRole = a;
			dr.integrationGroup = b;
			dr.riskGroup = c;
			dr.scopeGroup = d;
			dr.qualityGroup = e;
			dr.scheduleGroup = f;
			dr.costGroup = g;
			dr.communicationGroup = h;
			dr.procurementGroup = i;
			dr.humanResourceGroup = j;

			pd.dr = dr;
			gr.dr = dr;

			return true;
		}catch(Exception ee){
			ee.printStackTrace();
			return false;
		}
	}

	//重みをモデルに渡す
	public boolean setWeight(ObservableList<WeightData> wList1, ObservableList<WeightData> wList2,ObservableList<WeightData> wList3){
		try{
			WeightData wd = new WeightData();
			wd.books1 = wList1;
			wd.books2 = wList2;
			wd.books3 = wList3;

			pd.w = wd;
			gr.w = wd;
			return true;
		}catch(Exception e2){
			e2.printStackTrace();
			return false;
		}
	}

	//InputParametersの設定データを受け取る
	public boolean setData(int i, int j, String p, String comment, String id){
		try{
			SettingData sData = new SettingData();
			sData.numberOfGroup = i;
			sData.numberOfMenber = j;
			sData.persTest = p;
			sData.comment = comment;
			sData.rb.setId(id);

			pd.sData = sData;
			gr.sData = sData;
			return true;
		}catch(Exception e3){
			e3.printStackTrace();
			return false;
		}
	}

	//プロジェクトファイルを読み込む
	public boolean loadProjectFile(String ProjectFileName){
		pd = new ProjectData();
		try{
			if(ProjectModel.projectFile != null){		//オブジェクトファイルの読み込み
				//directoryAddress = projectFile;
				try {
					ObjectInputStream objInStream = new ObjectInputStream( new FileInputStream(ProjectFileName));
					XMLDecoder decoder = new XMLDecoder(objInStream);
					pd = (ProjectData)decoder.readObject();
					decoder.close();
					objInStream.close();
				} catch (FileNotFoundException ee) {
					ee.printStackTrace();
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
			for(int i = 0; i < pd.studentData.size(); i++){
			 map.put(pd.studentData.get(i).id, pd.studentData.get(i));
			}
			return true;
		} catch(Exception e4){
			e4.printStackTrace();
			return false;
		}
	}

	//編成結果のHTMLファイルを生成して表示
	public boolean createResultHTML(){
		//設定した役割を取得
		StringBuilder sb = new StringBuilder();							//文字列を格納する
		int g = pd.sData.numberOfGroup;									//グループ数
		int m = pd.sData.numberOfMenber;									//メンバーの数
		int count = 0;

		int amari = pd.studentData.size()%g;
		int s = amari+1;

		try{
			sb.append("<html>\n<head><title>編成結果</title>\n");
			//style
			sb.append("<style>*{margin:0}body{font-family:'メイリオ','ヒラギノ角ゴ Pro W3','Meiryo','Osaka';font-size:12pt;line-height:1.5em;margin:5px;padding:5px}.SettingArea{margin:1em;width:640px;border:1px solid#aaa;padding:1px}.SettingArea table{border:none}.SettingArea table td,tr{width:120px}.SettingSubArea{width:280px;margin:-2em 0 auto auto}.ResultArea{width:auto;clear:both}table{border:1px solid#aaa}.LinkButton{border:1px solid#aaa;background-color:#eee;color:#000;width:200px;height:auto;margin:1em;padding:1px;text-align:center}.ResultArea h3{margin-top:2ex;width:100%}.GroupArea{width:500px;float:left;margin:5px}.GroupAreaDetail{clear:both;margin:5px;border:1px solid gray;padding:3px}.GroupTable{width:480px}.GroupDetailTable{width:1200px}.GroupTable th,.GroupDetailTable th{background-color:#eee}.GroupTable tr:nth-child(2n+1),.GroupDetailTable tr:nth-child(2n+1){background-color:#eef}.GroupTable tr:nth-child(2n),.GroupDetailTable tr:nth-child(2n){background-color:#f6f6ff}</style>");

			//JavaScriptなど
			sb.append("<script type=\"text/javascript\">\n"
					+ "function fuga(elm, target) { \n	var flag = (elm.innerHTML=='隠す');\n"
					+ "	document.getElementById(target).style.display = flag?'none':'block';\n"
					+ "	elm.innerHTML = flag?'詳細結果':'隠す';\n}\n</script>\n</head>\n<body>\n<h1>自動グループ編成結果</h1>\n<div class=\"SettingArea\">"
					+ "	<h3>グループ編成設定の基本情報</h3>"
					+ "<table>\n	<tr>\n		<td>グループ数</td>\n		<td>"+g+"</td>\n	</tr>\n	<tr>\n		<td>性格検査</td>\n		<td>"+pd.sData.persTest+"</td>\n	</tr>\n</table>\n\n"
					+ "<div class=\"SettingSubArea\">\n	<h3>設定の詳細</h3>\n	<div class=\"LinkButton\"><a href=\"weight.html\">データの重み設定を確認</a></div>\n	"
					+ "<div  class=\"LinkButton\"><a href=\"divide.html\">役割の分割設定を確認</a></div>\n</div>\n</div>\n\n"
					+ "<div class=\"ResultArea\">\n");

			for(int i = 1; i <= g; i++){			//グループの数だけテーブルを生成
				//EachGroupResult eg  =new EachGroupResult();
				//gr.eg.add(eg);		//グループの数だけEachGroupResultをリストに追加していく
				sb.append("	<div class=\"GroupArea\">\n		グループ"+i+"<button onclick=\"fuga(this,'group"+i+"')\">詳細結果</button>\n		<table class=\"GroupTable\">\n");
				sb.append("		<tr><th>学籍番号</th><th>氏名</th><th>役割</th></tr>\n");
				if(amari != 0 && pd.sData.numberOfMenber%2==1 && i > s)m++;	//学生の数がグループ数で割り切れない場合はメンバを一人追加
				if(amari != 0 && pd.sData.numberOfMenber%2==0)m++;			//学生数÷グループ数が奇数なら後ろからメンバを追加、偶数なら最初からメンバを追加していく
				setRole(i-1, m);		//役割の割り当て;
				for(int j = 0; j < m; j++){			//メンバーの数だけ行を生成
					sb.append("				<tr><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).id+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).name+"</td><td>"
							+getRole(map.get(cg.eg.get(i-1).sr.get(j).id).role)+"</td></tr>\n");
					count++;
				}
				if(pd.sData.persTest.equals("FFS")){
					sb.append("		</table>\n	</div>\n 	<div class=\"GroupAreaDetail\" id=\"group"+i+"\" style=\"display:none;\">\n		グループ"+i+"の詳細<br>\n		<table table class=\"GroupDetailTable\">\n"
						+ "			<tr><th>学籍番号</th><th>氏名</th><th>役割</th><th>PMテストの点数</th><th>FFSの結果</th></tr>\n");
					count = count-m;
					for(int j = 0; j < m; j++){			//詳細結果1つ目の表
						sb.append("			<tr><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).id+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).name+"</td><td>"
								+getRole(map.get(cg.eg.get(i-1).sr.get(j).id).role)+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.totalScore+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pt.ffsResult+"</td></tr>\n");
						count++;
					}
				}else if(pd.sData.persTest.equals("BigFive")){
					sb.append("		</table>\n	</div>\n 	<div class=\"GroupAreaDetail\" id=\"group"+i+"\" style=\"display:none;\">\n		グループ"+i+"の詳細<br>\n		<table table class=\"GroupDetailTable\">\n"
							+ "			<tr><th>学籍番号</th><th>氏名</th><th>役割</th><th>PMテストの点数</th><th>BigFive(開放)の結果</th><th>BigFive(誠実)の結果</th><th>BigFive(外向)の結果</th><th>BigFive(協調)の結果</th><th>BigFive(神経)の結果</th></tr>\n");
					count = count-m;
					for(int j = 0; j < m; j++){			//詳細結果1つ目の表
						sb.append("			<tr><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).id+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).name+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).role+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.totalScore+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pt.bigFiveResult1+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pt.bigFiveResult2+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pt.bigFiveResult3+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pt.bigFiveResult4+"</td><td>"
								+map.get(cg.eg.get(i-1).sr.get(j).id).pt.bigFiveResult5+"</td></tr>\n");
						count++;
					}
				}
				sb.append("		</table><br>\n\n		知識エリアごとのPMテストの点数\n		<table table class=\"GroupDetailTable\">\n"
						+ "			<tr><th>学籍番号</th><th>氏名</th><th>統合</th><th>リスク</th><th>スコープ</th><th>品質</th>"
						+ "<th>スケジュール</th><th>コスト</th><th>コミュニケーション</th><th>調達</th><th>人的資源</th></tr>\n");
				count = count-m;
				for(int j = 0; j< m; j++){			//詳細結果2つ目の表
					sb.append("			<tr><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).id+"</td><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).name+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.integrationScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.riskScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.scopeScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.qualityScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.scheduleScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.costScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.communicationScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.procurementScore+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).pmTestData.humanResourceScore+"</td></tr>\n");
					count++;
				}
				sb.append("		</table><br>\n\n		授業に関するデータ\n		<table table class=\"GroupDetailTable\">\n"
						+ "			<tr><th>学籍番号</th><th>氏名</th><th>授業1の出席率</th><th>授業1の成績</th><th>授業1の課題提出率</th>"
						+ "<th>授業2の出席率</th><th>授業2の成績</th><th>授業2の課題提出率</th></tr>\n");
				count = count-m;
				for(int j = 0; j< m; j++){			//詳細結果3つ目の表
					sb.append("			<tr><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).id+"</td><td>"+map.get(cg.eg.get(i-1).sr.get(j).id).name+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).ar.attendanceRate1+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).p.performance1+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).sr.submissionRate1+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).ar.attendanceRate2+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).p.performance2+"</td><td>"
							+map.get(cg.eg.get(i-1).sr.get(j).id).sr.submissionRate2+"</td></tr>\n");
					count++;
				}
				if(amari != 0 && pd.sData.numberOfMenber%2==1 && i > s){m--; amari--;}		//追加した分を引く
				if(amari != 0 && pd.sData.numberOfMenber%2==0){m--; amari--;}
				sb.append("		</table><br>\n	</div>\n");
				calcGroupEvaluation(i);			//グループごとの能力を計算
			}
			calcGroupEvalVar(pd.sData.numberOfGroup);					//分散を計算
			//フッター部分を生成
			sb.append("</div>\n</body>\n</html>");

			//編成日時を保存
			Calendar cal1 = Calendar.getInstance();
			int year = cal1.get(Calendar.YEAR);
		    int month = cal1.get(Calendar.MONTH) + 1;
		    int day = cal1.get(Calendar.DATE);

			gr.date =(year+"年"+month+"月"+day+"日");

			//プロジェクトデータに編成結果を追加
			pd.gr.add(gr);

			try {								//スタディを作成したとき
				if(directoryAddress!=null){
					//ファイルを保存するためのディレクトリを作成する
					File newfile = new File(directoryAddress+"\\"+projectFile+".xml"+gr.date+pd.gr.size());
					newfile.mkdir();
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(directoryAddress+"\\"+projectFile+".xml"+gr.date+pd.gr.size(), "index.html")),"Unicode"));
					bw.write(sb.toString());
					bw.close();

				}else{							//スタディを読み込んだとき
					File newfile = new File(projectFile+gr.date+pd.gr.size());
					newfile.mkdir();
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(projectFile+gr.date+pd.gr.size(), "index.html")),"Unicode"));
					bw.write(sb.toString());
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			//編成結果のHTMLファイルをブラウザで表示
			if(directoryAddress!=null){
				String str = "explorer "+directoryAddress+"\\"+projectFile+".xml"+gr.date+pd.gr.size()+"\\index.html";
				Runtime rt = Runtime.getRuntime();
				try{
					Process pr = rt.exec(str);
				}catch(Exception ee){
					ee.printStackTrace();;
				}

				//新たなgrを生成
				gr = new GroupingResult();
				//編成したタイミングでデータを保存
				singleton.saveProjectData(directoryAddress);
			}else{
				String str = "explorer "+projectFile+gr.date+pd.gr.size()+"\\index.html";
				Runtime rt = Runtime.getRuntime();
				try{
					Process pr = rt.exec(str);
				}catch(Exception ee){
					ee.printStackTrace();;
				}

				//新たなgrを生成
				gr = new GroupingResult();
				//編成したタイミングでデータを保存
				singleton.saveProjectData(projectFile);
			}


			return true;
		}catch(Exception e4){
			e4.printStackTrace();
			return false;
		}
	}

	//編成時の設定を確認する画面を生成する
	public void createSettingDataHTML(){
		StringBuilder wsb = new StringBuilder();							//文字列を格納する
		StringBuilder dsb = new StringBuilder();
		try{
			wsb.append("<html>\n<head><title>設定内容</title>\n");
			//style
			wsb.append("<style>\n*{margin:0;}body{font-family:'メイリオ','ヒラギノ角ゴ Pro W3','Meiryo', 'Osaka';font-size:12pt;line-height:1.5em;margin: 5px;padding: 5px;}.WeightArea{margin:1em;width:1200px;border:1px solid #aaa;padding:1px;}.DivideArea{margin:1em;width:300px;border:1px solid #aaa;padding:1px;}.GroupTable1{width:1200px;}.GroupTable2{width:1100px;}.GroupTable3{width:1000px;}.GroupTable4{width:300px;}.GroupTable th, .GroupDetailTable th{background-color:#eee;}.GroupTable1 tr:nth-child(2n+1),.GroupTable2 tr:nth-child(2n+1),.GroupTable3 tr:nth-child(2n+1),.GroupTable4 tr:nth-child(2n+1){background-color:#eef;}.GroupTable1 tr:nth-child(2n),.GroupTable2 tr:nth-child(2n),.GroupTable3 tr:nth-child(2n),.GroupTable4 tr:nth-child(2n){background-color:#f6f6ff;}.LinkButton{border:1px solid #aaa;background-color:#eee;color:#000;width:100px;height:auto;margin:1em;padding:1px;text-align:center;}\n</style>\n");
			wsb.append("</head>\n<body>\n<h1>重みの設定</h1>\n<div class=\"WeightArea\">\n	<h2>性格検査の結果</h2>\n	<table table class=\"GroupTable1\">\n		<tr><th>知識エリア</th><th>FFS(凝縮)</th><th>FFS(受容)</th><th>FFS(拡散)</th><th>FFS(保全)</th><th>BigFive(開放)</th><th>BigFive(誠実)</th><th>BigFive(外向)</th><th>BigFive(調和)</th><th>BigFive(神経)</th></tr>");
			wsb.append("		<tr><td>"+pd.w.books1.get(0).getKArea01()+"</td><td>"+pd.w.books1.get(0).getFfsResult1()+"</td><td>"+pd.w.books1.get(0).getFfsResult2()+"</td><td>"+pd.w.books1.get(0).getFfsResult3()+"</td><td>"+pd.w.books1.get(0).getFfsResult4()+"</td><td>"+pd.w.books1.get(0).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(0).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(0).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(0).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(0).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(1).getKArea01()+"</td><td>"+pd.w.books1.get(1).getFfsResult1()+"</td><td>"+pd.w.books1.get(1).getFfsResult2()+"</td><td>"+pd.w.books1.get(1).getFfsResult3()+"</td><td>"+pd.w.books1.get(1).getFfsResult4()+"</td><td>"+pd.w.books1.get(1).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(1).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(1).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(1).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(1).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(2).getKArea01()+"</td><td>"+pd.w.books1.get(2).getFfsResult1()+"</td><td>"+pd.w.books1.get(2).getFfsResult2()+"</td><td>"+pd.w.books1.get(2).getFfsResult3()+"</td><td>"+pd.w.books1.get(2).getFfsResult4()+"</td><td>"+pd.w.books1.get(2).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(2).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(2).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(4).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(2).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(3).getKArea01()+"</td><td>"+pd.w.books1.get(3).getFfsResult1()+"</td><td>"+pd.w.books1.get(3).getFfsResult2()+"</td><td>"+pd.w.books1.get(3).getFfsResult3()+"</td><td>"+pd.w.books1.get(3).getFfsResult4()+"</td><td>"+pd.w.books1.get(3).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(3).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(3).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(3).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(3).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(4).getKArea01()+"</td><td>"+pd.w.books1.get(4).getFfsResult1()+"</td><td>"+pd.w.books1.get(4).getFfsResult2()+"</td><td>"+pd.w.books1.get(4).getFfsResult3()+"</td><td>"+pd.w.books1.get(4).getFfsResult4()+"</td><td>"+pd.w.books1.get(4).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(4).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(4).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(4).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(4).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(5).getKArea01()+"</td><td>"+pd.w.books1.get(5).getFfsResult1()+"</td><td>"+pd.w.books1.get(5).getFfsResult2()+"</td><td>"+pd.w.books1.get(5).getFfsResult3()+"</td><td>"+pd.w.books1.get(5).getFfsResult4()+"</td><td>"+pd.w.books1.get(5).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(5).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(5).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(5).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(5).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(6).getKArea01()+"</td><td>"+pd.w.books1.get(6).getFfsResult1()+"</td><td>"+pd.w.books1.get(6).getFfsResult2()+"</td><td>"+pd.w.books1.get(6).getFfsResult3()+"</td><td>"+pd.w.books1.get(6).getFfsResult4()+"</td><td>"+pd.w.books1.get(6).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(6).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(6).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(6).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(6).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(7).getKArea01()+"</td><td>"+pd.w.books1.get(7).getFfsResult1()+"</td><td>"+pd.w.books1.get(7).getFfsResult2()+"</td><td>"+pd.w.books1.get(7).getFfsResult3()+"</td><td>"+pd.w.books1.get(7).getFfsResult4()+"</td><td>"+pd.w.books1.get(7).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(7).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(7).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(7).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(7).getBigFiveResult5()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books1.get(8).getKArea01()+"</td><td>"+pd.w.books1.get(8).getFfsResult1()+"</td><td>"+pd.w.books1.get(8).getFfsResult2()+"</td><td>"+pd.w.books1.get(8).getFfsResult3()+"</td><td>"+pd.w.books1.get(8).getFfsResult4()+"</td><td>"+pd.w.books1.get(8).getBigFiveResult1()+"</td><td>"+pd.w.books1.get(8).getBigFiveResult2()+"</td><td>"+pd.w.books1.get(8).getBigFiveResult3()+"</td><td>"+pd.w.books1.get(8).getBigFiveResult4()+"</td><td>"+pd.w.books1.get(8).getBigFiveResult5()+"</td></tr>\n"
					+ "	<table>\n	<br>\n	<h2>PMテストの点数</h2>\n	<table table class=\"GroupTable2\">\n		<tr><th>知識エリア</th><th>統合</th><th>リスク</th><th>スケジュール</th><th>スコープ</th><th>品質</th><th>人的資源</th><th>コスト</th><th>調達</th><th>コミュニケーション</th><th>合計</th></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(0).getKArea02()+"</td><td>"+pd.w.books2.get(0).getIntegration()+"</td><td>"+pd.w.books2.get(0).getRisk()+"</td><td>"+pd.w.books2.get(0).getSchedule()+"</td><td>"+pd.w.books2.get(0).getScope()+"</td><td>"+pd.w.books2.get(0).getQuality()+"</td><td>"+pd.w.books2.get(0).getHumanResource()+"</td><td>"+pd.w.books2.get(0).getCost()+"</td><td>"+pd.w.books2.get(0).getProcurement()+"</td><td>"+pd.w.books2.get(0).getCommunication()+"</td><td>"+pd.w.books2.get(0).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(1).getKArea02()+"</td><td>"+pd.w.books2.get(1).getIntegration()+"</td><td>"+pd.w.books2.get(1).getRisk()+"</td><td>"+pd.w.books2.get(1).getSchedule()+"</td><td>"+pd.w.books2.get(1).getScope()+"</td><td>"+pd.w.books2.get(1).getQuality()+"</td><td>"+pd.w.books2.get(1).getHumanResource()+"</td><td>"+pd.w.books2.get(1).getCost()+"</td><td>"+pd.w.books2.get(1).getProcurement()+"</td><td>"+pd.w.books2.get(1).getCommunication()+"</td><td>"+pd.w.books2.get(1).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(2).getKArea02()+"</td><td>"+pd.w.books2.get(2).getIntegration()+"</td><td>"+pd.w.books2.get(2).getRisk()+"</td><td>"+pd.w.books2.get(2).getSchedule()+"</td><td>"+pd.w.books2.get(2).getScope()+"</td><td>"+pd.w.books2.get(2).getQuality()+"</td><td>"+pd.w.books2.get(2).getHumanResource()+"</td><td>"+pd.w.books2.get(2).getCost()+"</td><td>"+pd.w.books2.get(2).getProcurement()+"</td><td>"+pd.w.books2.get(2).getCommunication()+"</td><td>"+pd.w.books2.get(2).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(3).getKArea02()+"</td><td>"+pd.w.books2.get(3).getIntegration()+"</td><td>"+pd.w.books2.get(3).getRisk()+"</td><td>"+pd.w.books2.get(3).getSchedule()+"</td><td>"+pd.w.books2.get(3).getScope()+"</td><td>"+pd.w.books2.get(3).getQuality()+"</td><td>"+pd.w.books2.get(3).getHumanResource()+"</td><td>"+pd.w.books2.get(3).getCost()+"</td><td>"+pd.w.books2.get(3).getProcurement()+"</td><td>"+pd.w.books2.get(3).getCommunication()+"</td><td>"+pd.w.books2.get(3).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(4).getKArea02()+"</td><td>"+pd.w.books2.get(4).getIntegration()+"</td><td>"+pd.w.books2.get(4).getRisk()+"</td><td>"+pd.w.books2.get(4).getSchedule()+"</td><td>"+pd.w.books2.get(4).getScope()+"</td><td>"+pd.w.books2.get(4).getQuality()+"</td><td>"+pd.w.books2.get(4).getHumanResource()+"</td><td>"+pd.w.books2.get(4).getCost()+"</td><td>"+pd.w.books2.get(4).getProcurement()+"</td><td>"+pd.w.books2.get(4).getCommunication()+"</td><td>"+pd.w.books2.get(4).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(5).getKArea02()+"</td><td>"+pd.w.books2.get(5).getIntegration()+"</td><td>"+pd.w.books2.get(5).getRisk()+"</td><td>"+pd.w.books2.get(5).getSchedule()+"</td><td>"+pd.w.books2.get(5).getScope()+"</td><td>"+pd.w.books2.get(5).getQuality()+"</td><td>"+pd.w.books2.get(5).getHumanResource()+"</td><td>"+pd.w.books2.get(5).getCost()+"</td><td>"+pd.w.books2.get(5).getProcurement()+"</td><td>"+pd.w.books2.get(5).getCommunication()+"</td><td>"+pd.w.books2.get(5).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(6).getKArea02()+"</td><td>"+pd.w.books2.get(6).getIntegration()+"</td><td>"+pd.w.books2.get(6).getRisk()+"</td><td>"+pd.w.books2.get(6).getSchedule()+"</td><td>"+pd.w.books2.get(6).getScope()+"</td><td>"+pd.w.books2.get(6).getQuality()+"</td><td>"+pd.w.books2.get(6).getHumanResource()+"</td><td>"+pd.w.books2.get(6).getCost()+"</td><td>"+pd.w.books2.get(6).getProcurement()+"</td><td>"+pd.w.books2.get(6).getCommunication()+"</td><td>"+pd.w.books2.get(6).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(7).getKArea02()+"</td><td>"+pd.w.books2.get(7).getIntegration()+"</td><td>"+pd.w.books2.get(7).getRisk()+"</td><td>"+pd.w.books2.get(7).getSchedule()+"</td><td>"+pd.w.books2.get(7).getScope()+"</td><td>"+pd.w.books2.get(7).getQuality()+"</td><td>"+pd.w.books2.get(7).getHumanResource()+"</td><td>"+pd.w.books2.get(7).getCost()+"</td><td>"+pd.w.books2.get(7).getProcurement()+"</td><td>"+pd.w.books2.get(7).getCommunication()+"</td><td>"+pd.w.books2.get(7).getTotalScore()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books2.get(8).getKArea02()+"</td><td>"+pd.w.books2.get(8).getIntegration()+"</td><td>"+pd.w.books2.get(8).getRisk()+"</td><td>"+pd.w.books2.get(8).getSchedule()+"</td><td>"+pd.w.books2.get(8).getScope()+"</td><td>"+pd.w.books2.get(8).getQuality()+"</td><td>"+pd.w.books2.get(8).getHumanResource()+"</td><td>"+pd.w.books2.get(8).getCost()+"</td><td>"+pd.w.books2.get(8).getProcurement()+"</td><td>"+pd.w.books2.get(8).getCommunication()+"</td><td>"+pd.w.books2.get(8).getTotalScore()+"</td></tr>\n");
			wsb.append("	</table>\n	<br>\n	<h2>授業に関するデータ</h2>\n	<table table class=\"GroupTable3\">\n		<tr><th>知識エリア</th><th>授業1の出席率</th><th>授業1の成績</th><th>授業1の課題提出率</th><th>授業2の出席率</th><th>授業2の成績</th><th>授業2の課題提出率</th></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(0).getKArea03()+"</td><td>"+pd.w.books3.get(0).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(0).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(0).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(0).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(0).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(0).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(1).getKArea03()+"</td><td>"+pd.w.books3.get(1).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(1).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(1).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(1).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(1).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(1).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(2).getKArea03()+"</td><td>"+pd.w.books3.get(2).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(2).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(2).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(2).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(2).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(2).getSubmissionRateOfClass02()+"<td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(3).getKArea03()+"</td><td>"+pd.w.books3.get(3).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(3).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(3).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(3).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(3).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(3).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(4).getKArea03()+"</td><td>"+pd.w.books3.get(4).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(4).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(4).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(4).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(4).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(4).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(5).getKArea03()+"</td><td>"+pd.w.books3.get(5).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(5).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(5).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(5).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(5).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(5).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(6).getKArea03()+"</td><td>"+pd.w.books3.get(6).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(6).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(6).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(6).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(6).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(6).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(7).getKArea03()+"</td><td>"+pd.w.books3.get(7).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(7).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(7).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(7).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(7).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(7).getSubmissionRateOfClass02()+"</td></tr>\n"
					+ "		<tr><td>"+pd.w.books3.get(8).getKArea03()+"</td><td>"+pd.w.books3.get(8).getAttendanceOfClass01()+"</td><td>"+pd.w.books3.get(8).getPerformanceOfClass01()+"</td><td>"+pd.w.books3.get(8).getSubmissionRateOfClass01()+"</td><td>"+pd.w.books3.get(8).getAttendanceOfClass02()+"</td><td>"+pd.w.books3.get(8).getPerformanceOfClass02()+"</td><td>"+pd.w.books3.get(8).getSubmissionRateOfClass02()+"</td></tr>\n");
			wsb.append("	</table>\n</div>\n<div class=\"LinkButton\"><a href=\"index.html\">戻る</a></div>\n<br></body>\n</html>");

			dsb.append("<html>\n<head><title>設定内容</title>\n");
			//style
			dsb.append("<style>\n*{margin:0;}body{font-family:'メイリオ','ヒラギノ角ゴ Pro W3','Meiryo', 'Osaka';font-size:12pt;line-height:1.5em;margin: 5px;padding: 5px;}.WeightArea{margin:1em;width:1200px;border:1px solid #aaa;padding:1px;}.DivideArea{margin:1em;width:300px;border:1px solid #aaa;padding:1px;}.GroupTable1{width:1200px;}.GroupTable2{width:1100px;}.GroupTable3{width:1000px;}.GroupTable4{width:300px;}.GroupTable th, .GroupDetailTable th{background-color:#eee;}.GroupTable1 tr:nth-child(2n+1),.GroupTable2 tr:nth-child(2n+1),.GroupTable3 tr:nth-child(2n+1),.GroupTable4 tr:nth-child(2n+1){background-color:#eef;}.GroupTable1 tr:nth-child(2n),.GroupTable2 tr:nth-child(2n),.GroupTable3 tr:nth-child(2n),.GroupTable4 tr:nth-child(2n){background-color:#f6f6ff;}.LinkButton{border:1px solid #aaa;background-color:#eee;color:#000;width:100px;height:auto;margin:1em;padding:1px;text-align:center;}\n</style>\n");
			dsb.append("</head>\n<body>\n<h1>役割の分割設定</h1></br>\n<h2>役割の数:"+ pd.dr.divideRole +"</h2>\n<div class=\"DivideArea\">\n	<table table class=\"GroupTable4\">\n		<tr><th>知識エリア</th><th>役割</th></tr>");
			dsb.append("		<tr><td>統合管理</td><td>"+pd.dr.integrationGroup+"</td></tr>\n"
					+ "		<tr><td>リスク管理</td><td>"+pd.dr.riskGroup+"</td></tr>\n"
					+ "		<tr><td>スコープ管理</td><td>"+pd.dr.scopeGroup+"</td></tr>\n"
					+ "		<tr><td>品質管理</td><td>"+pd.dr.qualityGroup+"</td></tr>\n"
					+ "		<tr><td>スケジュール管理</td><td>"+pd.dr.scheduleGroup+"</td></tr>\n"
					+ "		<tr><td>コスト管理</td><td>"+pd.dr.costGroup+"</td></tr>\n"
					+ "		<tr><td>コミュニケーション管理</td><td>"+pd.dr.communicationGroup+"</td></tr>\n"
					+ "		<tr><td>調達管理</td><td>"+pd.dr.procurementGroup+"</td></tr>\n"
					+ "		<tr><td>人的資源管理</td><td>"+pd.dr.humanResourceGroup+"</td></tr>\n");
			dsb.append("	</table>\n</div>\n<div class=\"LinkButton\"><a href=\"index.html\">戻る</a></div>\n</body>\n</html>");
		}catch(Exception ei){
			ei.printStackTrace();
		}

		try {
			if(directoryAddress!=null){
				// ファイルの書き込み
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(directoryAddress+"\\"+projectFile+".xml"+pd.gr.get(pd.gr.size()-1).date+pd.gr.size(), "weight.html")),"Unicode"));
				bw.write(wsb.toString());
				BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(directoryAddress+"\\"+projectFile+".xml"+pd.gr.get(pd.gr.size()-1).date+pd.gr.size(), "divide.html")),"Unicode"));
				bw1.write(dsb.toString());
				bw.close();
				bw1.close();
			}else{
				// ファイルの書き込み
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(projectFile+pd.gr.get(pd.gr.size()-1).date+pd.gr.size(), "weight.html")),"Unicode"));
				bw.write(wsb.toString());
				BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(projectFile+pd.gr.get(pd.gr.size()-1).date+pd.gr.size(), "divide.html")),"Unicode"));
				bw1.write(dsb.toString());
				bw.close();
				bw1.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//個人の評価値を計算する	引数：学籍番号
	public void calcEvaluation(String s){
		int g = pd.sData.numberOfGroup;									//グループ数
		int m = pd.sData.numberOfMenber;									//メンバーの数
		if(m == 0)m = pd.studentData.size()/g;					//グループ数を指定したときのメンバ数を計算
		if(g == 0)g = pd.studentData.size()/m;					//メンバ数を指定したときのグループ数を計算
		StudentResult sr = new StudentResult();
		PMRoleResult pr = new PMRoleResult();
		EachGroupResult eg = new EachGroupResult();
		double totalEval = 0;				//個人の評価値
		double[] knowledgeEval = new double[9];			//知識エリアごとの評価値
		String id = s;
		for(int j = 0; j < knowledgeEval.length; j++){		//知識エリアの数だけ繰り返し
			double eval = 0;
			//FFSを選択したとき
			if(pd.sData.persTest.equals("FFS")){
				if(map.get(s).pt.ffsResult==0)eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult1()))*2;
				else eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult1()));

				if(map.get(s).pt.ffsResult==1)eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult2()))*2;
				else eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult2()));

				if(map.get(s).pt.ffsResult==2)eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult3()))*2;
				else eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult3()));

				if(map.get(s).pt.ffsResult==3)eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult4()))*2;
				else eval += (Integer.parseInt(pd.w.books1.get(j).getFfsResult4()));
			}else{
				eval += ((map.get(s).pt.bigFiveResult1)/14)*Integer.parseInt(pd.w.books1.get(j).getBigFiveResult1());
				eval += ((map.get(s).pt.bigFiveResult2)/14)*Integer.parseInt(pd.w.books1.get(j).getBigFiveResult2());
				eval += ((map.get(s).pt.bigFiveResult3)/14)*Integer.parseInt(pd.w.books1.get(j).getBigFiveResult3());
				eval += ((map.get(s).pt.bigFiveResult4)/14)*Integer.parseInt(pd.w.books1.get(j).getBigFiveResult4());
				eval += ((map.get(s).pt.bigFiveResult5)/14)*Integer.parseInt(pd.w.books1.get(j).getBigFiveResult5());
			}
			eval += ((map.get(s).pmTestData.integrationScore)/10)*Integer.parseInt(pd.w.books2.get(j).getIntegration());
			eval += ((map.get(s).pmTestData.riskScore)/10)*Integer.parseInt(pd.w.books2.get(j).getRisk());
			eval += ((map.get(s).pmTestData.scopeScore)/10)*Integer.parseInt(pd.w.books2.get(j).getScope());
			eval += ((map.get(s).pmTestData.qualityScore)/10)*Integer.parseInt(pd.w.books2.get(j).getQuality());
			eval += ((map.get(s).pmTestData.scheduleScore)/10)*Integer.parseInt(pd.w.books2.get(j).getSchedule());
			eval += ((map.get(s).pmTestData.costScore)/10)*Integer.parseInt(pd.w.books2.get(j).getCost());
			eval += ((map.get(s).pmTestData.communicationScore)/10)*Integer.parseInt(pd.w.books2.get(j).getCommunication());
			eval += ((map.get(s).pmTestData.procurementScore)/10)*Integer.parseInt(pd.w.books2.get(j).getProcurement());
			eval += ((map.get(s).pmTestData.humanResourceScore)/10)*Integer.parseInt(pd.w.books2.get(j).getHumanResource());
			eval += ((map.get(s).pmTestData.totalScore)/90)*Integer.parseInt(pd.w.books2.get(j).getHumanResource());

			eval += ((map.get(s).ar.attendanceRate1)/100)*Integer.parseInt(pd.w.books3.get(j).getAttendanceOfClass01());
			eval += ((map.get(s).p.performance1)/100)*Integer.parseInt(pd.w.books3.get(j).getPerformanceOfClass01());
			eval += ((map.get(s).sr.submissionRate1)/100)*Integer.parseInt(pd.w.books3.get(j).getSubmissionRateOfClass01());
			eval += ((map.get(s).ar.attendanceRate2)/100)*Integer.parseInt(pd.w.books3.get(j).getAttendanceOfClass02());
			eval += ((map.get(s).p.performance2)/100)*Integer.parseInt(pd.w.books3.get(j).getPerformanceOfClass02());
			eval += ((map.get(s).sr.submissionRate2)/100)*Integer.parseInt(pd.w.books3.get(j).getSubmissionRateOfClass02());

			knowledgeEval[j] = eval;
			totalEval += eval;
		}
		//計算結果をモデルに格納
		sr.id = id;
		sr.totalEval = totalEval;
		pr.integrationEvaluation =  knowledgeEval[0];
		pr.riskEvaluation =  knowledgeEval[1];
		pr.scopeEvaluation =  knowledgeEval[2];
		pr.qualityEvaluation =  knowledgeEval[3];
		pr.scheduleEvaluation =  knowledgeEval[4];
		pr.costEvaluation =  knowledgeEval[5];
		pr.communicationEvaluation =  knowledgeEval[6];
		pr.procurementEvaluation =  knowledgeEval[7];
		pr.humanResourceEvaluation =  knowledgeEval[8];
		sr.pmResult = pr;

		cg.personalEval.add(sr);
	}

	public void setRole(int g, int m1){			//役割の割り当て    引数：グループ番号、メンバ数
		int c = 0;		//カウント用変数,割る数
		int[][] roleEval = new int[cg.eg.get(g).sr.size()][5];		//[メンバ][役割]		役割ごとの適性
		for(int i = 0; i < roleEval.length; i++){		//メンバの数だけ繰り返し
			for(int j = 0; j < roleEval[0].length; j++){		//役割の数だけ繰り返し
				if(pd.dr.integrationGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.integrationEvaluation; c++;}	//統合管理で「1」を設定していたら統合管理の評価値を役割1の適性に加算.以下同じ
				if(pd.dr.riskGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.riskEvaluation; c++;}
				if(pd.dr.scopeGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.scopeEvaluation; c++;}
				if(pd.dr.qualityGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.qualityEvaluation; c++;}
				if(pd.dr.scheduleGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.scheduleEvaluation; c++;}
				if(pd.dr.costGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.costEvaluation; c++;}
				if(pd.dr.communicationGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.communicationEvaluation; c++;}
				if(pd.dr.procurementGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.procurementEvaluation; c++;}
				if(pd.dr.humanResourceGroup==j+1){roleEval[i][j]+=cg.eg.get(g).sr.get(i).pmResult.humanResourceEvaluation; c++;}
				if(c!=0)roleEval[i][j] = roleEval[i][j]/c;							//役割が複数の知識エリアの時は知識エリアの数で割る
				c = 0;
			}
		}
		int[] role = new int[pd.dr.divideRole];
		double maxValue = 0;
		for(int k = 0; k < roleEval.length; k++){
			if(maxValue < roleEval[k][0]){maxValue = roleEval[k][0];role[0] = k;}
		}
		maxValue =0;
		for(int k = 0; k < roleEval.length; k++){
			if(role[0]!=k){
				if(maxValue < roleEval[k][1]){maxValue = roleEval[k][1];role[1] = k;}
			}
		}
		maxValue = 0;
		for(int k = 0; k < roleEval.length; k++){
			if(role[0]!=k && role[1]!=k){
				if(maxValue < roleEval[k][2]){maxValue = roleEval[k][2];role[2] = k;}
			}
		}
		maxValue = 0;
		if(pd.dr.divideRole==4){
			for(int k = 0; k < roleEval.length; k++){
				if(role[0]!=k && role[1]!=k && role[2]!= k){
					if(maxValue < roleEval[k][3]){maxValue = roleEval[k][3];role[3] = k;}
				}
			}
		}
		if(pd.dr.divideRole==5){
			for(int k = 0; k < roleEval.length; k++){
				if(role[0]!=k && role[1]!=k && role[2]!= k){
					if(maxValue < roleEval[k][4]){maxValue = roleEval[k][4];role[4] = k;}
				}
			}
		}
		if(m1 < role.length){				//役割よりメンバの数が少ないとき
			for(int m = 0; m < m1; m++){		//割り当て
				map.get(cg.eg.get(g).sr.get(role[m]).id).role = String.valueOf(m);
			}
		}else if(role.length <= m1){		//メンバの数より役割の方が少ないとき or それぞれが同じとき
			for(int m = 0; m < role.length; m++){		//割り当て
				map.get(cg.eg.get(g).sr.get(role[m]).id).role = String.valueOf(m);
			}
		}
		for(int i = 0; i < pd.sData.numberOfGroup; i++){
			gr.eg.add(cg.eg.get(i));
		}
	}

	public void createGroup(){				//編成案の生成
		cg = new CreateGroupData();
		EachGroupResult eg = new EachGroupResult();
		int count = 0;					//リストを参照するときの変数
		int c = -1;						//dの値を増やしたり減らしたりするための変数
		int d = 0;						//割り当てるグループを表す変数   1,2,3,3,2,1,・・・
		int amari = pd.studentData.size()%pd.sData.numberOfGroup;
		boolean f = true;
		int s = amari;
		HashMap<Double,StudentData> map = new HashMap<Double,StudentData>();		//キー：個人の能力  データ：学生の情報
		HashMap<String,StudentResult> map1 = new HashMap<String,StudentResult>();
		double[] eval = new double[pd.studentData.size()];		//個人の能力を格納する配列

		for(int i = 0; i < pd.studentData.size(); i++){				//学生の評価値を計算
			calcEvaluation(pd.studentData.get(i).id);
			eval[i] = cg.personalEval.get(i).totalEval;
		}
		for(int x = 0; x < pd.studentData.size()-1; x++){						//totalEvalが等しい学生がいるとき
			for(int y = x+1; y < pd.studentData.size(); y++){
				if(eval[y]==eval[x]){
					eval[y]= eval[y]+0.001;
				}
			}
		}
		for(int z = 0; z < pd.studentData.size(); z++){							//個人の評価値をキーにして学生情報が取得できるようにする
			map.put(eval[z], pd.studentData.get(z));							//idから評価値を取得できるようにする
			map1.put(pd.studentData.get(z).id, cg.personalEval.get(z));
		}
		for(int j = 0; j < pd.studentData.size()-1; j++){				//個人の能力を降順にソート
			for(int k = j+1; k < pd.studentData.size(); k++){
				if(eval[k]>eval[j]){
					double temp = eval[k];
					eval[k] = eval[j];
					eval[j] = temp;
				}
			}
		}
		for(int l = 0; l < pd.studentData.size(); l++){					//cgのstudentListに個人の評価値が降順になるように学生情報を格納する
			cg.studentList.add(map.get(eval[l]));
		}
		for(int a = 0; a < pd.sData.numberOfMenber; a++){				//メンバの数だけ繰り返し       EachGroupにデータを渡す
			for(int b = 0; b < pd.sData.numberOfGroup; b++){			//グループの数だけ繰り返し
				if(a==0){
					eg = new EachGroupResult();
					cg.eg.add(eg);
				}
				StudentResult sr = new StudentResult();
				sr.id = cg.studentList.get(count).id;
				sr = map1.get(cg.studentList.get(count).id);
				//System.out.println(cg.studentList.get(count).name+"の評価値："+sr.totalEval);
				cg.eg.get(d).sr.add(sr);
				count++;

				if(a == pd.sData.numberOfMenber-1 && amari != 0 && pd.sData.numberOfMenber%2==1 && b > s){		//割り切れないときグループに一人メンバを追加
					sr = new StudentResult();
					sr.id = cg.studentList.get(count).id;
					sr = map1.get(cg.studentList.get(count).id);
					cg.eg.get(d).sr.add(sr);
					count++;
					amari--;
				}
				if(a == pd.sData.numberOfMenber-1 && amari != 0 && pd.sData.numberOfMenber%2==0 && b == pd.sData.numberOfGroup-1){		//割り切れないときグループに一人メンバを追加
					for(int i = 0; i < s; i++){
						sr = new StudentResult();
						sr.id = cg.studentList.get(count).id;
						sr = map1.get(cg.studentList.get(count).id);
						cg.eg.get(i).sr.add(sr);
						count++;
						amari--;
					}
				}
				if(d==0){
					if(f == false){				//グループの数の最小値に来たとき、一回目
						d = d + 1*c*(-1);
						f = true;
					}else if(f == true){			//グループの数の最小値に来たとき、二回目
						c = (-1)*c;
						f = false;
					}
				}else if(d==pd.sData.numberOfGroup-1){
					if(f == false){				//グループの数の最大値に来たとき、一回目
						d = d + 1*c*(-1);
						f = true;
					}else if(f == true){			//グループの数の最大値に来たとき、二回目
						c = (-1)*c;
						f = false;
					}
				}
				d = d + 1*c;
			}
		}
	}

	//グループの評価値を計算する	引数：グループ番号
	public void calcGroupEvaluation(int i){
		double groupEval = 0;
		for(int j = 0; j < gr.eg.get(i-1).sr.size(); j++){		//グループのメンバの数だけ繰り返し
			groupEval += gr.eg.get(i-1).sr.get(j).totalEval;
		}
		cg.eg.get(i-1).groupPerformance = groupEval;
		gr.eg.get(i-1).groupPerformance = groupEval;
		//System.out.println("group" + i + "の評価値："+ groupEval);
	}

	//グループの能力のばらつきを計算する	引数：グループ数
	public void calcGroupEvalVar(int i){
		double average = 0;
		double variance = 0;
		//グループの能力の平均を求める
		for(int j = 0; j < i; j++){
			average += gr.eg.get(j).groupPerformance;
		}
		average = average/i;
		//System.out.println("平均："+average);
		//分散を計算
		for(int k = 0; k < i; k++){
			variance += (gr.eg.get(k).groupPerformance - average)*(gr.eg.get(k).groupPerformance - average);
		}
		variance = variance/i;
		//System.out.println("分散："+variance);
		gr.groupEvalVariance = variance;
	}

	public String getRole(String i){		//引数：役割の番号      戻り値：役割に含まれる知識エリアの文字列(表示用)
		String[] role = {"", "", "", "", "", ""};		//returnする配列,各役割に設定されている知識エリアを格納する
		String[][] group = {{String.valueOf(pd.dr.integrationGroup), "統合管理"}, {String.valueOf(pd.dr.riskGroup), "リスク管理"},
							{String.valueOf(pd.dr.scopeGroup), "スコープ管理"}, {String.valueOf(pd.dr.qualityGroup), "品質管理"},
							{String.valueOf(pd.dr.scheduleGroup), "スケジュール管理"}, {String.valueOf(pd.dr.costGroup), "コスト管理"},
							{String.valueOf(pd.dr.communicationGroup), "コミュニケーション管理"}, {String.valueOf(pd.dr.procurementGroup), "調達管理"},
							{String.valueOf(pd.dr.humanResourceGroup), "人的資源管理"}};

		for(int g = 0; g < group.length; g++){
			if(Integer.parseInt(group[g][0])==0){   }
			else if(Integer.parseInt(group[g][0])==1){
				role[0] += " "+group[g][1];
					}
			else if(Integer.parseInt(group[g][0])==2){
				role[1] += " "+group[g][1];
			}
			else if(Integer.parseInt(group[g][0])==3){
				role[2] += " "+group[g][1];
			}
			else if(Integer.parseInt(group[g][0])==4){
				role[3] += " "+group[g][1];
			}
			else if(Integer.parseInt(group[g][0])==5){
				role[4] += " "+group[g][1];
			}
		}
		if(i.equals(""))return "";
		return role[Integer.parseInt(i)];
	}
}