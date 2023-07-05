package cn.edu.hzcu.ky.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.hzcu.ky.dao.ClassDao;
import cn.edu.hzcu.ky.util.DBUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectClassFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;

	String classname;
	String courseid1;
	String term1;
	String timeslot1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("你好，");
		
		JLabel lblNewLabel_1 = new JLabel("欢迎你选课。");
		
		JLabel lblNewLabel_2 = new JLabel(LoginOnFrm.userid);
		
		JLabel lblNewLabel_4 = new JLabel("您选中的课程是：");
		
		JLabel selectInfo = new JLabel(classname);
		
		JButton btnNewButton = new JButton("选课");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSelectedRowData();
				selectActionPerformed(e);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 702, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selectInfo, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(selectInfo))
					.addGap(28)
					.addComponent(btnNewButton)
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D", "\u662F\u5426\u4E3A\u7279\u8272\u73ED", "\u4E0A\u8BFE\u5B66\u671F", "\u65F6\u95F4\u6BB5", "\u5468", "\u5468ID"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(88);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		fillClassTable();


	}
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
				v.add(rs.getString(4));
				v.add(rs.getString(3));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//提取被选中table行的数据
	private void getSelectedRowData(){
		int row=table.getSelectedRow();//获取选中的行
		String courseId=table.getValueAt(row, 0).toString();
		courseid1 = courseId;
		String className=table.getValueAt(row, 1).toString();
		classname = className;
		//String isSpecial=table.getValueAt(row, 2).toString();
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
		if(mod==1){
			JOptionPane.showMessageDialog(null, "选课成功！");
			//fillSelectClassTable();
		}else{
			JOptionPane.showMessageDialog(null, "选课失败！");
		}
	}
}
