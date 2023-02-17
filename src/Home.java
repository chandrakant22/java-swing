import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.ProductDao;

public class Home {

	Home() {
		int x = 30, y = 50, w = 70, h = 30;
		int fontSize = 20;
		JFrame f = new JFrame("Inventory Management System");// creating instance of
																// JFrame

		JButton b = new JButton("Add");// creating instance of JButton
		b.setFont(new Font("Arial", Font.PLAIN, fontSize));
		b.setBounds(x, y, w, h);// x axis, y axis, width, height

		JButton b2 = new JButton("Search");// creating instance of JButton
		b2.setFont(new Font("Arial", Font.PLAIN, fontSize));

		b2.setBounds(x + w + 20, y, w + 30, h);// x axis, y axis, width, height

		x = x + w + 20 + 30;
		JButton b3 = new JButton("Update");// creating instance of JButton
		b3.setFont(new Font("Arial", Font.PLAIN, fontSize));
		b3.setBounds(x + w + 20, y, w + 30, h);// x axis, y axis, width, height

		x = x + w + 20 + 30;
		JButton b4 = new JButton("Delete");// creating instance of JButton
		b4.setFont(new Font("Arial", Font.PLAIN, fontSize));
		b4.setBounds(x + w + 20, y, w + 30, h);// x axis, y axis, width, height

		JLabel lb = new JLabel("Low Inventory and out of stock items");
		lb.setFont(new Font("Arial", Font.PLAIN, fontSize));
		lb.setBounds(30, y + h + 40, 500, h);

		// jtable
		String column[] = { "ID", "Product NAME", "Quantity", "Rate" };
		//String data[][] = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" }, { "101", "Sachin", "700000" } };
		
		
		JTable jt = new JTable(new ProductDao().getProduct(), column);

		jt.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		jt.setFont(new Font("Arial", Font.PLAIN, 15));
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(30, y + h + 80, 800, 300);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(50);
		jt.getColumnModel().getColumn(1).setPreferredWidth(350);
		jt.getColumnModel().getColumn(2).setPreferredWidth(200);
		jt.getColumnModel().getColumn(3).setPreferredWidth(200);
		
		// jTable

		// actions
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new AddStock();
			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchProduct();
			}
		});

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//update
			}
		});
		
		
		f.add(b);// adding button in JFrame
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(lb);
		f.add(sp);

		Color color = new Color(255, 249, 243);
		f.getContentPane().setBackground(color);
		f.setResizable(false);
		f.setSize(900, 600);// 400 width and 500 height
		f.setLayout(null);// using no layout managers
		f.setVisible(true);// making the frame visible

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int wx = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int wy = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(wx, wy);
	}
	
	
	

	public static void main(String[] args) {

		new Home();

	}

}
