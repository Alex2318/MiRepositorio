import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Caja_Conexion extends JFrame {

	private JPanel contentPane;
	private JTextField JTextUser;
	private JPasswordField JTextPassword;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caja_Conexion frame = new Caja_Conexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Caja_Conexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(125, 28, 175, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUser = new JLabel("USER");
		lblUser.setBounds(45, 76, 46, 14);
		contentPane.add(lblUser);
		
		JTextUser = new JTextField();
		JTextUser.setBounds(161, 73, 225, 20);
		contentPane.add(JTextUser);
		JTextUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(45, 122, 78, 14);
		contentPane.add(lblPassword);
		
		JTextPassword = new JPasswordField();
		JTextPassword.setBounds(161, 119, 225, 20);
		contentPane.add(JTextPassword);
		JTextPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Logueate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion con=new Conexion(JTextUser.getText(), JTextPassword.getText());
			}
		});
		btnNewButton.setBounds(45, 189, 341, 23);
		contentPane.add(btnNewButton);
		
	
	}
}
