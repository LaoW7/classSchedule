package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import cn.edu.hzcu.ky.dao.ClassDao;
import cn.edu.hzcu.ky.dao.CourseDao;
import cn.edu.hzcu.ky.model.BeanClassSchedule;
import cn.edu.hzcu.ky.model.BeanCourse;
import cn.edu.hzcu.ky.model.BeanDetailClassSchedule;
import cn.edu.hzcu.ky.util.BaseException;
import cn.edu.hzcu.ky.util.DBUtil;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

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
	private JRadioButton dayRadioButton_1;
	private JRadioButton dayRadioButton_2;
	private JRadioButton dayRadioButton_3;
	private JRadioButton dayRadioButton_4;
	private JRadioButton dayRadioButton_5;
	private JRadioButton dayRadioButton_6;
	private JRadioButton dayRadioButton_7;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField_3;
	



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
		setBounds(100, 100, 857, 534);
		
		JLabel lblNewLabel = new JLabel("开班课程ID：");
		lblNewLabel.setBounds(49, 26, 84, 15);
		
		textField = new JTextField();
		textField.setBounds(143, 23, 66, 21);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 79, 122, 21);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("班级名：");
		lblNewLabel_1.setBounds(71, 82, 57, 15);
		
		JLabel lblNewLabel_2 = new JLabel("开班学期：");
		lblNewLabel_2.setBounds(59, 134, 72, 15);
		
		textField_2 = new JTextField();
		textField_2.setBounds(141, 131, 66, 21);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("是否为特色班？");
		lblNewLabel_3.setBounds(260, 26, 123, 15);
		
		SpeciallRadioButton_yes = new JRadioButton("是");
		SpeciallRadioButton_yes.setBounds(360, 22, 48, 23);
		
		SpeciallRadioButton_no = new JRadioButton("否");
		SpeciallRadioButton_no.setBounds(431, 22, 52, 23);
		
		JLabel lblNewLabel_4 = new JLabel("开班时段：");
		lblNewLabel_4.setBounds(49, 234, 72, 15);
		
		timeRadioButton_1 = new JRadioButton("一二节");
		timeRadioButton_1.setBounds(127, 230, 92, 23);
		
		timeRadioButton_2 = new JRadioButton("三四节");
		timeRadioButton_2.setBounds(219, 230, 93, 23);
		
		timeRadioButton_3 = new JRadioButton("三四五节");
		timeRadioButton_3.setBounds(312, 230, 93, 23);
		
		timeRadioButton_4 = new JRadioButton("六七节");
		timeRadioButton_4.setBounds(405, 230, 88, 23);
		
		timeRadioButton_5 = new JRadioButton("八九节");
		timeRadioButton_5.setBounds(493, 230, 101, 23);
		
		JLabel lblNewLabel_5 = new JLabel("开班日：");
		lblNewLabel_5.setBounds(61, 275, 57, 15);
		
		 dayRadioButton_1 = new JRadioButton("周一");
		 dayRadioButton_1.setBounds(127, 271, 72, 23);
		
		dayRadioButton_2 = new JRadioButton("周二");
		dayRadioButton_2.setBounds(219, 271, 72, 23);
		
		dayRadioButton_3 = new JRadioButton("周三");
		dayRadioButton_3.setBounds(312, 271, 72, 23);
		
		dayRadioButton_4 = new JRadioButton("周四");
		dayRadioButton_4.setBounds(405, 271, 72, 23);
		
		dayRadioButton_5 = new JRadioButton("周五");
		dayRadioButton_5.setBounds(493, 271, 72, 23);
		
		dayRadioButton_6 = new JRadioButton("周六");
		dayRadioButton_6.setBounds(594, 271, 72, 23);
		
		dayRadioButton_7 = new JRadioButton("周天");
		dayRadioButton_7.setBounds(668, 271, 76, 23);
		
		JButton btnNewButton = new JButton("开班");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(61, 318, 95, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassActionPerformed(e);
				fillClassTable();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(textField);
		getContentPane().add(lblNewLabel_3);
		getContentPane().add(SpeciallRadioButton_yes);
		getContentPane().add(SpeciallRadioButton_no);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(lblNewLabel_4);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(textField_1);
		getContentPane().add(textField_2);
		getContentPane().add(dayRadioButton_1);
		getContentPane().add(timeRadioButton_1);
		getContentPane().add(dayRadioButton_2);
		getContentPane().add(timeRadioButton_2);
		getContentPane().add(dayRadioButton_3);
		getContentPane().add(timeRadioButton_3);
		getContentPane().add(dayRadioButton_4);
		getContentPane().add(timeRadioButton_4);
		getContentPane().add(timeRadioButton_5);
		getContentPane().add(dayRadioButton_5);
		getContentPane().add(lblNewLabel_5);
		getContentPane().add(dayRadioButton_6);
		getContentPane().add(dayRadioButton_7);
		getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(493, 47, 319, 177);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
        		textField.setText((String) table.getValueAt(row, 0));
				textField_1.setText((String) table.getValueAt(row, 1));
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D", "\u5B66\u5206"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("可开班的课程：点击可直接选择👇");
		lblNewLabel_6.setBounds(493, 22, 196, 15);
		getContentPane().add(lblNewLabel_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(219, 328, 618, 167);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u73ED\u7EA7\u540D\u79F0", "\u5F00\u73ED\u5B66\u671F", "\u662F\u5426\u4E3A\u7279\u8272\u73ED", "\u5F00\u73ED\u65F6\u6BB5", "\u5F00\u73ED\u65E5\u671F", "\u5468\u6BB5"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_7 = new JLabel("已经开过的班：");
		lblNewLabel_7.setBounds(219, 303, 103, 15);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("选中下方班级以删除👇");
		lblNewLabel_8.setBounds(415, 303, 137, 15);
		getContentPane().add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("删除开班");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				String courseID = (String) table_1.getValueAt(row, 0);
				String ClassName = (String) table_1.getValueAt(row, 1);
				String Semester = (String) table_1.getValueAt(row, 2);
				if(row<0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的班级");
					return;
				}
				try {
					int num = ClassDao.deleteCourseSchedule(courseID, ClassName, Semester);
					if(num==0) {
						JOptionPane.showMessageDialog(null, "该课程已被选，不能删除");
					}else if(num==1){
						JOptionPane.showMessageDialog(null, "删除成功");
						fillClassTable();
					}
					
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(547, 300, 95, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("周段：");
		lblNewLabel_9.setBounds(82, 182, 48, 15);
		getContentPane().add(lblNewLabel_9);
		
		textField_3 = new JTextField();
		textField_3.setText("1-16周");
		textField_3.setBounds(141, 179, 84, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("（默认全周，非特殊不更改）");
		lblNewLabel_10.setBounds(234, 182, 171, 15);
		getContentPane().add(lblNewLabel_10);
		
		fillCourseTable(new BeanCourse());
		fillClassTable();


	}
	
	
	private void ClassActionPerformed(ActionEvent e) {
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
			String WeekSlot=textField_3.getText();
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
			BeanDetailClassSchedule bdcs = new BeanDetailClassSchedule(id,time,day,WeekSlot);
			ClassDao.addDetailClassSchedule(bdcs);

		}
	}
		
	
	private void fillCourseTable(BeanCourse beancourse){
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		Connection conn=null;

		try {
			conn=DBUtil.getConnection();
			ResultSet rs = CourseDao.loadAllCourse(conn);
			//Set<Integer> set = new HashSet<>();
			//System.out.println("测试1");
			while(rs.next()){
				Vector<String> v = new Vector<>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				//System.out.println(rs.getString(1));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void fillClassTable(){
		DefaultTableModel dtm = (DefaultTableModel)table_1.getModel();
		dtm.setRowCount(0);
		Connection conn=null;

		try {
			conn=DBUtil.getConnection();
			ResultSet rs = ClassDao.loadAllClass(conn);
			//Set<Integer> set = new HashSet<>();
			//System.out.println("测试1");
			while(rs.next()){
				Vector<String> v = new Vector<>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				if(rs.getInt(4)==1) {
					v.add("是");
				}
				else {
					v.add("否");
				}
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(8));
				dtm.addRow(v);
				}
			} catch (Exception e) {
		}
	}
}
