package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelModificarPersonas extends JPanel {
	private JTextField txtNombreModificar;
	private JTextField txtApellidoModificar;
	private JTextField txtDniModificar;
	private JList listaPersonas;
	private JButton btnModificar;

	/**
	 * Create the panel.
	 */
	public PanelModificarPersonas() {
		setLayout(null);
		
		JLabel lblSeleccioneLaPersona = new JLabel("Seleccione la persona que desea modificar:");
		lblSeleccioneLaPersona.setBounds(94, 23, 250, 16);
		add(lblSeleccioneLaPersona);
		
		listaPersonas = new JList();
		listaPersonas.setBounds(70, 52, 500, 162);
		add(listaPersonas);
		
		txtNombreModificar = new JTextField();
		txtNombreModificar.setBounds(70, 238, 116, 22);
		add(txtNombreModificar);
		txtNombreModificar.setColumns(10);
		
		txtApellidoModificar = new JTextField();
		txtApellidoModificar.setBounds(198, 238, 116, 22);
		add(txtApellidoModificar);
		txtApellidoModificar.setColumns(10);
		
		txtDniModificar = new JTextField();
		txtDniModificar.setBounds(326, 238, 116, 22);
		add(txtDniModificar);
		txtDniModificar.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(454, 237, 116, 25);
		add(btnModificar);

	}

	public JTextField getTxtNombreModificar() {
		return txtNombreModificar;
	}

	public void setTxtNombreModificar(JTextField txtNombreModificar) {
		this.txtNombreModificar = txtNombreModificar;
	}

	public JTextField getTxtApellidoModificar() {
		return txtApellidoModificar;
	}

	public void setTxtApellidoModificar(JTextField txtApellidoModificar) {
		this.txtApellidoModificar = txtApellidoModificar;
	}

	public JTextField getTxtDniModificar() {
		return txtDniModificar;
	}

	public void setTxtDniModificar(JTextField txtDniModificar) {
		this.txtDniModificar = txtDniModificar;
	}

	public JList getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(JList listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	
	
	
	

}
