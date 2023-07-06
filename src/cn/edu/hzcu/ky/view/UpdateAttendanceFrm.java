package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import cn.edu.hzcu.ky.dao.AttendanceDao;
import cn.edu.hzcu.ky.dao.StudentDao;
import cn.edu.hzcu.ky.model.BeanAttendance;
import cn.edu.hzcu.ky.util.DBUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;

public class UpdateAttendanceFrm extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAttendanceFrm frame = new UpdateAttendanceFrm();
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
	public UpdateAttendanceFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("更新考勤信息");
		setBounds(100, 100, 840, 460);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("签到学生ID：");
		lblNewLabel.setBounds(45, 88, 81, 18);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(122, 86, 94, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("签到时间：");
		lblNewLabel_1.setBounds(45, 145, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(122, 143, 62, 22);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("年");
		lblNewLabel_2.setBounds(185, 145, 21, 18);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(206, 143, 34, 22);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("月");
		lblNewLabel_2_1.setBounds(245, 145, 21, 18);
		getContentPane().add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(265, 143, 34, 22);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("日，");
		lblNewLabel_2_1_1.setBounds(304, 145, 34, 18);
		getContentPane().add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("签到类型：");
		lblNewLabel_1_1.setBounds(45, 204, 72, 18);
		getContentPane().add(lblNewLabel_1_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String> (new String[] {"正常", "迟到", "早退", "缺勤", "有课"}));
		comboBox.setBounds(122, 200, 62, 27);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("更新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateActionPerformed(e);
				fillAttendanceTable();
			}
		});
		btnNewButton.setBounds(70, 289, 99, 28);
		getContentPane().add(btnNewButton);
		
		textField_4 = new JTextField();
		textField_4.setBounds(331, 143, 41, 22);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("时");
		lblNewLabel_3.setBounds(372, 145, 21, 18);
		getContentPane().add(lblNewLabel_3);
		
		textField_5 = new JTextField();
		textField_5.setBounds(394, 143, 34, 22);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("分");
		lblNewLabel_4.setBounds(440, 145, 21, 18);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("持续时间：");
		lblNewLabel_5.setBounds(269, 204, 69, 18);
		getContentPane().add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(331, 200, 41, 22);
		getContentPane().add(textField_6);
		
		JLabel lblNewLabel_3_1 = new JLabel("时");
		lblNewLabel_3_1.setBounds(372, 202, 21, 18);
		getContentPane().add(lblNewLabel_3_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(394, 200, 34, 22);
		getContentPane().add(textField_7);
		
		JLabel lblNewLabel_4_1 = new JLabel("分（用于计算签退时间）");
		lblNewLabel_4_1.setBounds(440, 202, 145, 18);
		getContentPane().add(lblNewLabel_4_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 249, 449, 164);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751F\u7F16\u53F7", "\u7B7E\u5230\u65E5\u671F", "\u7B7E\u5230\u65F6\u95F4", "\u7B7E\u9000\u65F6\u95F4", "\u8003\u52E4\u7C7B\u578B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("删除选中");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
			
		});
		btnNewButton_1.setBounds(70, 363, 99, 28);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(580, 18, 195, 220);
		getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_1.getSelectedRow();
        		textField.setText((String) table_1.getValueAt(row, 0));
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FID", "\u5B66\u751F\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_6 = new JLabel("右侧可点击直接导入👉");
		lblNewLabel_6.setBounds(45, 58, 139, 18);
		getContentPane().add(lblNewLabel_6);

		fillStudentTable();
		fillAttendanceTable();

	}


	//提取被选中table行的数据
	private void deleteSelectedRowData(){
		int row=table.getSelectedRow();//获取选中的行
		if(row==-1){
			JOptionPane.showMessageDialog(null, "请选择一行");
			return;
		}
		String StudentID=(String) table.getValueAt(row, 0);
		String Date=(String) table.getValueAt(row, 1);
		String SignInTime=(String) table.getValueAt(row, 2);
		String SignOutTime=(String) table.getValueAt(row, 3);
		String AttendanceType=(String) table.getValueAt(row, 4);

		try {
			int i = AttendanceDao.deleteAttendance(StudentID, Date, SignInTime, SignOutTime, AttendanceType);
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功");
				fillAttendanceTable();
			}else{
				JOptionPane.showMessageDialog(null, "删除失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除选中行
	private void deleteActionPerformed(ActionEvent evt) {
		deleteSelectedRowData();
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

		//获取界面text信息
		private void updateActionPerformed(ActionEvent e){
			String studentId=this.textField.getText();
			String year=this.textField_1.getText();
			String month=this.textField_2.getText();
			String day=this.textField_3.getText();
			String hour=this.textField_4.getText();
			String minute=this.textField_5.getText();
			String durationHour=this.textField_6.getText();
			String durationMinute=this.textField_7.getText();
			String type=this.comboBox.getSelectedItem().toString();
			//System.out.println(type);
			//判断是否为空
			if(studentId==null||studentId.isEmpty()){
				JOptionPane.showMessageDialog(null, "学生ID不能为空！");
				return;
			}
			if(year==null||year.isEmpty()){
				JOptionPane.showMessageDialog(null, "年不能为空！");
				return;
			}
			if(month==null||month.isEmpty()){
				JOptionPane.showMessageDialog(null, "月不能为空！");
				return;
			}
			if(day==null||day.isEmpty()){
				JOptionPane.showMessageDialog(null, "日不能为空！");
				return;
			}
			if(hour==null||hour.isEmpty()){
				JOptionPane.showMessageDialog(null, "时不能为空！");
				return;
			}
			if(minute==null||minute.isEmpty()){
				JOptionPane.showMessageDialog(null, "分不能为空！");
				return;
			}
			if(durationHour==null||durationHour.isEmpty()){
				JOptionPane.showMessageDialog(null, "持续时间时不能为空！");
				return;
			}
			if(durationMinute==null||durationMinute.isEmpty()){
				JOptionPane.showMessageDialog(null, "持续时间分不能为空！");
				return;
			}

			try {
				// 将年月日转化为Date类型
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = dateFormat.parse(year + "-" + month + "-" + day);
				Date date = new java.sql.Date(parsedDate.getTime());
				
				// 将时分转化为Time类型
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				java.util.Date parsedTime = timeFormat.parse(hour + ":" + minute + ":00");
				Time time = new java.sql.Time(parsedTime.getTime());

				// 将durationHour和durationMinute转化为整数
				int durationHourInt = Integer.parseInt(durationHour);
				int durationMinuteInt = Integer.parseInt(durationMinute);

				// 使用Calendar来增加时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(parsedTime);
				calendar.add(Calendar.HOUR, durationHourInt); // 根据 durationHourInt 添加小时
				calendar.add(Calendar.MINUTE, durationMinuteInt); // 根据 durationMinuteInt 添加分钟

				Time signOutTime = new Time(calendar.getTimeInMillis()); // 获取签退时间


				BeanAttendance attendance = new BeanAttendance(studentId,date,time,signOutTime,type);
				int i = AttendanceDao.addattendance(attendance);
				if(i==1){
					JOptionPane.showMessageDialog(null, "添加成功！");
				}
				else{
					JOptionPane.showMessageDialog(null, "添加失败！");
				}
			} catch(Exception e1) {
				// 处理异常，例如无法解析的日期或时间
				e1.printStackTrace();
			}

		}

		private void fillStudentTable(){
			DefaultTableModel dtm=(DefaultTableModel) table_1.getModel();
			dtm.setRowCount(0);//设置成0行
			Connection con=null;
			try{
				con=DBUtil.getConnection();
				ResultSet rs=StudentDao.loadAllStudent(con);
				while(rs.next()){
					Vector<String> v=new Vector<>();
					v.add(rs.getString("StudentID"));
					v.add(rs.getString("Name"));
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
