package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("考勤选课系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 717);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("选课");
		setJMenuBar(menuBar);
		
		JMenu infoMenu = new JMenu("信息维护");
		menuBar.add(infoMenu);
		
		JMenuItem addcourse = new JMenuItem("更新课程信息（管理员）");
		
		addcourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCourseFrm courseFrm = new UpdateCourseFrm();
				courseFrm.setVisible(true);
				table.add(courseFrm);
			}
		});
		infoMenu.add(addcourse);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("更新开班信息（管理员）");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateClassRegistrationFrm classRegistrationFrm = new UpdateClassRegistrationFrm();
				classRegistrationFrm.setVisible(true);
				table.add(classRegistrationFrm);
			}
		});
		infoMenu.add(mntmNewMenuItem);

		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("更新学生信息");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudentsFrm studentsFrm = new UpdateStudentsFrm();
				studentsFrm.setVisible(true);
				table.add(studentsFrm);
			}
		});
		infoMenu.add(mntmNewMenuItem_1);
		
		JMenu kaoqin = new JMenu("考勤");
		menuBar.add(kaoqin);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("更新考勤信息（管理员）");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateAttendanceFrm attendanceFrm = new UpdateAttendanceFrm();
				attendanceFrm.setVisible(true);
				table.add(attendanceFrm);
			}
		});
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("一键签到（学生）");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OneClickAttendanceFrm oneClickAttendanceFrm = new OneClickAttendanceFrm();
				oneClickAttendanceFrm.setVisible(true);
				table.add(oneClickAttendanceFrm);
				
			}
		});
		kaoqin.add(mntmNewMenuItem_8);
		kaoqin.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("查询考勤信息（管理员）");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchAttendanceFrm searchAttendanceFrm = new SearchAttendanceFrm();
				searchAttendanceFrm.setVisible(true);
				table.add(searchAttendanceFrm);
			}
		});
		kaoqin.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("查询考勤信息（学生）");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuSearchAttendanceFrm stuSearchAttendanceFrm = new StuSearchAttendanceFrm();
				stuSearchAttendanceFrm.setVisible(true);
				table.add(stuSearchAttendanceFrm);
			}
		});
		kaoqin.add(mntmNewMenuItem_5);
		
		
		JMenu mnNewMenu = new JMenu("选课");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new SelectClassFrm().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("选课系统");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectClassFrm().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("请假");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("填写请假");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateLeaveApplicationFrm leaveApplicationFrm = new UpdateLeaveApplicationFrm();
				leaveApplicationFrm.setVisible(true);
				table.add(leaveApplicationFrm);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(5, 5, 1126, 647);
		table.setColumnSelectionAllowed(true);
		contentPane.add(table);
		//如果为学生登陆，则更新课程信息、更新开班信息、更新考勤信息、查询考勤信息不可见
		if(LoginOnFrm.isAdmin == false){
			addcourse.setVisible(false);
			mntmNewMenuItem.setVisible(false);
			mntmNewMenuItem_2.setVisible(false);
			mntmNewMenuItem_3.setVisible(false);

		}else if(LoginOnFrm.isAdmin == true){
			mntmNewMenuItem_5.setVisible(false);
			mnNewMenu.setVisible(false);
			mntmNewMenuItem_8.setVisible(false);
			
		}
	}
}
