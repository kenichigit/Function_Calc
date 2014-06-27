import java.util.Arrays;

import javax.swing.JTextField;
/**表示画面
 * 数式の入力、結果の出力を行う
 * */

public class CalcTextField extends JTextField{
	
	String tmp="";		//数値を一時的に入れる
	String[] RPN = new String[100];		//ポ-ランド記法を作る配列
	boolean[] flag = new boolean[100];	//数値フラグ 
	String[] op = new String[100];		//一時的に演算子,「()」を格納する配列
	int i = 0,j = 0;
	String func;

	
	public CalcTextField() {
		super("");
		// TODO 自動生成されたコンストラクタ-・スタブ
	}
	
	/**　表示画面に加える */
	public void addString(String string) {
		//表示している数が0の状態での入力
		if("0".equals(getText())){
			if(string == ".")this.setText("0.");
			else this.setText(string);
		}
		//桁を増やして並べて表示
		else{
			this.setText(this.getText() + string);		
		}
	}
	/** テキストフィールドをリセットする */
	public void clear(){
		/*表示する数をリセット*/
		this.setText("");
		/*配列を初期化*/
		Arrays.fill(RPN,null);
		Arrays.fill(flag,false);
		/*一時変数を初期化*/
		i=j=0;
		tmp="";
	}
	
	/**数式を後置記法に変換する*/
	public void RPN_transform(String operator){
		this.getText();
		
		if(Calc.last_click =='O'||Calc.last_click =='='){   //☆演算記号が押される
			if(tmp != ""){
			RPN[i] = tmp;//数字を入れる
			flag[i] = true;
			i++;
			}
			
			if(Calc.last_click =='O'){
			op[j] = operator;
			j++;
			}
			
			if(j>=2){	
				if((op[j-2]=="×"||op[j-2]=="÷") && (op[j-1]=="＋"||op[j-1]=="－")){//op[]の中で、「×」か「/」が一個前に入っているときに「＋」か「ー」が入る。
		        //演算子の優先順位で入れ替え
		    		RPN[i] = op[j-2];
					flag[i] = false;
		    		i++;
		    		op[j-2] = op[j-1];
		    		op[j-2] = null;
		    	}
			}
			
			if(operator == ")"){                //「)」が入力されたとき
				for(;;){
					if(op[j] == "("){//「(」が出てくるまでRPNの後ろにopの先頭を入れる
						break;
					}else{
						RPN[i] = op[j-1];
						flag[i] = false;
						i++;
						j--;
						
						if(RPN[i-1]=="(" || RPN[i-1]==")"){
							i--;
						}
					}
				}
			}
			
			if(operator.equals("=")){                      //「＝」が入ったらopに入ってる記号をRPNに入れる
				for(;;){
					RPN[i] = op[j-1];	
					flag[i] = false;
					i++;
					j--;
					if(j==0){                    //opが空になったら終了
						break;    
					}
				}
			}
		}//☆演算記号が押されたときの処理終了
	}
	
//	/**数式を後置記法に変換*/
//	public void RPN_transform(String operator){
//		
//		if(tmp != ""){
//			RPN[i] = tmp;//数字を入れる
//			flag[i] = true;
//			i++;
//		}
//		
//		//EqualButtonが押された際は演算子を格納しない
//		if(Calc.last_click =='O'){
//			op[j] = operator;
//			j++;
//		}
//		
//		if(j>=2){
//			//op[]の中で、「*」か「/」が一個前に入っているときに「+」か「-」が入る。
//			if((op[j-2]=="*"||op[j-2]=="/") && (op[j-1]=="+"||op[j-1]=="-")){
//				//演算子の優先順位で入れ替え
//	    		RPN[i] = op[j-2];
//				flag[i] = false;
//	    		i++;
//	    		op[j-2] = op[j-1];
//	    		op[j-2] = null;
//	    	}
//		}
//		
//		if(operator == ")"){	//「)」が入力されたとき
//			while(op[j] != "("){	//「(」が出てくるまでRPNの後ろにopの先頭を入れる
//				RPN[i] = op[j-1];
//				flag[i] = false;
//				i++;
//				j--;			
//				if(RPN[i-1]=="(" || RPN[i-1]==")"){
//					i--;
//				}
//			}
//		}
//			
//		if(operator.equals("=")){	//「＝」が入ったらopに入ってる記号をRPNに入れる
//			while(j!=0){	//opが空になったら終了
//				RPN[i] = op[j-1];	
//				flag[i] = false;
//				i++;
//				j--;
//			}
//		}
//	}
	
	
	
