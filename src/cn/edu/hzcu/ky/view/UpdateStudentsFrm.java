package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import cn.edu.hzcu.ky.dao.StudentDao;
import cn.edu.hzcu.ky.model.BeanStudent;
import cn.edu.hzcu.ky.util.DBUtil;
import cn.edu.hzcu.ky.util.StringUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class UpdateStudentsFrm extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JButton btnNewButton;
	private boolean isAdmin = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudentsFrm frame = new UpdateStudentsFrm();
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
	public UpdateStudentsFrm() {
		setTitle("更新学生信息");
		setClosable(true);
		setBounds(100, 100, 634, 361);
		
		JLabel lblNewLabel = new JLabel("学号：");
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		
		JLabel lblNewLabel_2 = new JLabel("登陆密码：");
		
		JLabel lblNewLabel_3 = new JLabel("班级：");
		
		JLabel lblNewLabel_4 = new JLabel("入学年份：");
		
		JLabel lblNewLabel_5 = new JLabel("专业：");
		
		JLabel lblNewLabel_6 = new JLabel("电话号码：");
		
		JLabel lblEmail = new JLabel("Email：");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAdmin==false) {
					updateActionPerformed(e);
				}else{
					addActionPerformed(e);
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(265)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(264, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_4)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_5)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_6)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblEmail)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(21))
		);
		getContentPane().setLayout(groupLayout);

		fillPane();
		System.out.println(isAdmin);
		if(isAdmin==true){
			textField.setEditable(true);
		}
	}
		/*
	 * 添加信息
	 */
	private void addActionPerformed(ActionEvent e){
		String studentID = textField.getText();
		String studentName = textField_1.getText();
		String loginpwd = textField_2.getText();
		String class1 = textField_3.getText();
		String enrollmentYear = textField_4.getText();
		String major = textField_5.getText();
		String phoneNumber = textField_6.getText();
		String email = textField_7.getText();
		if(StringUtil.isEmpty(studentID+"")){
			JOptionPane.showMessageDialog(null, "学号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(studentName+"")){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(loginpwd+"")){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if(StringUtil.isEmpty(class1+"")){
			JOptionPane.showMessageDialog(null, "班级不能为空！");
			return;
		}
		if(StringUtil.isEmpty(enrollmentYear+"")){
			JOptionPane.showMessageDialog(null, "入学年份不能为空！");
			return;
		}
		if(StringUtil.isEmpty(major+"")){
			JOptionPane.showMessageDialog(null, "专业不能为空！");
			return;
		}
		if(StringUtil.isEmpty(phoneNumber+"")){
			JOptionPane.showMessageDialog(null, "电话号码不能为空！");
			return;
		}
		if(StringUtil.isEmpty(email+"")){
			JOptionPane.showMessageDialog(null, "邮箱不能为空！");
			return;
		}
		BeanStudent student = new BeanStudent(studentID,studentName,loginpwd,class1,enrollmentYear,major,phoneNumber,email);
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			int n = StudentDao.add(con, student);
			if(n==1){
				JOptionPane.showMessageDialog(null, "添加成功！");
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		}catch(Exception e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败！");
		}finally{
			try{
				DBUtil.closeConnection(con);
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
	/*
	* 修改信息
	* 
	*/
	private void updateActionPerformed(ActionEvent e){
		String studentID = textField.getText();
		String studentName = textField_1.getText();
		String loginpwd = textField_2.getText();
		String class1 = textField_3.getText();
		String enrollmentYear = textField_4.getText();
		String major = textField_5.getText();
		String phoneNumber = textField_6.getText();
		String email = textField_7.getText();
		if(StringUtil.isEmpty(studentID+"")){
			JOptionPane.showMessageDialog(null, "学号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(studentName+"")){
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(loginpwd+"")){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		updateInfo(studentID,studentName,loginpwd,class1,enrollmentYear,major,phoneNumber,email);
	}


	/*
	 * 修改信息
	 */

	private void updateInfo(String studentID, String studentName, String loginpwd, String class1, String enrollmentYear,
			String major, String phoneNumber, String email) {
		BeanStudent student = new BeanStudent();
		student.setStudentID(studentID);
		student.setName(studentName);
		student.setLoginPassword(loginpwd);
		student.setClass1(class1);
		student.setEnrollmentYear(enrollmentYear);
		student.setMajor(major);
		student.setPhoneNumber(phoneNumber);
		student.setEmail(email);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int n = StudentDao.update(conn, student);
			if(n==1){
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败！");
		}
	}

	/*
	 * 展示信息
	 */
	private void fillPane(){
		BeanStudent student = new BeanStudent();
		student.setStudentID(LoginOnFrm.userid);
		Connection	conn=null;
		try {
			conn = DBUtil.getConnection();
			ResultSet rs = StudentDao.list(conn, student);
	
			if(rs.next()){
				textField.setText(rs.getString(1));//StudentID
				textField_1.setText(rs.getString(2));//StudentName
				textField_2.setText(rs.getString(3));//Loginpwd
				textField_3.setText(rs.getString(4));//Class
				textField_4.setText(rs.getString(5));//EnrollmentYear
				textField_5.setText(rs.getString(6));//Major
				textField_6.setText(rs.getString(7));//PhoneNumber
				textField_7.setText(rs.getString(8));//Email
			}else{
					isAdmin=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				DBUtil.closeConnection(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	

}
