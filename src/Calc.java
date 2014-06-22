import javax.swing.*;

import java.awt.*;

/**電卓クラス(mainクラス)*/

public class Calc extends JFrame {

	/**数字ボタン1～9,e,π,.*/
	private PublicButton[] pubButton = new PublicButton[30];
	/**関数ボタン*/
	private FunctionButton[] funButton = new FunctionButton[10];
	/**演算子ボタン,()*/
	private OpButton[] opButton = new OpButton[7];
	/**イコ-ルボタン*/
	private EqualButton equalButton;
	/**クリア-ボタン*/
	private ClearButton clearButton;
	/**表示/入力画面*/
	private CalcTextField textField = new CalcTextField();
	/** 最後にクリックされたボタン */
	public static char last_click;

	/**電卓クラスのインスタンス化*/
	public static void main(String[] args) {
		Calc calc = new Calc();
		calc.setVisible(true);
	}
	/**電卓を使えるようにする*/
	public Calc() {
		
		initFields();
		initGUI();
	}
	
	/**フィ-ルドの宣言*/
	void initFields(){
		
		/*ボタンのインスタンス化*/
		for (int i = 0; i < 10; i++) {
			pubButton[i] = new PublicButton(i, textField);
			add(pubButton[i]);
		}
		pubButton[10] = new PublicButton("π", textField);
		pubButton[11] = new PublicButton("e", textField);
		pubButton[12] = new PublicButton(".",textField);
		
		funButton[0] = new FunctionButton("sin", textField);
		funButton[1] = new FunctionButton("cos", textField);
		funButton[2] = new FunctionButton("tan", textField);
		funButton[3] = new FunctionButton("asin", textField);
		funButton[4] = new FunctionButton("acos", textField);
		funButton[5] = new FunctionButton("atan", textField);
		funButton[6] = new FunctionButton("log", textField); // log_10
		funButton[7] = new FunctionButton("ln", textField); // log_e
		funButton[8] = new FunctionButton("lg", textField); // log_2
		funButton[9] = new FunctionButton("√", textField); //平方根

		opButton[0] = new OpButton("+", textField);
		opButton[1] = new OpButton("-", textField);
		opButton[2] = new OpButton("*", textField);
		opButton[3] = new OpButton("/", textField);
		opButton[4] = new OpButton("^",  textField);
		opButton[5] = new OpButton("(",  textField);
		opButton[6] = new OpButton(")",  textField);

		equalButton = new EqualButton(textField);

		clearButton = new ClearButton(textField);
		
		
	}
	
	/**フレ-ムの設置*/
	void initGUI(){

		/*画面の設定*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,350);
		setLocation(100, 100);
		
		
		/*レイアウト*/
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = c.weighty = 1;
		c.gridx = GridBagConstraints.RELATIVE;

		c.gridy = 0;
		c.gridwidth = 5;
		add(textField, c);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridy = 1;
		for(int i=5;i<7;i++){
			add(opButton[i],c);
		}
		
		c.gridy = 2;
		for(int i=0;i<5;i++){
			add(funButton[i], c);
		}
		
		c.gridy = 3;
		for(int i=5;i<10;i++){
			add(funButton[i], c);
		}
		
		c.gridy = 4;
		add(pubButton[7], c);
		add(pubButton[8], c);
		add(pubButton[9], c);
		add(opButton[3], c);
		add(pubButton[11],c);

		c.gridy = 5;
		add(pubButton[4], c);
		add(pubButton[5], c);
		add(pubButton[6], c);
		add(opButton[2], c);
		add(pubButton[10], c);

		c.gridy=6;
		add(pubButton[1],c);
		add(pubButton[2],c);
		add(pubButton[3],c);
		add(opButton[1],c);
		add(opButton[4],c);
        
        c.gridy=7;
        add(pubButton[0], c);
        add(pubButton[12],c); 
		add(clearButton,c);
		add(opButton[0],c);
        add(equalButton,c);
		

	}

}