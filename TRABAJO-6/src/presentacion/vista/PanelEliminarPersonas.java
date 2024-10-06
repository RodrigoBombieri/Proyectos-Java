package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

public class PanelEliminarPersonas extends JPanel {
	private JList listaPersonas;
	private JButton btnEliminar;
	
	public PanelEliminarPersonas() {
		setLayout(null);
		
		JLabel lblEliminarUsuarios = new JLabel("Eliminar Usuarios");
		lblEliminarUsuarios.setBounds(164, 23, 127, 16);
		add(lblEliminarUsuarios);
		
		listaPersonas = new JList();
		listaPersonas.setBounds(123, 48, 181, 194);
		add(listaPersonas);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(123, 251, 181, 25);
		add(btnEliminar);

	}

	public JList getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(JList listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	
	
}
