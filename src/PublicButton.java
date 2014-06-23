import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**数字ボタンクラス
 *表示する数クラス(DisplayedNumber)に数字を渡す
 */
public class PublicButton extends JButton implements MouseListener {
	/**割り当てられた数字や記号 */
	private String string;
	private int digit;

	/**表示する数*/
	private CalcTextField textField;
	/** ボタンを使えるようにする */
	PublicButton(String string, CalcTextField textField) {
		
		super(string);
		this.string = string;
		this.textField = textField;
		addMouseListener(this);
	}
	PublicButton(int digit, CalcTextField textField) {
		
		super(""+digit);
		this.string = ""+digit;
		this.textField = textField;
		addMouseListener(this);
	}
	/**数字ボタンがクリックされたときに表示する数クラスに数字を渡す*/
	public void mouseClicked(MouseEvent e) {
		
		textField.tmp +=  string;
		
		//桁を増やす
		textField.addString(string);
		
		if(Calc.last_click != 'F'){
			Calc.last_click='P';
		}
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
