package principal;

import acciones_usuarios.DM_Administrador;
import acciones_usuarios.DM_Laboratorista;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import java.io.File;
import java.sql.Date;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import usuarios.Administrador;
import usuarios.Laboratorista;
import usuarios.Medico;
import usuarios.Paciente;

public class Carga {

    private DM_Administrador dmadmin = new DM_Administrador();
    private DM_Laboratorista dmlab = new DM_Laboratorista();
    private DM_Medico dmmed = new DM_Medico();
    private DM_Paciente dmpac = new DM_Paciente();

    public Carga() {
    }

    public String CargarDatos() {
        String mensaje = null;
        try {
            JFileChooser seleccionar = new JFileChooser();
            final FileNameExtensionFilter filtro_xml = new FileNameExtensionFilter("Archivo xml", "xml");
            seleccionar.setFileFilter(filtro_xml);
            int seleccion = seleccionar.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = seleccionar.getSelectedFile();
                if (fichero.getName().endsWith(".xml")) {
                    mensaje = IngresarDatos(fichero);
                } else {
                    mensaje = "Archivo incorrecto";
                }

            } else {
                mensaje = "No se cargo ningun archivo";
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }

    public String IngresarDatos(File fichero) {
        String mensaje = null;
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(fichero);

            document.getDocumentElement().normalize();

            NodeList administradores = document.getElementsByTagName("admin");

            for (int i = 0; i < administradores.getLength(); i++) {
                Node nodo = administradores.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String contraseña = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    Administrador administrador = new Administrador(codigo, nombre, dpi, contraseña);
                    dmadmin.AgregarAdministrador(administrador);
                }
            }

            NodeList doctores = document.getElementsByTagName("doctor");

            for (int i = 0; i < doctores.getLength(); i++) {
                Node nodo = doctores.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    int numero_colegiado = Integer.parseInt(element.getElementsByTagName("COLEGIADO").item(0).getTextContent());
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();
                    int telefono = Integer.parseInt(element.getElementsByTagName("TELEFONO").item(0).getTextContent());
                    String correo = element.getElementsByTagName("CORREO").item(0).getTextContent();
                    String hora_entrada = element.getElementsByTagName("INICIO").item(0).getTextContent();
                    String hora_salida = element.getElementsByTagName("FIN").item(0).getTextContent();
                    Date fecha_inicio = Date.valueOf(element.getElementsByTagName("TRABAJO").item(0).getTextContent());
                    String contraseña = element.getElementsByTagName("PASSWORD").item(0).getTextContent();

                    Medico medico = new Medico(codigo, nombre, numero_colegiado, dpi, telefono, correo, hora_entrada, hora_salida, fecha_inicio, contraseña);

                    Node titulo = element.getElementsByTagName("ESPECIALIDAD").item(0).getFirstChild();
                    while (titulo != null) {
                        titulo = titulo.getNextSibling();
                        if (titulo != null) {
                            if (titulo.getNodeType() == Node.ELEMENT_NODE) {
                                String especialidad = titulo.getTextContent();
                                medico.AñadirEspecialidad(especialidad);
                            }
                        }
                    }
                    dmmed.AgregarMedico(medico);
                    System.out.println("");
                }

            }

            NodeList laboratoristas = document.getElementsByTagName("laboratorista");

            for (int i = 0; i < laboratoristas.getLength(); i++) {
                Node nodo = laboratoristas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String numero_registro = element.getElementsByTagName("REGISTRO").item(0).getTextContent();
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();
                    int telefono = Integer.parseInt(element.getElementsByTagName("TELEFONO").item(0).getTextContent());
                    String tipo_examen = element.getElementsByTagName("EXAMEN").item(0).getTextContent();
                    String correo = element.getElementsByTagName("CORREO").item(0).getTextContent();
                    Date fecha_inicio = Date.valueOf(element.getElementsByTagName("DIATRABAJO").item(0).getTextContent());
                    String contraseña = element.getElementsByTagName("PASSWORD").item(0).getTextContent();

                    Laboratorista laboratorista = new Laboratorista(codigo, nombre, numero_registro, dpi, telefono, tipo_examen, correo, fecha_inicio, contraseña);

                    Node dia = element.getElementsByTagName("TRABAJO").item(0).getFirstChild();
                    while (dia != null) {
                        dia = dia.getNextSibling();
                        if (dia != null) {
                            if (dia.getNodeType() == Node.ELEMENT_NODE) {
                                String dia_laboral = dia.getTextContent();
                                laboratorista.AñadirDiaDeTrabajo(dia_laboral);
                            }
                        }
                    }
                    dmlab.AñadirLaboratorista(laboratorista);
                }
            }

            NodeList pacientes = document.getElementsByTagName("paciente");

            for (int i = 0; i < pacientes.getLength(); i++) {
                Node nodo = pacientes.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String sexo = element.getElementsByTagName("SEXO").item(0).getTextContent();
                    Date fecha_de_nacimiento = Date.valueOf(element.getElementsByTagName("BIRTH").item(0).getTextContent());
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();
                    int telefono = Integer.parseInt(element.getElementsByTagName("TELEFONO").item(0).getTextContent());
                    double peso = Double.parseDouble(element.getElementsByTagName("PESO").item(0).getTextContent());
                    String tipo_sangre = element.getElementsByTagName("SANGRE").item(0).getTextContent();
                    String correo = element.getElementsByTagName("CORREO").item(0).getTextContent();
                    String contraseña = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    Paciente paciente = new Paciente(codigo, nombre, sexo, fecha_de_nacimiento, dpi, telefono, peso, tipo_sangre, correo, contraseña);
                    dmpac.AgregarPaciente(paciente);
                }
            }
            mensaje = "Datos agregados";
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
}
