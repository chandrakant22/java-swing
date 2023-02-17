import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import model.Product;
import model.ProductDao;

public class SearchProduct {
	private ArrayList<Product> listProduct;
	private ArrayList<JButton> listEdit, listDelete;
	JFrame f;

	public SearchProduct() {

		listProduct = new ArrayList<Product>();
		listEdit = new ArrayList<JButton>();
		listDelete = new ArrayList<JButton>();

		int x = 30, y = 50, w = 70, h = 30;
		int fontSize = 20;
		f = new JFrame("Inventory Management System");// creating instance of JFrame

		JTextField tx = new JTextField();// creating instance of JButton
		tx.setFont(new Font("Arial", Font.PLAIN, fontSize));
		tx.setBounds(x, y, w + 500, h);// x axis, y axis, width, height

		JButton b2 = new JButton("Search");// creating instance of JButton
		b2.setFont(new Font("Arial", Font.PLAIN, fontSize));
		b2.setBounds(x + w + 410 + w + 20, y, w + 30, h - 1);// x axis, y axis,width, height

		JLabel lb = new JLabel("SEARCH RESULT");
		lb.setFont(new Font("Arial", Font.PLAIN, fontSize));
		lb.setBounds(30, y + h + 40, 500, h);

		// jtable
		JTable jt = new JTable(new ClientTableModel());
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		jt.getColumn("Edit").setCellRenderer(buttonRenderer);
		jt.getColumn("Delete").setCellRenderer(buttonRenderer);
		jt.addMouseListener(new JTableButtonMouseListener(jt));

		jt.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
		jt.setFont(new Font("Arial", Font.PLAIN, 16));
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(30, y + h + 80, 800, 300);

		
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(50);
		jt.getColumnModel().getColumn(1).setPreferredWidth(350);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);
		jt.getColumnModel().getColumn(3).setPreferredWidth(100);
		jt.getColumnModel().getColumn(4).setPreferredWidth(100);
		jt.getColumnModel().getColumn(5).setPreferredWidth(100);

		

		// jTable
		int k=0;
		// actions
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ((tx.getText() == null) || (tx.getText().length() == 0))
					return;

				listProduct = new ProductDao().searchproduct(tx.getText().trim());
				listEdit.clear();
				listDelete.clear();
				
				for (int i = 0; i < listProduct.size(); i++) {
					JButton btn = new JButton("Edit");
					final int k=i;
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							//JOptionPane.showMessageDialog(f, "Successfully! Record save"+listProduct.get(k).getpName());
							//((DefaultTableModel) jt.getModel()).fireTableDataChanged();
							f.setVisible(false);
							new EditStock(listProduct.get(k).getpId());

							
						}
					});
					listEdit.add(btn);
					btn = new JButton("Delete");
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							 int dialogButton = JOptionPane.YES_NO_OPTION;
						        int dialogResult = JOptionPane.showConfirmDialog (f, "Would you like to delete this Product?", "Warning", dialogButton);
						        if(dialogResult == JOptionPane.YES_OPTION){
						            ProductDao clientDAO = new ProductDao();
						            int index=listProduct.get(k).getpId();
						            clientDAO.deleteStock(index);
						            listProduct.remove(index);
						            listEdit.remove(index);
						            listDelete.remove(index);
						        }
						        ((DefaultTableModel)jt.getModel()).fireTableDataChanged();
							
						}
					});
					listDelete.add(btn);

				}
				((DefaultTableModel) jt.getModel()).fireTableDataChanged();
			}
		});

		f.add(tx);// adding button in JFrame
		f.add(b2);
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


	class ClientTableModel extends DefaultTableModel {
		private String[] columnNames = { "ID", "Product Name", "Quantity", "Rate", "Edit", "Delete" };
		private final Class<?>[] columnTypes = new Class<?>[] { Integer.class, String.class, String.class, String.class,
				JButton.class, JButton.class };

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return listProduct.size();
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		@Override
		public Object getValueAt(final int rowIndex, final int columnIndex) {
			/* Adding components */
			switch (columnIndex) {
			case 0:
				return listProduct.get(rowIndex).getpId();
			case 1:
				return listProduct.get(rowIndex).getpName();
			case 2:
				return listProduct.get(rowIndex).getpQuantity();
			case 3:
				return listProduct.get(rowIndex).getpRate();
			case 4:
				return listEdit.get(rowIndex);
			case 5:
				return listDelete.get(rowIndex);

			default:
				return "Error";
			}
		}
	}

	class JTableButtonMouseListener extends MouseAdapter {
		private final JTable table;

		public JTableButtonMouseListener(JTable table) {
			this.table = table;
		}

		public void mouseClicked(MouseEvent e) {
			int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the column of the button
			int row = e.getY() / table.getRowHeight(); // get the row of the button

			// *Checking the row or column is valid or not
			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
				Object value = table.getValueAt(row, column);
				if (value instanceof JButton) {
					// perform a click event
					((JButton) value).doClick();
				}
			}
		}
	}

	class JTableButtonRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JButton button = (JButton) value;
			return button;
		}
	}

	
	
	public static void main(String[] args) {

		new SearchProduct();

	}
	
	void showWindow()
	{
	  f.setVisible(true);	
	}

}