	/**後置記法の数式の演算を行う*/
	public  void operation(String[] RPN,boolean[] Flag){
		
		int len = 0;
		//数値,演算子の個数lenはnullが見つかるまでのRPNの個数
		for(int i=0;RPN[i]!=null;i++){
			len++;
		}
		
		/*############ e,πを数値に置き換える ##################*/			
		for(int i=0;i<len;i++){
			if(RPN[i].equals("e")){
				RPN[i]=""+Math.E;
			}
		}
		for(int i=0;i<len;i++){
			if(RPN[i].equals("π")){
				RPN[i]=""+Math.PI;
			}
		}
		
		/*############ 演算を行う ##################*/
		char op=0;	
		for(int i=0;Flag[1];){	//全ての計算が終わった時Flag[1]は演算子(false)になる
			if(Flag[i]&&Flag[i+1]&&(!Flag[i+2])){	//[数字,数字,演算子]の並びがあれば計算する
				op = RPN[i+2].charAt(0);	//RPN[i+2]をcharにキャストしてopに代入 この時RPN[i+2]は演算子
				switch(op){
					case '+':
						RPN[i] = ""+(Double.parseDouble(RPN[i]) + Double.parseDouble(RPN[i+1]));
						break;
					case '-':
						RPN[i] = ""+(Double.parseDouble(RPN[i]) - Double.parseDouble(RPN[i+1]));
						break;
					case '*':
						RPN[i] = ""+(Double.parseDouble(RPN[i]) * Double.parseDouble(RPN[i+1]));
						break;
					case '/':
						RPN[i] = ""+(Double.parseDouble(RPN[i]) / Double.parseDouble(RPN[i+1]));
						break;
					case '^':
						RPN[i] = ""+Math.pow(Double.parseDouble(RPN[i]), Double.parseDouble(RPN[i+1]));
						break;
				}
				for(int j=i;j<RPN.length-3;j++){
					RPN[j+1] = RPN[j+3];
					Flag[j+1] = Flag[j+3];
				}
				//System.out.println(Arrays.toString(RPN));
				i=0;	//計算が行われたら、最初から検索
			}
			else i++;	//計算が行われなかったら、次を検索
		}	
	}

	/**関数の演算を行う*/
	public void func_opration() {
		// TODO 自動生成されたメソッド・スタブ
		if(this.tmp.equals("e"))this.tmp = ""+Math.E;
		if(this.tmp.equals("π"))this.tmp = ""+Math.PI;
		
		if(func.equals("sin")){
			this.tmp = ""+Math.sin(Double.parseDouble(this.tmp));
		}
		if(func.equals("cos")){
			this.tmp = ""+Math.cos(Double.parseDouble(this.tmp));
		}
		if(func.equals("tan")){
			this.tmp = ""+Math.tan(Double.parseDouble(this.tmp));
		}
		if(func.equals("asin")){
			this.tmp = ""+Math.asin(Double.parseDouble(this.tmp));
		}
		if(func.equals("acos")){
			this.tmp = ""+Math.acos(Double.parseDouble(this.tmp));
		}
		if(func.equals("atan")){
			this.tmp = ""+Math.atan(Double.parseDouble(this.tmp));
		}
		if(func.equals("log")){
			this.tmp = ""+Math.log10(Double.parseDouble(this.tmp));
		}
		if(func.equals("lg")){
			this.tmp = ""+Math.log(Double.parseDouble(this.tmp)) / Math.log(2.0);
		}
		if(func.equals("ln")){
			this.tmp = ""+Math.log(Double.parseDouble(this.tmp));
		}
		if(func.equals("√")){
			this.tmp = ""+Math.sqrt(Double.parseDouble(this.tmp));
		}
	}
}
