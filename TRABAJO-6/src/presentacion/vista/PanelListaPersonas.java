package presentacion.vista;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;

public class PanelListaPersonas extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	/**
	 * Create the panel.
	 */
	public PanelListaPersonas() {
		setLayout(null);
		String[] columnNames = {"Nombre", "Apellido", "DNI"};
		
		tableModel = new DefaultTableModel(columnNames, 0);
		
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 58, 382, 174);
		add(scrollPane);

	}
	
	public void cargarDatos(List<Persona> personas) {
	    // Limpiar el modelo de la tabla antes de cargar nuevos datos
	    tableModel.setRowCount(0);
	    
	    for (Persona persona : personas) {
	        // Agregar cada persona como una nueva fila en la tabla
	        tableModel.addRow(new Object[]{persona.getNombre(), persona.getApellido(), persona.getDni()});
	    }
	}
}
