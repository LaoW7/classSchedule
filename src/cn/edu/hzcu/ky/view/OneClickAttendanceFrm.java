package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import cn.edu.hzcu.ky.util.DBUtil;

public class OneClickAttendanceFrm extends JInternalFrame {

	private Timer timer;
	private String currentSemester;
	private String academicYear;
	private	String semester;
	private String Timeslotstart;
	private String Timeslotend;
	private String AttendanceType;
	private	String semesterID;
	private int s=0;
	JLabel currentsemester = new JLabel(currentSemester);
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneClickAttendanceFrm frame = new OneClickAttendanceFrm();
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
	public OneClickAttendanceFrm() {
		setClosable(true);
		setTitle("一键签到");
		setBounds(100, 100, 694, 372);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("你好，“"+LoginOnFrm.userid+"”点击下方一键签到");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(28, 25, 401, 31);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("一键签到");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickActionPerfomed(e);
				currentsemester.setText(currentSemester);
			}
		});
		btnNewButton.setBounds(40, 259, 95, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("当前的系统时间为：");
		lblNewLabel_1.setBounds(28, 97, 351, 15);
		getContentPane().add(lblNewLabel_1);
		
		currentsemester = new JLabel(currentSemester);
		currentsemester.setFont(new Font("宋体", Font.PLAIN, 16));
		currentsemester.setBounds(28, 140, 492, 31);
		getContentPane().add(currentsemester);

		// 创建一个日期时间格式化器
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
		
		// 创建一个新的Timer，设置为每秒执行一次
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取当前的日期/时间
				LocalDateTime now = LocalDateTime.now();
				// 格式化日期/时间
				String str = now.format(formatter);
				// 更新标签文本
				lblNewLabel_1.setText("当前的系统时间为：" + str);
			}
		});
		
		// 开始计时器
		timer.start();
		

	}

	protected void clickActionPerfomed(ActionEvent e) {

		//获取系统当前日期、时间，转化为String格式Date：yyyy-MM-dd，time：HH:mm:ss
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		//根据日期得到今天是星期几，格式为星期几
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("E");
		System.out.println(now.format(formatter3));//星期几
		String date = now.format(formatter1);
		String time = now.format(formatter2);

		//获取当前登录用户的学号
		String studentid = LoginOnFrm.userid;

		//根据当前时间查询当前所在学期，获取当前学期信息
		try {
			rs = null;
			Connection conn = DBUtil.getConnection();
			String sql="SELECT AcademicYear, Semester,SemesterID FROM semester WHERE StartDate <= ? AND EndDate >= ?";
			PreparedStatement ps = conn.prepareStatement(sql);



			ps.setString(1, date);
			ps.setString(2, date);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					academicYear = rs.getString("AcademicYear");
					semester = rs.getString("Semester");
					currentSemester = "当前日期在学期内. 学年: " + academicYear + ", 学期: " + semester;
					semesterID = rs.getString("SemesterID");
					System.out.println(currentSemester);

				} else {
					currentSemester="当前日期不在学期内.";
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//星期数据转换
		String weekid = null;
		if(now.format(formatter3).equals("星期一")){
			weekid = "001";
		}else if(now.format(formatter3).equals("星期二")){
			weekid = "002";
		}else if(now.format(formatter3).equals("星期三")){
			weekid = "003";
		}else if(now.format(formatter3).equals("星期四")){
			weekid = "004";
		}else if(now.format(formatter3).equals("星期五")){
			weekid = "005";
		}else if(now.format(formatter3).equals("星期六")){
			weekid = "006";
		}else if(now.format(formatter3).equals("星期日")){
			weekid = "007";
		}
		//根据studentid、当前学期信息查询当前学生的课程信息储存在ResultSet中
		
		try{
			rs = null;
			Connection conn = DBUtil.getConnection();
			String sql="SELECT DISTINCT\r\n" + //
					"\t`courseregistration`.`StudentID` AS `StudentID`,\r\n" + //
					"\t`courseregistration`.`CourseID` AS `CourseID`,\r\n" + //
					"\t`courseregistration`.`TimeSlot` AS `TimeSlot`,\r\n" + //
					"\t`detailedclassschedule`.`WeekID` AS `WeekID`,\r\n" + //
					"\tcount(0) AS `count(*)`,\r\n" + //
					"\t`courseregistration`.`Semester` AS `Semester`,\r\n" + //
					"\t`timeslot`.`StartTime` AS `StartTime`,\r\n" + //
					"\t`timeslot`.`EndTime` AS `EndTime`\r\n" + //
					"FROM\r\n" + //
					"\t(\r\n" + //
					"\t\t(\r\n" + //
					"\t\t\t`courseregistration`\r\n" + //
					"\t\t\tJOIN `detailedclassschedule` ON (\r\n" + //
					"\t\t\t\t(\r\n" + //
					"\t\t\t\t\t`courseregistration`.`TimeSlot` = `detailedclassschedule`.`TimeSlot`\r\n" + //
					"\t\t\t\t)\r\n" + //
					"\t\t\t)\r\n" + //
					"\t\t)\r\n" + //
					"\t\tJOIN `timeslot` ON (\r\n" + //
					"\t\t\t(\r\n" + //
					"\t\t\t\t`detailedclassschedule`.`TimeSlot` = `timeslot`.`TimeSlotID`\r\n" + //
					"\t\t\t)\r\n" + //
					"\t\t)\r\n" + //
					"\t)\r\n" + //
					"WHERE\r\n" + //
					"\t(\r\n" + //
					"\t\t(\r\n" + //
					"\t\t\t`courseregistration`.`StudentID` = ?\r\n" + //
					"\t\t)\r\n" + //
					"\t\tAND (\r\n" + //
					"\t\t\t`courseregistration`.`Semester` = ?\r\n" + //
					"\t\t)\r\n" + //
					"\t\tAND (\r\n" + //
					"\t\t\t`detailedclassschedule`.`WeekID` = ?\r\n" + //
					"\t\t)\r\n" + //
					"\t)\r\n" + //
					"GROUP BY\r\n" + //
					"\t`courseregistration`.`StudentID`";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, studentid);
			ps.setString(2, semesterID);
			ps.setString(3, weekid);

			System.out.println(studentid);
			System.out.println(semesterID);
			System.out.println(weekid);

			rs=ps.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				System.out.println(rs);
				Timeslotstart = rs.getString("StartTime");
				Timeslotend = rs.getString("EndTime");

				s = 1;

				System.out.println(Timeslotstart);
				System.out.println(Timeslotend);


			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		//如果rs为空，提示当前学生没有课程
		try {
			//将字符串转换为LocalTime对象
			LocalTime currentTime = LocalTime.parse(time, formatter2);
			LocalTime timeslotStartTime = Timeslotstart != null ? LocalTime.parse(Timeslotstart, formatter2) : null;
			LocalTime timeslotEndTime = Timeslotend != null ? LocalTime.parse(Timeslotend, formatter2) : null;

			System.out.println(currentTime);
			System.out.println(timeslotStartTime);
			System.out.println(timeslotEndTime);

			if(timeslotEndTime != null) { // 先确保timeslotEndTime不为null
				if(currentTime.isAfter(timeslotEndTime)) {
					System.out.println("Current time is after timeslot end time.");
				} else {
					System.out.println("Current time is not after timeslot end time.");
				}
			} else {
				System.out.println("Timeslot end time is not specified.");
			}


			if(s == 0||currentTime.isAfter(timeslotEndTime)){
				JOptionPane.showMessageDialog(null, "当前没有课程，无需签到！");
			}
			//如果rs不为空，录入签到信息
			else if(s == 1&&!currentTime.isAfter(timeslotEndTime)){
				try{
					Connection conn = DBUtil.getConnection();
					//如果当前时间前后不超过Timeslotstart20分钟，AttendanceType为正常，否则为迟到

					//对Timeslotstart进行null检查
					if(Timeslotstart != null) {
						//计算两个时间之间的差距
						long minutesDiff = Duration.between(currentTime, timeslotStartTime).toMinutes();

						//比较差距是否在20分钟之内
						if(Math.abs(minutesDiff) <= 20){
							AttendanceType = "正常";
						}else{
							AttendanceType = "迟到";
						}
					} else {
						AttendanceType = "迟到"; // 如果Timeslotstart为空，则设置为迟到
					}

					String sql="INSERT INTO attendance (StudentID, Date, SignInTime, AttendanceType) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, studentid);
					ps.setString(2, date);
					ps.setString(3, time);
					ps.setString(4, AttendanceType);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "签到成功！");
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 





		}

		
}
