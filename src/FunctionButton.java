import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**関数ボタンクラス
 *表示する数クラス(DisplayedNumber)に値を渡す
 */
public class FunctionButton extends JButton implements MouseListener {
	/**割り当てられた関数 */
	private String func;
	/**表示する数*/
	private CalcTextField textField;
	
	/** 関数ボタンを使えるようにする */
	FunctionButton(String func, CalcTextField textField) {
		super(func);//long→String変換しButtonにsetText
		this.func = func;
		this.textField = textField;
		addMouseListener(this);
	}

	/**関数ボタンがクリックされたときに表示する数クラスに渡す*/
	public void mouseClicked(MouseEvent e) {
		textField.addString(func+"(");
		Calc.last_click = 'F';
		textField.func = this.func;
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
