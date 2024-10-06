	package presentacion.controlador;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.ArrayList;
	import java.util.List;
	
	import javax.swing.JOptionPane;
	
	import entidad.Persona;
	import negocio.PersonaNegocio;
	import presentacion.vista.PanelAgregarPersonas;
	import presentacion.vista.PanelEliminarPersonas;
	import presentacion.vista.PanelListaPersonas;
	import presentacion.vista.PanelModificarPersonas;
	import presentacion.vista.VentanaPrincipal;
	
	public class Controlador implements ActionListener {
		private VentanaPrincipal ventanaPrincipal;
		private PanelAgregarPersonas pnlAgregar;
		private PanelModificarPersonas pnlModificar;
		private PanelEliminarPersonas pnlEliminar;
		private PanelListaPersonas pnlLista;
		private ArrayList<Persona> personasEnTabla;
		private PersonaNegocio pNeg;
		
		
		public Controlador(VentanaPrincipal vista, PersonaNegocio pNego) {
			this.ventanaPrincipal = vista;
			this.pNeg = pNego;
			// INSTANCIAS DE LOS PANELES
			this.pnlAgregar = new PanelAgregarPersonas();
			this.pnlModificar = new PanelModificarPersonas();
			this.pnlEliminar = new PanelEliminarPersonas();
			this.pnlLista = new PanelListaPersonas();
			// EVENTOS DEL MENU
			this.ventanaPrincipal.getMntmAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
			this.ventanaPrincipal.getMntmModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
			this.ventanaPrincipal.getMntmEliminar().addActionListener(a->EventoClickMenu_AbrirPanel_EliminarPersona(a));
			this.ventanaPrincipal.getMntmListar().addActionListener(a->EventoClickMenu_AbrirPanel_ListaPersonas(a));
			
			// EVENTOS DEL PANEL AGREGAR
			this.pnlAgregar.getBtnAceptar().addActionListener(a->EventoClickBoton_AgregarPersona_PanelAgregarPErsona(a));
			
			// EVENTOS DEL PANEL MODIFICAR
			this.pnlModificar.getBtnModificar().addActionListener(a->EventoClickBoton_ModificarPersona_PanelModificarPersona(a));
			this.pnlModificar.getListaPersonas().addListSelectionListener(e -> {
		        if (!e.getValueIsAdjusting()) {
		        	EventoClickBoton_CargarDatosModificarPersona();
		        }
		    });
			
			// EVENTOS DEL PANEL ELIMINAR
			this.pnlEliminar.getBtnEliminar().addActionListener(a->EventoClickBoton_EliminarPersona_PanelEliminarPersona(a));
			
			// EVENTOS DEL PANEL LISTA
		}
		
	
		private void EventoClickBoton_CargarDatosModificarPersona() {
			// CAPTURA LA PERSONA SELECCIONADA
		    String seleccion = (String) pnlModificar.getListaPersonas().getSelectedValue();
	
		    // Obtener el DNI de la selección
		    String dni = seleccion.split(" - ")[1];
	
		    // Buscar la persona seleccionada en la lista
		    Persona personaAModificar = pNeg.readAll().stream()
		        .filter(p -> p.getDni().equals(dni))
		        .findFirst()
		        .orElse(null);
	
	
		    // Cargar los datos de la persona en los campos de texto
		    pnlModificar.getTxtDniModificar().setText(personaAModificar.getDni());
		    pnlModificar.getTxtNombreModificar().setText(personaAModificar.getNombre());
		    pnlModificar.getTxtApellidoModificar().setText(personaAModificar.getApellido());
		    pnlModificar.getTxtDniModificar().setEditable(false);
			
		}
	
	
		private void EventoClickBoton_ModificarPersona_PanelModificarPersona(ActionEvent a) {
			// Capturar los datos actuales del formulario para la modificación
		    String dni = pnlModificar.getTxtDniModificar().getText();
		    String nombre = pnlModificar.getTxtNombreModificar().getText();
		    String apellido = pnlModificar.getTxtApellidoModificar().getText();
	
		    // Crear una nueva persona con los datos actualizados
		    Persona personaModificada = new Persona(dni, nombre, apellido);
	
		    // Intentar realizar la modificación
		    boolean estado = pNeg.edit(personaModificada);
	
		    // Mostrar el mensaje correspondiente
		    if (estado) {
		        JOptionPane.showMessageDialog(null, "Persona modificada con éxito");
		        pnlModificar.getTxtDniModificar().setText("");
		        pnlModificar.getTxtNombreModificar().setText("");
		        pnlModificar.getTxtApellidoModificar().setText("");
		        pnlModificar.getTxtDniModificar().setEditable(false);
		        refrescarListaPersonasModificar(); // Refrescar la lista de personas
		    } else {
		        JOptionPane.showMessageDialog(null, "Error al modificar la persona");
		    }
		}
	
	
		private void EventoClickBoton_EliminarPersona_PanelEliminarPersona(ActionEvent a) {
			String seleccion = (String) pnlEliminar.getListaPersonas().getSelectedValue();
			if (seleccion != null) {
		        String dni = seleccion.split(" - ")[1]; // Extraemos el DNI
		        
		        Persona personaAEliminar = new Persona();
		        personaAEliminar.setDni(dni); // Usamos el DNI como identificador
		        
		        boolean estado = pNeg.delete(personaAEliminar); // Eliminar la persona
		        
		        if (estado) {
		            JOptionPane.showMessageDialog(null, "Persona eliminada con éxito");
		            refrescarListaPersonasEliminar(); // Actualizar la lista después de la eliminación
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar persona");
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar una persona para eliminar");
		    }
		}
	
	
		private void EventoClickBoton_AgregarPersona_PanelAgregarPErsona(ActionEvent a) {
			String nombre = this.pnlAgregar.getTxtNombre().getText();
			String apellido = this.pnlAgregar.getTxtApellido().getText();
			String dni = this.pnlAgregar.getTxtDni().getText();
			
			// VALIDACIONES
			if (!validarNombreApellido(nombre) || !validarNombreApellido(apellido)) {
		        JOptionPane.showMessageDialog(null, "El nombre y apellido deben contener solo letras.");
		        return;
		    }

		    if (!validarDNI(dni)) {
		        JOptionPane.showMessageDialog(null, "El DNI debe contener solo números.");
		        return;
		    }

		    if (!pNeg.esDniUnico(dni)) {
		        JOptionPane.showMessageDialog(null, "El DNI ya está registrado. Debe ser único.");
		        return;
		    }
			
			
			
			Persona nuevaPersona = new Persona(dni, nombre, apellido);
			
			boolean estado = pNeg.insert(nuevaPersona);
	
			if(estado==true) {
				this.pnlAgregar.getTxtNombre().setText("");
				this.pnlAgregar.getTxtApellido().setText("");
				this.pnlAgregar.getTxtDni().setText("");
				JOptionPane.showMessageDialog(null, "Persona agregada con éxito");
			}else {
				JOptionPane.showMessageDialog(null, "Error al agregar persona");
			}
			
			
		}
	
	
		private void EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a) {
			ventanaPrincipal.getContentPane().removeAll();
			ventanaPrincipal.getContentPane().add(pnlAgregar);
			ventanaPrincipal.getContentPane().repaint();
			ventanaPrincipal.getContentPane().revalidate();
			
		}
		private void EventoClickMenu_AbrirPanel_ModificarPersona(ActionEvent a) {
			refrescarListaPersonasModificar();
			
			ventanaPrincipal.getContentPane().removeAll();
			ventanaPrincipal.getContentPane().add(pnlModificar);
			ventanaPrincipal.getContentPane().repaint();
			ventanaPrincipal.getContentPane().revalidate();
		}
		
		private void refrescarListaPersonasModificar() {
			//TRAIGO LOS DATOS DE LA BASE DE DATOS
			List<Persona> personas = pNeg.readAll();
			//TRANSFORMO LOS DATOS EN UN FORMATO ADECUADO
			String[] personasArray = new String[personas.size()];
			for (int i = 0; i < personas.size(); i++) {
				Persona p = personas.get(i);
				personasArray[i] = p.getNombre() + " " + p.getApellido() + " - " + p.getDni();
			}
			pnlModificar.getListaPersonas().setListData(personasArray);		
		}
	
	
		private void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a) {
			
			refrescarListaPersonasEliminar();
			
			ventanaPrincipal.getContentPane().removeAll();
			ventanaPrincipal.getContentPane().add(pnlEliminar);
			ventanaPrincipal.getContentPane().repaint();
			ventanaPrincipal.getContentPane().revalidate();
		}
		private void refrescarListaPersonasEliminar() {
		    //TRAIGO LOS DATOS DE LA BASE DE DATOS
			List<Persona> personas = pNeg.readAll();
		    //TRANSFORMO LOS DATOS EN UN FORMATO ADECUADO
		    String[] personasArray = new String[personas.size()];
		    for (int i = 0; i < personas.size(); i++) {
		        Persona p = personas.get(i);
		        personasArray[i] = p.getNombre() + " " + p.getApellido() + " - " + p.getDni();
		    }
		    pnlEliminar.getListaPersonas().setListData(personasArray);		
		}
	
	
		private void EventoClickMenu_AbrirPanel_ListaPersonas(ActionEvent a) {
			 // Traer los datos de la base de datos
		    List<Persona> personas = pNeg.readAll();
		    // Cargar los datos en el panel
		    pnlLista.cargarDatos(personas);
			
			ventanaPrincipal.getContentPane().removeAll();
			ventanaPrincipal.getContentPane().add(pnlLista);
			ventanaPrincipal.getContentPane().repaint();
			ventanaPrincipal.getContentPane().revalidate();
		}
	
	
		public void inicializar() {
			this.ventanaPrincipal.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		// Método para validar que un nombre o apellido contenga solo letras
		private boolean validarNombreApellido(String texto) {
		    return texto.matches("[a-zA-Z]+");
		}

		// Método para validar que un DNI contenga solo números
		private boolean validarDNI(String dni) {
		    return dni.matches("[0-9]+");
		}
	
	}
