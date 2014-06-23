import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/** Cボタンクラス */
public class ClearButton extends JButton implements MouseListener {

	/** 表示する数 */
	private CalcTextField textField;

	/** Cボタンを使えるようにする */
	ClearButton(CalcTextField textField) {
		super("C");
		this.textField = textField;
		addMouseListener(this);
	}

    /** 表示する数を0にする */
	public void mouseClicked(MouseEvent e) {
		textField.clear();
	}
	 /** 今回は使用しない */
	public void mouseEntered(MouseEvent e) {
	}
	 /** 今回は使用しない */
	public void mouseExited(MouseEvent e) {
	}
	 /** 今回は使用しない */
	public void mousePressed(MouseEvent e) {
	}
	 /** 今回は使用しない */
	public void mouseReleased(MouseEvent e) {
	}
}
