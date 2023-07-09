package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.hzcu.ky.dao.AdministratorDao;
import cn.edu.hzcu.ky.dao.StudentDao;
import cn.edu.hzcu.ky.model.BeanAdministrator;
import cn.edu.hzcu.ky.model.BeanStudent;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class LoginOnFrm extends JFrame {
	public static String userid;
	public static boolean isAdmin=true;

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private StudentDao studentDao = new StudentDao();
	private AdministratorDao administratorDao = new AdministratorDao();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOnFrm frame = new LoginOnFrm();
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
	public LoginOnFrm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(61, 125, 55, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(61, 178, 51, 15);
		
		textField = new JTextField();
		textField.setBounds(120, 131, 139, 21);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("选课考勤系统");
		lblNewLabel_2.setBounds(123, 54, 158, 43);
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 26));
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setBounds(83, 219, 95, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed(arg0);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setBounds(213, 219, 95, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				resetActionPerformed(arg1);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("用户类别");
		lblNewLabel_3.setBounds(269, 134, 57, 15);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(330, 131, 80, 21);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"学生", "管理员"}));

		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel);
		contentPane.add(textField);
		contentPane.add(lblNewLabel_3);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 175, 136, 21);
		contentPane.add(passwordField);
	}

	protected void resetActionPerformed(ActionEvent arg1) {//清空账号和密码
		this.textField.setText("");
		this.passwordField.setText("");
	}

	private void loginActionPerformed(ActionEvent arg0) {//登录事件
		String name=this.textField.getText();
		String password=new String(this.passwordField.getPassword());
		System.out.println(name+","+password);
		if(StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		
		String userID=name;
		String userType = comboBox.getSelectedItem().toString();
		//System.out.println(userType);
		if(userType.equals("学生")){//学生登录
			isAdmin=false;
			BeanStudent currentUser = new BeanStudent(userID,password);
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				BeanStudent currentStudent = studentDao.login(conn, currentUser);
				//System.out.println(currentStudent);
				if(currentStudent!=null) {
					userid=userID;
					dispose();
					new MainFrm().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "账号或密码错误");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					DBUtil.closeConnection(conn);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}else{//管理员登录
			isAdmin=true;
			BeanAdministrator currentUser = new BeanAdministrator(userID,password);
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				BeanAdministrator currentAdmin = administratorDao.login(conn, currentUser);
				if(currentAdmin!=null) {
					userid=userID;
					dispose();
					new MainFrm().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "账号或密码错误");
				}
			} catch (Exception e) {
			}finally {
				try {
					DBUtil.closeConnection(conn);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
