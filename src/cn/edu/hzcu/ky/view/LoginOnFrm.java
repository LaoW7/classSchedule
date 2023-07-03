package cn.edu.hzcu.ky.view;

import java.awt.BorderLayout;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LoginOnFrm extends JFrame {
	public static String userid;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private StudentDao studentDao = new StudentDao();
	private AdministratorDao administratorDao = new AdministratorDao();

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
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("选课系统");
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 26));
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActionPerformed(arg0);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				resetActionPerformed(arg1);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("用户类别");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"学生", "管理员"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(173)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(127))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void resetActionPerformed(ActionEvent arg1) {//清空账号和密码
		// TODO Auto-generated method stub
		this.textField.setText("");
		this.textField_1.setText("");
	}

	private void loginActionPerformed(ActionEvent arg0) {//登录事件
		String name=this.textField.getText();
		String password=this.textField_1.getText();
		//System.out.println(name+","+password);
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
