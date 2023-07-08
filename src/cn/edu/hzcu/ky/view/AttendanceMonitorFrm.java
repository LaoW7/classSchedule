package cn.edu.hzcu.ky.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AttendanceMonitorFrm extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceMonitorFrm frame = new AttendanceMonitorFrm();
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
	public AttendanceMonitorFrm() {
		setTitle("考勤监控");
		setBounds(100, 100, 736, 440);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("考勤异常监控：");
		lblNewLabel.setBounds(352, 34, 84, 15);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 35, 257, 316);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FID", "\u5F02\u5E38\u7C7B\u578B", "\u8BB0\u5F55\u65E5\u671F", "\u8BB0\u5F55\u65F6\u95F4"
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
		
		JLabel lblNewLabel_1 = new JLabel("特定查询：");
		lblNewLabel_1.setBounds(10, 34, 95, 15);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"正常", "迟到", "早退", "缺勤", "有课"}));
		comboBox.setBounds(92, 84, 62, 27);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("查询类型：");
		lblNewLabel_2.setBounds(12, 90, 69, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("特定时段：");
		lblNewLabel_3.setBounds(12, 149, 69, 15);
		getContentPane().add(lblNewLabel_3);

	}
}
