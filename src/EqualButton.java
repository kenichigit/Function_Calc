import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**イコールボタンクラス
 * 演算器(ArithmeticUnit)に演算を行わせるクラス。
 * JButtonを拡張したクラスであり，MouseListenerインターフェースを実装している。
 * プッシュボタンの「=」を押されることにより演算器クラス(ArithmeticUnit)に演算を行わせる。
 * */
public class EqualButton extends JButton implements MouseListener {

    /** 
     * 演算器に受け渡すために用意した属性
     */
	private CalcTextField textField;

    /** 
     * =ボタンを使えるようにする。
     * @param arithmUnit 
     */
	EqualButton(CalcTextField textField) {
		super("=");
		this.textField = textField;
		addMouseListener(this);
	}

	  /** コンポーネント上でマウスボタンがクリックされたときに動作し、
     * 演算器(ArithmeticUnit)に演算を行わせ、
     * 表示する数(DisplayedNumber)に演算器(ArithmeticUnit)から読み出した値を代入させる。 */
	public void mouseClicked(MouseEvent e) {		
		/*計算を実行*/
		Calc.last_click ='=';
		
		/**後置記法に変換*/
		textField.RPN_transform("=");
		
		textField.operation(textField.RPN, textField.flag);
		
		
		textField.setText(textField.RPN[0]);
		
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
