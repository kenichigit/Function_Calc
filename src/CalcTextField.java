import javax.swing.JTextField;
/**表示画面
 * 数式の入力、結果の出力を行う
 * */

public class CalcTextField extends JTextField{
	
	String tmp;
	
	public CalcTextField() {
		super("");
		// TODO 自動生成されたコンストラクター・スタブ
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
		this.setText("");
	}
	
	/**数式を後置記法に変換*/
	public void RPN_transform(String oreprator){
		this.getText();
		
	}
	
	/**演算を行う*/
	public void operation(){

	}
}
