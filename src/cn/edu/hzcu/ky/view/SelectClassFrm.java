package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.hzcu.ky.dao.ClassDao;
import cn.edu.hzcu.ky.util.DBUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SelectClassFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;

	String classname;
	String courseid1;
	String term1;
	public static String isSpecial;
	String timeslot1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectClassFrm frame = new SelectClassFrm();
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
	public SelectClassFrm() {
		setTitle("选课");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 830, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 214, 757, 242);
		
		JLabel lblNewLabel = new JLabel("你好，");
		lblNewLabel.setBounds(15, 56, 43, 15);
		
		JLabel lblNewLabel_1 = new JLabel("欢迎你选课。");
		lblNewLabel_1.setBounds(131, 56, 104, 15);
		
		JLabel lblNewLabel_2 = new JLabel(LoginOnFrm.userid);
		//System.err.println(LoginOnFrm.userid);
		lblNewLabel_2.setBounds(57, 56, 64, 15);
		
		JLabel lblNewLabel_4 = new JLabel("点击下方课程进行选课：");
		lblNewLabel_4.setBounds(15, 135, 172, 15);
		
		JButton btnNewButton = new JButton("选课");
		btnNewButton.setBounds(15, 161, 95, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSelectedRowData();
				selectActionPerformed(e);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(298, 33, 391, 175);
		
		JLabel lblNewLabel_3 = new JLabel("你的选课信息：");
		lblNewLabel_3.setBounds(298, 12, 108, 15);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u540D", "\u7279\u8272\u73ED", "\u5B66\u671F", "\u65F6\u6BB5", "\u5468"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(58);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(58);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(58);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(57);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(47);
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D", "\u662F\u5426\u4E3A\u7279\u8272\u73ED", "\u4E0A\u8BFE\u5B66\u671F", "\u65F6\u95F4\u6BB5", "\u5468", "\u5468ID", "\u5468\u6BB5"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(88);
		contentPane.setLayout(null);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_4);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel_3);
		contentPane.add(scrollPane_1);
		
		JButton btnNewButton_1 = new JButton("退选");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(511, 8, 104, 23);
		contentPane.add(btnNewButton_1);

		fillClassTable();
		fillYourClassTable();

	}
	/*
	 * 个人课表Table填充数据	
	 */
	private void fillYourClassTable(){
		DefaultTableModel dtm=(DefaultTableModel) table_1.getModel();
		dtm.setRowCount(0); // 设置成0行
		// 从数据库中查询数据
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			ResultSet rs=ClassDao.loadYourAllClass(con);
			while(rs.next()){
				Vector<String> v=new Vector<>();
				v.add(rs.getString(1));
				if(rs.getInt(2)==1) {
					v.add("是");
				}
				else {
					v.add("否");
				}
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 课表Table填充数据	
	 */
	private void fillClassTable(){
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // 设置成0行
		// 从数据库中查询数据
		Connection con=null;
		try {
			con=DBUtil.getConnection();
			ResultSet rs=ClassDao.loadAllClass(con);
			while(rs.next()){
				Vector<String> v=new Vector<>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				if(rs.getInt(4)==1) {
					v.add("是");
				}
				else {
					v.add("否");
				}
				v.add(rs.getString(3));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				DBUtil.closeConnection(con);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//提取被选中table行的数据
	private void getSelectedRowData(){
		int row=table.getSelectedRow();//获取选中的行
		String courseId=table.getValueAt(row, 0).toString();
		courseid1 = courseId;
		String className=table.getValueAt(row, 1).toString();
		classname = className;
		isSpecial=table.getValueAt(row, 2).toString();
		String term=table.getValueAt(row, 3).toString();
		term1 = term;
		String timeslot=table.getValueAt(row, 6).toString();
		timeslot1 = timeslot;
		//String week=table.getValueAt(row, 5).toString()
		
	}
	private void selectActionPerformed(ActionEvent e){
		int mod = 0;
		mod = ClassDao.addCourseRegistration(LoginOnFrm.userid, courseid1, term1,timeslot1);
		System.out.println(courseid1);
		System.out.println(term1);
		System.out.println(timeslot1);

		if(isSpecial=="是") {
			try {
			Connection conn = DBUtil.getConnection();

            // Check if the student has already registered for a course in the same time slot and same semester.
            String checkSql = "select count(*) as count,Semester from courseregistration where StudentID = ? and TimeSlot in (select TimeSlotID from timeslot where TimeSlot = ?)";
            PreparedStatement checkPst = conn.prepareStatement(checkSql);
            checkPst.setString(1, LoginOnFrm.userid);
            checkPst.setString(2, timeslot1);
            ResultSet rs = checkPst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0 && rs.getString("Semester").equals(term1) ) {
                // There is a conflict.
                mod=-2;
            }
            rs.close();
            checkPst.close();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		



		if(mod==1){
			JOptionPane.showMessageDialog(null, "选课成功！");
			fillYourClassTable();
		}else if(mod==0){
			JOptionPane.showMessageDialog(null, "选课失败！");
		}else if(mod==-1){
			JOptionPane.showMessageDialog(null, "时间冲突！");
		}else if(mod==-2){
			JOptionPane.showMessageDialog(null, "你选择的是特色班，且和普通课程时间冲突，将为您自动请假！");
		}
	}

	private void deleteActionPerformed(ActionEvent e){
		int row=table_1.getSelectedRow();//获取选中的行
		String coursename=table_1.getValueAt(row, 0).toString();
		String term=table_1.getValueAt(row, 2).toString();
		String timeslot=table_1.getValueAt(row, 3).toString();
		int mod = 0;
		mod = ClassDao.deleteCourseRegistration(LoginOnFrm.userid, coursename, term,timeslot);
		if(mod==1){
			JOptionPane.showMessageDialog(null, "退课成功！");
			fillYourClassTable();
		}else{
			JOptionPane.showMessageDialog(null, "退课失败！");
		}

	}
}
