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
	
	
	/**数式を後置記法に変換*/
	public void RPN_transform(String operator){
		
		if(tmp != ""){
			RPN[i] = tmp;//数字を入れる
			flag[i] = true;
			i++;
		}
		
		if(Calc.last_click =='O'){
			op[j] = operator;//演算記号を入れる
			j++;
		}

		
			//op[]の中で、「*」か「/」が一個前に入っているときに「+」か「-」が入る。
		if( j>=2 && (op[j-2]=="*"||op[j-2]=="/") && (op[j-1]=="+"||op[j-1]=="-"||op[j-2]=="*"||op[j-2]=="/")){
				//演算子の優先順位で入れ替え
	    		RPN[i] = op[j-2];
				flag[i] = false;
	    		i++;
	    		op[j-2] = op[j-1];
	    		op[j-2] = null;
		}
		
		if(j>=2 && (op[j-2]=="+"||op[j-2]=="-") && (op[j-1]=="+"||op[j-1]=="-"||op[j-2]=="*"||op[j-2]=="/") ){
			RPN[i] = op[j-1];
			flag[i] = false;
			i++;
			j--;
			
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
	
	

}
