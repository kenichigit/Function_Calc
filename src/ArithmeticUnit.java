
public class ArithmeticUnit {
	
	CalcTextField textField;

	public ArithmeticUnit(CalcTextField textField) {
		super();
		this.textField = textField;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**後置記法の数式の演算を行う*/
	public  void operation(String[] RPN,boolean[] Flag){
		
		int len = 0;
		//数値,演算子の個数lenはnullが見つかるまでのRPNの個op[j-1]=="+"数
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
		boolean PI=false;
		
		if(textField.tmp.equals("e")){
			textField.tmp = ""+Math.E;
		}
		if(textField.tmp.equals("π")){
			textField.tmp = ""+Math.PI;
			PI = true;
		}
		
		if(textField.func.equals("sin")){
			if(PI)textField.tmp = "0";
			else textField.tmp = ""+Math.sin(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("cos")){
			textField.tmp = ""+Math.cos(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("tan")){
			textField.tmp = ""+Math.tan(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("asin")){
			textField.tmp = ""+Math.asin(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("acos")){
			textField.tmp = ""+Math.acos(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("atan")){
			textField.tmp = ""+Math.atan(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("log")){
			textField.tmp = ""+Math.log10(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("lg")){
			textField.tmp = ""+Math.log(Double.parseDouble(textField.tmp)) / Math.log(2.0);
		}
		if(textField.func.equals("ln")){
			textField.tmp = ""+Math.log(Double.parseDouble(textField.tmp));
		}
		if(textField.func.equals("√")){
			textField.tmp = ""+Math.sqrt(Double.parseDouble(textField.tmp));
		}
	}
}
