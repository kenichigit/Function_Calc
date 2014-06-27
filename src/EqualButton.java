import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**イコ-ルボタンクラス
 * 演算器(ArithmeticUnit)に演算を行わせるクラス。
 * JButtonを拡張したクラスであり，MouseListenerインタ-フェ-スを実装している。
 * */
public class EqualButton extends JButton implements MouseListener {

	private CalcTextField textField;

    /** 
     * 「=」ボタンを使えるようにする。
     * @param arithmUnit 
     */
	EqualButton(CalcTextField textField) {
		super("=");
		this.textField = textField;
		addMouseListener(this);
	}

	  /** コンポ-ネント上でマウスボタンがクリックされたときに動作 */
	public void mouseClicked(MouseEvent e) {		

		Calc.last_click ='=';
		
		/**後置記法に変換する*/
		textField.RPN_transform("=");
		
		System.out.println(Arrays.toString(textField.RPN));	//確認用
		
		/**計算を行う*/
		textField.operation(textField.RPN, textField.flag);
		
		textField.setText(textField.RPN[0]);
		
		textField.tmp = textField.RPN[0];
		
		textField.i = textField.j = 0;
	}
    /** 今回は使用しない */
	public void mouseEntered(MouseEvent e) {}
    /** 今回は使用しない */
	public void mouseExited(MouseEvent e) {}
    /** 今回は使用しない */
	public void mousePressed(MouseEvent e) {}
    /** 今回は使用しない */
	public void mouseReleased(MouseEvent e) {}
	
}
