package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.hzcu.ky.dao.AttendanceDao;
import cn.edu.hzcu.ky.util.DBUtil;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SearchAttendanceFrm extends JInternalFrame {
	private JTextField startYear;
	private JTextField startMonth;
	private JTextField startDay;
	private JTextField startHour;
	private JTextField startMinute;
	private JTextField endYear;
	private JTextField endMonth;
	private JTextField endDay;
	private JTextField endHour;
	private JTextField endMinute;
	private JTextField studentId;
	private JTextField name1;
	private JTextField enrollmentYear;
	private JTextField major;
	private JTable table;
	private JComboBox<String> comboBox;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchAttendanceFrm frame = new SearchAttendanceFrm();
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
	public SearchAttendanceFrm() {
		setClosable(true);
		setTitle("管理员考勤查询");
		setBounds(100, 100, 760, 587);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("特定考勤查询");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 23));
		lblNewLabel.setBounds(281, 25, 158, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("在下方输入特定信息即可缩小查询范围");
		lblNewLabel_1.setBounds(243, 58, 265, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("时段：");
		lblNewLabel_2.setBounds(20, 100, 52, 15);
		getContentPane().add(lblNewLabel_2);
		
		startYear = new JTextField();
		startYear.setColumns(10);
		startYear.setBounds(12, 127, 62, 22);
		getContentPane().add(startYear);
		
		JLabel lblNewLabel_2_1 = new JLabel("年");
		lblNewLabel_2_1.setBounds(75, 129, 21, 18);
		getContentPane().add(lblNewLabel_2_1);
		
		startMonth = new JTextField();
		startMonth.setColumns(10);
		startMonth.setBounds(96, 127, 34, 22);
		getContentPane().add(startMonth);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("月");
		lblNewLabel_2_1_1.setBounds(135, 129, 21, 18);
		getContentPane().add(lblNewLabel_2_1_1);
		
		startDay = new JTextField();
		startDay.setColumns(10);
		startDay.setBounds(155, 127, 34, 22);
		getContentPane().add(startDay);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("日，");
		lblNewLabel_2_1_1_1.setBounds(194, 129, 34, 18);
		getContentPane().add(lblNewLabel_2_1_1_1);
		
		startHour = new JTextField();
		startHour.setColumns(10);
		startHour.setBounds(221, 127, 41, 22);
		getContentPane().add(startHour);
		
		JLabel lblNewLabel_3 = new JLabel("时");
		lblNewLabel_3.setBounds(262, 129, 21, 18);
		getContentPane().add(lblNewLabel_3);
		
		startMinute = new JTextField();
		startMinute.setColumns(10);
		startMinute.setBounds(284, 127, 34, 22);
		getContentPane().add(startMinute);
		
		JLabel lblNewLabel_4 = new JLabel("分  至");
		lblNewLabel_4.setBounds(325, 130, 46, 15);
		getContentPane().add(lblNewLabel_4);
		
		endYear = new JTextField();
		endYear.setColumns(10);
		endYear.setBounds(364, 127, 62, 22);
		getContentPane().add(endYear);
		
		JLabel lblNewLabel_2_2 = new JLabel("年");
		lblNewLabel_2_2.setBounds(427, 129, 21, 18);
		getContentPane().add(lblNewLabel_2_2);
		
		endMonth = new JTextField();
		endMonth.setColumns(10);
		endMonth.setBounds(448, 127, 34, 22);
		getContentPane().add(endMonth);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("月");
		lblNewLabel_2_1_2.setBounds(487, 129, 21, 18);
		getContentPane().add(lblNewLabel_2_1_2);
		
		endDay = new JTextField();
		endDay.setColumns(10);
		endDay.setBounds(507, 127, 34, 22);
		getContentPane().add(endDay);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("日，");
		lblNewLabel_2_1_1_2.setBounds(546, 129, 34, 18);
		getContentPane().add(lblNewLabel_2_1_1_2);
		
		endHour = new JTextField();
		endHour.setColumns(10);
		endHour.setBounds(573, 127, 41, 22);
		getContentPane().add(endHour);
		
		JLabel lblNewLabel_3_1 = new JLabel("时");
		lblNewLabel_3_1.setBounds(614, 129, 21, 18);
		getContentPane().add(lblNewLabel_3_1);
		
		endMinute = new JTextField();
		endMinute.setColumns(10);
		endMinute.setBounds(636, 127, 34, 22);
		getContentPane().add(endMinute);
		
		JLabel lblNewLabel_5 = new JLabel("分");
		lblNewLabel_5.setBounds(676, 130, 21, 15);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("学号：");
		lblNewLabel_6.setBounds(20, 174, 57, 15);
		getContentPane().add(lblNewLabel_6);
		
		studentId = new JTextField();
		studentId.setBounds(59, 171, 71, 21);
		getContentPane().add(studentId);
		studentId.setColumns(10);
		
		JLabel name = new JLabel("姓名：");
		name.setBounds(155, 174, 52, 15);
		getContentPane().add(name);
		
		name1 = new JTextField();
		name1.setBounds(194, 171, 68, 21);
		getContentPane().add(name1);
		name1.setColumns(10);
		
		JLabel lblNewLabel_7_1 = new JLabel("年级：");
		lblNewLabel_7_1.setBounds(281, 174, 52, 15);
		getContentPane().add(lblNewLabel_7_1);
		
		enrollmentYear = new JTextField();
		enrollmentYear.setColumns(10);
		enrollmentYear.setBounds(320, 171, 81, 21);
		getContentPane().add(enrollmentYear);
		
		JLabel lblNewLabel_7_2 = new JLabel("专业：");
		lblNewLabel_7_2.setBounds(411, 174, 52, 15);
		getContentPane().add(lblNewLabel_7_2);
		
		major = new JTextField();
		major.setColumns(10);
		major.setBounds(448, 171, 96, 21);
		getContentPane().add(major);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 253, 340, 305);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FID", "\u7B7E\u5230\u65E5\u671F", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4", "\u7B7E\u5230\u7C7B\u578B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton new_button = new JButton("查询");
		new_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		new_button.setBounds(112, 225, 95, 23);
		getContentPane().add(new_button);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "正常", "迟到", "早退", "缺勤", "有课"}));
		comboBox.setBounds(646, 168, 62, 27);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("考勤类型：");
		lblNewLabel_7.setBounds(573, 174, 71, 15);
		getContentPane().add(lblNewLabel_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(411, 253, 340, 305);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FID", "\u7B7E\u5230\u7C7B\u578B", "\u6B21\u6570\u7EDF\u8BA1"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_8 = new JLabel("详情查询：");
		lblNewLabel_8.setBounds(20, 212, 76, 15);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("统计查询：");
		lblNewLabel_9.setBounds(411, 212, 71, 15);
		getContentPane().add(lblNewLabel_9);
		
		JButton btnNewButton = new JButton("升序查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillAttendanceTableattendanceTypeAsc();
			}
		});
		btnNewButton.setBounds(448, 225, 95, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("降序查询");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillAttendanceTableattendanceTypeDesc();
			}
		});
		btnNewButton_1.setBounds(602, 225, 95, 23);
		getContentPane().add(btnNewButton_1);
		fillAttendanceTable();
		timer();
	}

	//设置一个每隔3秒刷新的计时器
	private void timer() {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				fillAttendanceTable();
			}
		}, 0, 15000);
	}
	//提取所有textfield,并完成搜索
	private void searchActionPerformed(ActionEvent e){
		//get all textfield
		String startYear = this.startYear.getText();
		String startMonth = this.startMonth.getText();
		String startDay = this.startDay.getText();
		String startHour = this.startHour.getText();
		String startMinute = this.startMinute.getText();
		String endYear = this.endYear.getText();
		String endMonth = this.endMonth.getText();
		String endDay = this.endDay.getText();
		String endHour = this.endHour.getText();
		String endMinute = this.endMinute.getText();
		String studentId = this.studentId.getText();
		String name = this.name1.getText();
		String enrollmentYear = this.enrollmentYear.getText();
		String major = this.major.getText();
		String attendanceType = (String) this.comboBox.getSelectedItem();
		//结束时间不大于开始时间
		if(!"".equals(endYear)&&!"".equals(startYear)){
			if(Integer.parseInt(endYear)<Integer.parseInt(startYear)) {
				JOptionPane.showMessageDialog(null, "结束时间不能小于开始时间");
				return;
			}
		}
		if(!"".equals(endMonth)&&!"".equals(startMonth)){
			if(Integer.parseInt(endMonth)<Integer.parseInt(startMonth)){
				JOptionPane.showMessageDialog(null, "结束时间不能小于开始时间");
				return;
			}
		}
		if(!"".equals(endDay)&&!"".equals(startDay)){
			if(Integer.parseInt(endYear)==Integer.parseInt(startYear)&&Integer.parseInt(endMonth)==Integer.parseInt(startMonth)&&Integer.parseInt(endDay)<Integer.parseInt(startDay)){
				JOptionPane.showMessageDialog(null, "结束时间不能小于开始时间");
				return;
			}
		}
		//search 
		try {
			ResultSet rs = null;

			//search all
			rs = AttendanceDao.searchSpecificAttendance(startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute, studentId, name, enrollmentYear, major,attendanceType);
			
			//fill table
			DefaultTableModel dtm=(DefaultTableModel) table.getModel();
			dtm.setRowCount(0);//设置成0行
			while(rs.next()){
				Vector<String> v=new Vector<>();
				v.add(rs.getString("StudentID"));
				v.add(rs.getString("Date"));
				v.add(rs.getString("SignInTime"));
				v.add(rs.getString("SignOutTime"));
				v.add(rs.getString("AttendanceType"));
				dtm.addRow(v);
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	//fillAttendanceTable according to attendanceType
	private void fillAttendanceTableattendanceTypeAsc(){
		String attendanceType = (String) this.comboBox.getSelectedItem();
		try {
			ResultSet rs = null;
			rs = AttendanceDao.searchSpecificAttendanceAsc(attendanceType);
			//fill table
			DefaultTableModel dtm=(DefaultTableModel) table_1.getModel();
			dtm.setRowCount(0);//设置成0行
			while(rs.next()){
				Vector<String> v=new Vector<>();
				v.add(rs.getString("StudentID"));
				v.add(rs.getString("AttendanceType"));
				v.add(rs.getString("Count"));
				dtm.addRow(v);
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void fillAttendanceTableattendanceTypeDesc(){
		String attendanceType = (String) this.comboBox.getSelectedItem();
		try {
			ResultSet rs = null;
			rs = AttendanceDao.searchSpecificAttendanceDesc(attendanceType);
			//fill table
			DefaultTableModel dtm=(DefaultTableModel) table_1.getModel();
			dtm.setRowCount(0);//设置成0行
			while(rs.next()){
				Vector<String> v=new Vector<>();
				v.add(rs.getString("StudentID"));
				v.add(rs.getString("AttendanceType"));
				v.add(rs.getString("Count"));
				dtm.addRow(v);
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void fillAttendanceTable() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try{
			con=DBUtil.getConnection();
			ResultSet rs=AttendanceDao.loadAllAttendance(con);
			while(rs.next()){
				Vector<String> v=new Vector<>();
				v.add(rs.getString("StudentID"));
				v.add(rs.getString("Date"));
				v.add(rs.getString("SignInTime"));
				v.add(rs.getString("SignOutTime"));
				v.add(rs.getString("AttendanceType"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				DBUtil.closeConnection(con);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
