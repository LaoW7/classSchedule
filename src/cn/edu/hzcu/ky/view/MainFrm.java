package cn.edu.hzcu.ky.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		setTitle("选课系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 717);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("选课");
		setJMenuBar(menuBar);
		
		JMenu infoMenu = new JMenu("信息维护");
		menuBar.add(infoMenu);
		
		JMenuItem addcourse = new JMenuItem("更新课程信息");
		
		addcourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCourseFrm courseFrm = new UpdateCourseFrm();
				courseFrm.setVisible(true);
				table.add(courseFrm);
			}
		});
		infoMenu.add(addcourse);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("更新开班信息");
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
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("更新考勤信息");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateAttendanceFrm attendanceFrm = new UpdateAttendanceFrm();
				attendanceFrm.setVisible(true);
				table.add(attendanceFrm);
			}
		});
		kaoqin.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("查询考勤信息");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		kaoqin.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu = new JMenu("选课");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelectClassFrm().setVisible(true);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
}
