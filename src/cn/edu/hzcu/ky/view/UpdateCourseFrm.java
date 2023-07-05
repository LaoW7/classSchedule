package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.hzcu.ky.dao.CourseDao;
import cn.edu.hzcu.ky.model.BeanCourse;
import cn.edu.hzcu.ky.util.BaseException;
import cn.edu.hzcu.ky.util.DBUtil;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class UpdateCourseFrm extends JInternalFrame {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourseFrm frame = new UpdateCourseFrm();
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
	public UpdateCourseFrm() {
		setTitle("更改课程信息");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 817, 455);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 203, 807, 223);
		scrollPane.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("课程ID");
		lblNewLabel.setBounds(157, 63, 43, 15);
		
		textField = new JTextField();
		textField.setBounds(204, 60, 66, 21);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("课程名字");
		lblNewLabel_1.setBounds(293, 66, 66, 15);
		
		textField_1 = new JTextField();
		textField_1.setBounds(363, 63, 66, 21);
		textField_1.setText("");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("学分");
		lblNewLabel_2.setBounds(476, 63, 37, 15);
		
		textField_2 = new JTextField();
		textField_2.setBounds(517, 60, 66, 21);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("添加课程");
		btnNewButton.setBounds(351, 113, 95, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("删除选中课程");
		btnNewButton_1.setBounds(10, 174, 116, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCourseActionPerformed(e);
			}
		});
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0BID", "\u8BFE\u7A0B\u540D", "\u5B66\u5206"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		getContentPane().add(btnNewButton);
		getContentPane().add(lblNewLabel);
		getContentPane().add(textField);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(textField_1);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(textField_2);
		getContentPane().add(btnNewButton_1);
		fillCourseTable(new BeanCourse());

	}

	private int deleteCourseActionPerformed(ActionEvent e){
		int i=this.table.getSelectedRow();
		if(i<0) {
			JOptionPane.showMessageDialog(null, "请选择课程");
			return 0;
		}
		String course_id=(String) this.table.getValueAt(i, 0);
		try {
			(new CourseDao()).deleteCourse(course_id);
			JOptionPane.showMessageDialog(null, "删除成功");
			//removeRow(i);
			fillCourseTable(new BeanCourse());
			return 1;
		}
		catch(BaseException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	private int addCourseActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(textField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "课程ID不能为空");
			return 0;
		}
		if(textField_1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "课程名字不能为空");
			return 0;
		}
		if(textField_2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "学分不能为空");
			return 0;
		}
		BeanCourse bc=new BeanCourse(textField.getText(),textField_1.getText(),Double.parseDouble(textField_2.getText()));
		try {
			(new CourseDao()).addCourse(bc);
			JOptionPane.showMessageDialog(null, "添加成功");
			fillCourseTable(new BeanCourse());
			return 1;
		}
		catch(BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "添加错误",JOptionPane.ERROR_MESSAGE);
			return 0;
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
}
