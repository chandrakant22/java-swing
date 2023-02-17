import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Product;
import model.ProductDao;

public class EditStock {

	public EditStock(int pId ) {
		int x = 40, y = 40, w = 150, h = 30;
		
		Product product=new ProductDao().getOneProduct(pId);
		
		
		JFrame f = new JFrame("Update");// creating instance of JFrame

		JLabel lb1 = new JLabel("Product Name");
		lb1.setFont(new Font("Arial", Font.PLAIN, 20));
		lb1.setBounds(x, y, w, h);
		JTextField tx1 = new JTextField();
		tx1.setText(product.getpName());
		
		tx1.setFont(new Font("Arial", Font.PLAIN, 20));
		tx1.setBounds(x, y + h + 10, w + 145, h);

		y = y + h + 60;
		JLabel lb2 = new JLabel("Product Quantity");
		lb2.setFont(new Font("Arial", Font.PLAIN, 20));
		lb2.setBounds(x, y, w, h);
		JTextField tx2 = new JTextField();
		tx2.setText(product.getpQuantity());

		tx2.setFont(new Font("Arial", Font.PLAIN, 20));
		tx2.setBounds(x, y + h + 10, w + 145, h);

		y = y + h + 60;
		JLabel lb3 = new JLabel("Per Product Rate");
		lb3.setFont(new Font("Arial", Font.PLAIN, 20));
		lb3.setBounds(x, y, w + 50, h);
		JTextField tx3 = new JTextField();
		tx3.setText(product.getpRate());

		tx3.setFont(new Font("Arial", Font.PLAIN, 20));
		tx3.setBounds(x, y + h + 10, w + 145, h);

		y = y + h + 60;
		JLabel lb4 = new JLabel("Total Price");
		lb4.setFont(new Font("Arial", Font.PLAIN, 20));
		lb4.setBounds(x, y, w + 50, h);
		JTextField tx4 = new JTextField();
		int total=Integer.parseInt(product.getpQuantity())*Integer.parseInt(product.getpRate());
		tx4.setText(Integer.toString(total));

		tx4.setFont(new Font("Arial", Font.PLAIN, 20));
		tx4.setBounds(x, y + h + 10, w + 145, h);

		y = y + h + 90;
		JButton b1 = new JButton("Add Product");// creating instance of JButton
		b1.setFont(new Font("Arial", Font.PLAIN, 20));
		b1.setBounds(x, y, w, h);// x axis, y axis, width, height

		JButton b2 = new JButton("Clear");// creating instance of JButton
		b2.setFont(new Font("Arial", Font.PLAIN, 20));
		b2.setBounds(x + h + 140, y, w - 50, h);// x axis, y axis, width, height

		// action

		tx2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				String q = tx2.getText();
				String r = tx3.getText();
				if (q.equals("") || r.equals("")) {

				} else {
					int qty = Integer.parseInt(q);
					int rate = Integer.parseInt(r);
					tx4.setText(Integer.toString(qty * rate));
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		tx3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				String q = tx2.getText();
				String r = tx3.getText();
				if (q.equals("") || r.equals("")) {

				} else {
					int qty = Integer.parseInt(q);
					int rate = Integer.parseInt(r);
					tx4.setText(Integer.toString(qty * rate));
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		// Update
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pName = tx1.getText();
				String pQuantity = tx2.getText();
				String pRate = tx3.getText();

				if (pName.equals("")) {
					JOptionPane.showMessageDialog(f, "Please Enter Product Name!", "Message Box", JOptionPane.ERROR_MESSAGE);
				} else if (pQuantity.equals("")) {
					JOptionPane.showMessageDialog(f, "Please Enter Product Quantity!", "Message Box", JOptionPane.ERROR_MESSAGE);
				} else if (pRate.equals("")) {
					JOptionPane.showMessageDialog(f, "Please Enter Product Rate!", "Message Box", JOptionPane.ERROR_MESSAGE);
				} else {

					Product p = new Product(pId,pName, pQuantity, pRate);

					int a = 0;

					try {
						
						a = new ProductDao().update(p);
						
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

					if (a > 0) {
						JOptionPane.showMessageDialog(f, "Successfully! Record Update");
						// https://www.javatpoint.com/java-joptionpane

						tx1.setText("");
						tx2.setText("");
						tx3.setText("");
						tx4.setText("");
						f.dispose();
						new SearchProduct().showWindow();

					} else {
						JOptionPane.showMessageDialog(f, "Error!", "Message Dialog", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tx1.setText("");
				tx2.setText("");
				tx3.setText("");
				tx4.setText("");
			}
		});

		f.add(lb1);
		f.add(tx1);

		f.add(lb2);
		f.add(tx2);

		f.add(lb3);
		f.add(tx3);

		f.add(lb4);
		f.add(tx4);

		f.add(b1);
		f.add(b2);

		Color color = new Color(255, 249, 243);
		f.getContentPane().setBackground(color);
		f.setResizable(false);
		f.setSize(400, 580);// 400 width and 500 height
		f.setLayout(null); // using no layout managers
		f.setVisible(true); // making the frame visible

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int wx = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		int wy = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		f.setLocation(wx, wy);

	}

//	public static void main(String[] args) {
//
//		new AddStock();
//
//	}

}
