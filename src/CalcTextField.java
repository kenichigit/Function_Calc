import java.util.Arrays;

import javax.swing.JTextField;
/**表示画面
 * 数式の入力、結果の出力を行う
 * */

public class CalcTextField extends JTextField{
	
	String tmp="";		//数値を一時的に入れる
	String[] RPN = new String[100];		//ポ-ランド記法を作る配列
	String[] op = new String[100];		//一時的に演算子,「()」を格納する配列
	int i = 0,j = 0;
	boolean[] flag = new boolean[100];
	String func;

	
	public CalcTextField() {
		super("");
		// TODO 自動生成されたコンストラクタ-・スタブ
	}
	
	/**　表示画面に加える */
	public void addString(String string) {
		//表示している数が0の状態で入力したなら表示するのはstring
		if("0".equals(getText()))this.setText(string);
		//桁を増やして並べて表示
		else this.setText(this.getText() + string);		
	}
	/** 表示する数を0にする */
	public void clear(){
		/*表示する数をリセット*/
		this.setText("");
		/*後置記法配列を初期化*/
		Arrays.fill(RPN,null);
		/*一時変数を初期化*/
		i=j=0;
		tmp="";
	}
	
	/**数式を後置記法に変換*/
	public void RPN_transform(String operator){
		
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
			//op[]の中で、「*」か「/」が一個前に入っているときに「+」か「-」が入る。
			if((op[j-2]=="*"||op[j-2]=="/") && (op[j-1]=="+"||op[j-1]=="-")){
				//演算子の優先順位で入れ替え
	    		RPN[i] = op[j-2];
				flag[i] = false;
	    		i++;
	    		op[j-2] = op[j-1];
	    		op[j-2] = null;
	    	}
		}
		
		if(operator == ")"){	//「)」が入力されたとき
			while(op[j] != "("){	//「(」が出てくるまでRPNの後ろにopの先頭を入れる
				RPN[i] = op[j-1];
				flag[i] = false;
				i++;
				j--;			
				if(RPN[i-1]=="(" || RPN[i-1]==")"){
					i--;
				}
			}
		}
			
		if(operator.equals("=")){	//「＝」が入ったらopに入ってる記号をRPNに入れる
			while(j!=0){	//opが空になったら終了
				RPN[i] = op[j-1];	
				flag[i] = false;
				i++;
				j--;
			}
		}
	}
	
	
	
	/**演算を行う*/
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
