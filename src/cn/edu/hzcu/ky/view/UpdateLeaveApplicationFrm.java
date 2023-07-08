package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

import cn.edu.hzcu.ky.dao.LeaveApplicationDao;
import cn.edu.hzcu.ky.model.BeanLeaveApplication;
import cn.edu.hzcu.ky.util.DBUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class UpdateLeaveApplicationFrm extends JInternalFrame {
	private JTextField studentID;
	private JTextField startYear;
	private JTextField startMonth;
	private JTextField startDay;
	private JTextField endYear;
	private JTextField endMonth;
	private JTextField endDay;
	private JComboBox<String> comboBox_timeslot;
	private JTextPane textPane_reason;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateLeaveApplicationFrm frame = new UpdateLeaveApplicationFrm();
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
	public UpdateLeaveApplicationFrm() {
		setClosable(true);
		setTitle("填写请假");
		setBounds(100, 100, 826, 426);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("你好，");
		lblNewLabel.setBounds(23, 37, 46, 15);
		getContentPane().add(lblNewLabel);
		
		studentID = new JTextField();
		studentID.setBounds(56, 34, 66, 21);
		getContentPane().add(studentID);
		studentID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("请填写你的请假信息👇");
		lblNewLabel_1.setBounds(132, 37, 162, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("请假时间：");
		lblNewLabel_2.setBounds(23, 73, 79, 15);
		getContentPane().add(lblNewLabel_2);
		
		startYear = new JTextField();
		startYear.setColumns(10);
		startYear.setBounds(23, 112, 62, 22);
		getContentPane().add(startYear);
		
		JLabel lblNewLabel_2_1 = new JLabel("年");
		lblNewLabel_2_1.setBounds(86, 114, 21, 18);
		getContentPane().add(lblNewLabel_2_1);
		
		startMonth = new JTextField();
		startMonth.setColumns(10);
		startMonth.setBounds(107, 112, 34, 22);
		getContentPane().add(startMonth);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("月");
		lblNewLabel_2_1_1.setBounds(146, 114, 21, 18);
		getContentPane().add(lblNewLabel_2_1_1);
		
		startDay = new JTextField();
		startDay.setColumns(10);
		startDay.setBounds(166, 112, 34, 22);
		getContentPane().add(startDay);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("日");
		lblNewLabel_2_1_1_1.setBounds(205, 114, 34, 18);
		getContentPane().add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("至");
		lblNewLabel_4.setBounds(230, 115, 46, 15);
		getContentPane().add(lblNewLabel_4);
		
		endYear = new JTextField();
		endYear.setColumns(10);
		endYear.setBounds(249, 112, 62, 22);
		getContentPane().add(endYear);
		
		JLabel lblNewLabel_2_2 = new JLabel("年");
		lblNewLabel_2_2.setBounds(312, 114, 21, 18);
		getContentPane().add(lblNewLabel_2_2);
		
		endMonth = new JTextField();
		endMonth.setColumns(10);
		endMonth.setBounds(333, 112, 34, 22);
		getContentPane().add(endMonth);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("月");
		lblNewLabel_2_1_2.setBounds(372, 114, 21, 18);
		getContentPane().add(lblNewLabel_2_1_2);
		
		endDay = new JTextField();
		endDay.setColumns(10);
		endDay.setBounds(392, 112, 34, 22);
		getContentPane().add(endDay);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("日");
		lblNewLabel_2_1_1_2.setBounds(431, 114, 34, 18);
		getContentPane().add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("请假时段：");
		lblNewLabel_3.setBounds(23, 162, 79, 15);
		getContentPane().add(lblNewLabel_3);
		
		comboBox_timeslot = new JComboBox<String>();
		comboBox_timeslot.setModel(new DefaultComboBoxModel<String>(new String[] {"一、二节", "三、四节", "三四五节", "六、七节", "八、九节"}));
		comboBox_timeslot.setToolTipText("");
		comboBox_timeslot.setBounds(86, 158, 86, 23);
		getContentPane().add(comboBox_timeslot);
		
		JLabel lblNewLabel_5 = new JLabel("说明原因：");
		lblNewLabel_5.setBounds(23, 209, 79, 15);
		getContentPane().add(lblNewLabel_5);
		
		textPane_reason = new JTextPane();
		textPane_reason.setBounds(23, 229, 341, 129);
		getContentPane().add(textPane_reason);
		
		JButton btnNewButton = new JButton("请假");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateactionPerformed(e);
				fillMyClassTable();
			}
		});
		btnNewButton.setBounds(132, 364, 95, 23);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 37, 287, 256);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4", "\u65F6\u6BB5", "\u539F\u56E0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("我的请假：");
		lblNewLabel_6.setBounds(513, 12, 79, 18);
		getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("取消选中");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = deleteactionPerformed(e);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
				}else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
				fillMyClassTable();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(586, 305, 99, 28);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_7 = new JLabel("管理员可在左侧输入指定学号查询");
		lblNewLabel_7.setBounds(281, 35, 205, 18);
		getContentPane().add(lblNewLabel_7);
		
		JButton btnNewButton_2 = new JButton("查询");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillMyClassTable();
			}
			
		});
		btnNewButton_2.setBounds(327, 66, 99, 28);
		getContentPane().add(btnNewButton_2);

		//如果LoginOnFrm.isAdmin为false,就将studentID设置为不可编辑，然后自动填充LoginOnFrm.userid
		if(!LoginOnFrm.isAdmin) {
			studentID.setEditable(false);
			studentID.setText(LoginOnFrm.userid);
			btnNewButton_2.setVisible(false);
			lblNewLabel_7.setVisible(false);
		}


		fillMyClassTable();
	}

	//根据studentid查询其请假信息填充在table中	
	private void fillMyClassTable() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			ResultSet rs = LeaveApplicationDao.getLeaveApplicationByStudentID(studentID.getText(), con);
			while(rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("StartDate"));
				v.add(rs.getString("EndDate"));
				v.add(rs.getString("TimeSlot"));
				v.add(rs.getString("Reason"));
				dtm.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	//提取所有输入文本框的内容
	private int UpdateactionPerformed(ActionEvent e) {
		String studentid = studentID.getText();
		String startyear = startYear.getText();
		String startmonth = startMonth.getText();
		String startday = startDay.getText();
		String endyear = endYear.getText();
		String endmonth = endMonth.getText();
		String endday = endDay.getText();
		String timeslot = (String) comboBox_timeslot.getSelectedItem();
		String reason = textPane_reason.getText();
		//comboBox_timeslot信息转化
		if(timeslot.equals("一、二节")) {
			timeslot = "001";
		}else if(timeslot.equals("三、四节")) {
			timeslot = "002";
		}else if(timeslot.equals("三四五节")) {
			timeslot = "003";
		}else if(timeslot.equals("六、七节")) {
			timeslot = "004";
		}else if(timeslot.equals("八、九节")) {
			timeslot = "005";
		}

		
		//判断输入是否为空
		if(studentID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "学号不能为空");
			return 0;
		}
		if(startYear.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "开始年份不能为空");
			return 0;
		}
		if(startMonth.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "开始月份不能为空");
			return 0;
		}
		
		if(startDay.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "开始日期不能为空");
			return 0;
		}
		if(endYear.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "结束年份不能为空");
			return 0;
		}
		if(endMonth.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "结束月份不能为空");
			return 0;
		}
		if(endDay.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "结束日期不能为空");
			return 0;
		}
		if(textPane_reason.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请假原因不能为空");
			return 0;
		}
		/*将年月日转化为Date类型
		*		
		*/
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = dateFormat.parse(startyear + "-" + startmonth + "-" + startday);
			Date startdate = new java.sql.Date(parsedDate.getTime());
			parsedDate = dateFormat.parse(endyear + "-" + endmonth + "-" + endday);
			Date enddate = new java.sql.Date(parsedDate.getTime());
			BeanLeaveApplication leaveapplication = new BeanLeaveApplication(studentid, startdate, enddate, timeslot, reason);
			//判断开始时间是否早于结束时间
			if(startdate.after(enddate)) {
				JOptionPane.showMessageDialog(null, "开始时间不能晚于结束时间");
				return 0;
			}
			int i = LeaveApplicationDao.addLeaveApplication(leaveapplication);
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "请假申请成功");
				return 1;
			}
			else {
				JOptionPane.showMessageDialog(null, "请假申请失败");
				return 0;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "日期格式错误");
		}
		return 0;
		
	}

	private int deleteactionPerformed(ActionEvent e){
		int j=0;
		//获取选中table的行的数据
		int i = table.getSelectedRow();
		if(i<0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的行");
			return 0;
		}
		String startdate = (String) table.getValueAt(i, 0);
		String enddate = (String) table.getValueAt(i, 1);
		String timeslot = (String) table.getValueAt(i, 2);
		String reason = (String) table.getValueAt(i, 3);
		//将时间转化为Date类型
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = dateFormat.parse(startdate);
			Date start = new java.sql.Date(parsedDate.getTime());
			parsedDate = dateFormat.parse(enddate);
			Date end = new java.sql.Date(parsedDate.getTime());
			BeanLeaveApplication leaveapplication = new BeanLeaveApplication(studentID.getText(), start, end, timeslot, reason);
			j = LeaveApplicationDao.deleteLeaveApplication(leaveapplication);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "日期格式错误");
		}
		return j;

	}
}
