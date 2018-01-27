package dentaku.com.github.erudosan0524;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dentaku extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderlayout = new BorderLayout();
	JTextField result = new JTextField("");
	boolean isStacked = false;
	boolean afterCalc =  false;
	String currentOp = "";
	double stackedValue = 0.0;

	public Dentaku() {

		result.setEditable(false);

		contentPane.setLayout(borderlayout);
		this.setSize(new Dimension(250, 300));
		this.setTitle("ìdëÏ");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH);

		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4, 4));
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new ButtonManager("7"), 0); // É{É^ÉìÇÉåÉCÉAÉEÉgÇ…ÇÕÇﬂÇ±ÇÒÇ≈Ç¢Ç≠
		keyPanel.add(new ButtonManager("8"), 1);
		keyPanel.add(new ButtonManager("9"), 2);
		keyPanel.add(new CalcButton("ÅÄ"), 3);
		keyPanel.add(new ButtonManager("4"), 4);
		keyPanel.add(new ButtonManager("5"), 5);
		keyPanel.add(new ButtonManager("6"), 6);
		keyPanel.add(new CalcButton("Å~"), 7);
		keyPanel.add(new ButtonManager("1"), 8);
		keyPanel.add(new ButtonManager("2"), 9);
		keyPanel.add(new ButtonManager("3"), 10);
		keyPanel.add(new CalcButton("Å|"), 11);
		keyPanel.add(new ButtonManager("0"), 12);
		keyPanel.add(new ButtonManager("."), 13);
		keyPanel.add(new CalcButton("Å{"), 14);
		keyPanel.add(new CalcButton("ÅÅ"), 15);

		contentPane.add(new ClearButton(), BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void appendResult(String c) {
		if (!afterCalc) {
			result.setText(result.getText() + c);
		} else {
			result.setText(c);
			afterCalc = false;
		}

	}

	public class ButtonManager extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ButtonManager(String keyTop) {
			super(keyTop);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			String keyNumber = this.getText();
			appendResult(keyNumber);

		}

	}

	public class CalcButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public CalcButton(String op) {

			super(op);
			this.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e) {

			if (isStacked) {
				double resultValue = (Double.valueOf(result.getText())).doubleValue();

				if (currentOp.equals("Å{"))
					stackedValue += resultValue;
				else if (currentOp.equals("Å|"))
					stackedValue -= resultValue;
				else if (currentOp.equals("Å~"))
					stackedValue *= resultValue;
				else if (currentOp.equals("ÅÄ"))
					stackedValue /= resultValue;

				result.setText(String.valueOf(stackedValue));

			}

			currentOp = this.getText();
			stackedValue = (Double.valueOf(result.getText())).doubleValue();
			afterCalc = true;
			if (currentOp.equals("ÅÅ")) {
				isStacked = false;
			} else {
				isStacked = true;
			}
		}
	}

	public class ClearButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ClearButton() {

			super("C");
			this.addActionListener(this);

		}

		public void actionPerformed(ActionEvent e) {
			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");
		}

	}

}
