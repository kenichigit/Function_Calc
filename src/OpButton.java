/*演算子*/
import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**演算記号ボタン 
 * 演算器クラスに演算子を設定する  */
public class OpButton extends JButton implements MouseListener {
	/** 割り当てられた演算記号  */
	private String operator;
	/** 演算器クラス(ArithmeticUnit)とのメッセージの受け渡しのために用意された属性  */
	private CalcTextField textField;
	
	/** 演算記号ボタンを使えるようにする。  */
	OpButton(String opStr, CalcTextField textField) {
		super(opStr);
		this.operator = opStr;
		this.textField = textField;
		addMouseListener(this);
	}

	/** マウスで演算記号ボタンがクリックされると,演算器クラス(ArithmeticUnit)に選択された演算子を伝える。  */
	public void mouseClicked(MouseEvent e) {
		
		/*演算子が連続で押された際、置き換える*/
		String tmp;
		if(Calc.last_click == 'O'){
			tmp = textField.getText();
			textField.setText(tmp.substring(0, tmp.length()-1) );
		}
		
		/**表示する数の桁を増やす*/
		textField.addString(operator);
		
		if(Calc.last_click == 'F'){
			if(operator.equals(")")){
				
				/*ここで関数計算してtextfield.tmpに答え入れる*/
				textField.func_opration();
				
				Calc.last_click = 'P';
			}
		}
		else{
			Calc.last_click = 'O';
			
			/**後置記法の為の操作*/
			textField.RPN_transform(operator);
			
			textField.tmp = "";
		}
	}

	/** 今回は使用しない  */
	public void mouseEntered(MouseEvent e) {}
	/** 今回は使用しない  */
	public void mouseExited(MouseEvent e) {}
	/** 今回は使用しない  */
	public void mousePressed(MouseEvent e) {}
	/** 今回は使用しない  */
	public void mouseReleased(MouseEvent e) {}

}
