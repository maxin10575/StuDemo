package swt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackSwing {

	static JFrame frmZk;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackSwing window = new BackSwing();
					window.frmZk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BackSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZk = new JFrame();
		frmZk.getContentPane().setFont(new Font("ËÎÌå", Font.PLAIN, 16));
		frmZk.getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
		frmZk.setTitle("ZK\u6570\u636E\u5907\u4EFD\u6062\u590D\u5DE5\u5177");
		frmZk.setBounds(400, 200, 450, 300);
		frmZk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZk.getContentPane().setLayout(null);

		btnNewButton = new JButton("\u5907\u4EFD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackSwt.startBackSwt();
			}
		});
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(59, 102, 142, 52);
		frmZk.getContentPane().add(btnNewButton);

		JButton button_1 = new JButton("\u6062\u590D");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackSwt2.startBackSwt2();
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(233, 102, 142, 52);
		frmZk.getContentPane().add(button_1);
	}
}
