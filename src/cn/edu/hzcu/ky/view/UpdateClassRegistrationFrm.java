package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import cn.edu.hzcu.ky.dao.ClassDao;
import cn.edu.hzcu.ky.model.BeanClassSchedule;
import cn.edu.hzcu.ky.model.BeanDetailClassSchedule;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class UpdateClassRegistrationFrm extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//添加所有radiobutton的声明
	private JRadioButton SpeciallRadioButton_yes;
	private JRadioButton SpeciallRadioButton_no;
	private JRadioButton timeRadioButton_1;
	private JRadioButton timeRadioButton_2;
	private JRadioButton timeRadioButton_3;
	private JRadioButton timeRadioButton_4;
	private JRadioButton timeRadioButton_5;
	private JRadioButton timeRadioButton_6;
	private JRadioButton timeRadioButton_7;
	private JRadioButton dayRadioButton_1;
	private JRadioButton dayRadioButton_2;
	private JRadioButton dayRadioButton_3;
	private JRadioButton dayRadioButton_4;
	private JRadioButton dayRadioButton_5;
	private JRadioButton dayRadioButton_6;
	private JRadioButton dayRadioButton_7;
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClassRegistrationFrm frame = new UpdateClassRegistrationFrm();
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
	public UpdateClassRegistrationFrm() {
		setClosable(true);
		setTitle("开班");
		setBounds(100, 100, 857, 470);
		
		JLabel lblNewLabel = new JLabel("开班课程ID：");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("班级名：");
		
		JLabel lblNewLabel_2 = new JLabel("开班学期：");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("是否为特色班？");
		
		SpeciallRadioButton_yes = new JRadioButton("是");
		
		SpeciallRadioButton_no = new JRadioButton("否");
		
		JLabel lblNewLabel_4 = new JLabel("开班时段：");
		
		timeRadioButton_1 = new JRadioButton("一二节");
		
		timeRadioButton_2 = new JRadioButton("三四节");
		
		timeRadioButton_3 = new JRadioButton("三四五节");
		
		timeRadioButton_4 = new JRadioButton("六七节");
		
		timeRadioButton_5 = new JRadioButton("八九节");
		
		JLabel lblNewLabel_5 = new JLabel("开班日：");
		
		 dayRadioButton_1 = new JRadioButton("周一");
		
		dayRadioButton_2 = new JRadioButton("周二");
		
		dayRadioButton_3 = new JRadioButton("周三");
		
		dayRadioButton_4 = new JRadioButton("周四");
		
		dayRadioButton_5 = new JRadioButton("周五");
		
		dayRadioButton_6 = new JRadioButton("周六");
		
		dayRadioButton_7 = new JRadioButton("周天");
		
		JButton btnNewButton = new JButton("开班");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassActionPerformed(e);
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SpeciallRadioButton_yes, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(SpeciallRadioButton_no, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(70)
											.addComponent(lblNewLabel_1))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(61)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(timeRadioButton_1)
												.addComponent(dayRadioButton_1))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(timeRadioButton_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
												.addComponent(dayRadioButton_2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(timeRadioButton_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
												.addComponent(dayRadioButton_3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(timeRadioButton_4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
												.addComponent(dayRadioButton_4, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(dayRadioButton_5, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
												.addComponent(timeRadioButton_5, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(73)
									.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
							.addGap(22)
							.addComponent(dayRadioButton_6, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(dayRadioButton_7, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(108)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(210, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(SpeciallRadioButton_yes)
						.addComponent(SpeciallRadioButton_no))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(timeRadioButton_1)
						.addComponent(timeRadioButton_2)
						.addComponent(timeRadioButton_3)
						.addComponent(timeRadioButton_4)
						.addComponent(timeRadioButton_5))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(dayRadioButton_1)
						.addComponent(dayRadioButton_2)
						.addComponent(dayRadioButton_3)
						.addComponent(dayRadioButton_4)
						.addComponent(dayRadioButton_5)
						.addComponent(dayRadioButton_6)
						.addComponent(dayRadioButton_7))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(62))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	
	private void ClassActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//实现所有文本框不为空，每一种radioButton都有选择且只能选一个，否则弹出提示框
		if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请填写完整信息");
		}
		else if(!timeRadioButton_1.isSelected()&&!timeRadioButton_2.isSelected()&&!timeRadioButton_3.isSelected()&&!timeRadioButton_4.isSelected()&&!timeRadioButton_5.isSelected()) {
			JOptionPane.showMessageDialog(null, "请选择时间");
		}
		else if(!dayRadioButton_1.isSelected()&&!dayRadioButton_2.isSelected()&&!dayRadioButton_3.isSelected()&&!dayRadioButton_4.isSelected()&&!dayRadioButton_5.isSelected()&&!dayRadioButton_6.isSelected()&&!dayRadioButton_7.isSelected()) {
			JOptionPane.showMessageDialog(null, "请选择日期");
		}
		else {
			//将信息存入数据库
			String courseid=textField.getText();
			String classname=textField_1.getText();
			String semester=textField_2.getText();
			String time="";
			String day="";
			if(timeRadioButton_1.isSelected()) {
				time="001";
			}
			else if(timeRadioButton_2.isSelected()) {
				time="002";
			}
			else if(timeRadioButton_3.isSelected()) {
				time="003";
			}
			else if(timeRadioButton_4.isSelected()) {
				time="004";
			}
			else if(timeRadioButton_5.isSelected()) {
				time="005";
			}
			System.out.println(time);
			if(dayRadioButton_1.isSelected()) {
				day="001";
			}
			else if(dayRadioButton_2.isSelected()) {
				day="002";
			}
			else if(dayRadioButton_3.isSelected()) {
				day="003";
			}
			else if(dayRadioButton_4.isSelected()) {
				day="004";
			}
			else if(dayRadioButton_5.isSelected()) {
				day="005";
			}
			else if(dayRadioButton_6.isSelected()) {
				day="006";
			}
			else if(dayRadioButton_7.isSelected()) {
				day="007";
			}
			System.out.println(day);
			int special=0;
			if(SpeciallRadioButton_yes.isSelected()) {
				special=1;
			}
			else if(SpeciallRadioButton_no.isSelected()) {
				special=0;
			}
			System.out.println(special);
			//将信息存入数据库
			BeanClassSchedule bcs = new BeanClassSchedule(courseid,classname,semester,special);
			int id=ClassDao.addClassSchedule(bcs);
			BeanDetailClassSchedule bdcs = new BeanDetailClassSchedule(id,time,day);
			ClassDao.addDetailClassSchedule(bdcs);
		}
	}
}
