package principal;

import acciones_funciones.DM_Cita;
import acciones_funciones.DM_Consulta;
import acciones_funciones.DM_TipoExamen;
import acciones_funciones.DM_Informe;
import acciones_funciones.DM_Resultado;
import acciones_usuarios.DM_Administrador;
import acciones_usuarios.DM_Laboratorista;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import funciones.Cita;
import funciones.Consulta;
import funciones.Tipo_Examen;
import funciones.Informe;
import funciones.Resultado;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
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
    private DM_Cita dmcit = new DM_Cita();
    private DM_Consulta dmcon = new DM_Consulta();
    private DM_TipoExamen dmtipoexame = new DM_TipoExamen();
    private DM_Informe dminf = new DM_Informe();
    private DM_Resultado dmres = new DM_Resultado();
    private ArrayList<String> mesajes = new ArrayList<>();

    public Carga() {
    }

    public ArrayList<String> getMesajes() {
        return mesajes;
    }

    public void setMesajes(ArrayList<String> mesajes) {
        this.mesajes = mesajes;
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
                    String msj = dmadmin.AgregarAdministrador(administrador);
                    mesajes.add(msj);
                }
            }

            NodeList consultas = document.getElementsByTagName("consulta");

            for (int i = 0; i < consultas.getLength(); i++) {
                Node nodo = consultas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String tipo = element.getElementsByTagName("TIPO").item(0).getTextContent();
                    double costo = Double.parseDouble(element.getElementsByTagName("COSTO").item(0).getTextContent());
                    Consulta consulta = new Consulta(tipo, costo);
                    String msj = dmcon.AgregarConsulta(consulta);
                    mesajes.add(msj);
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
                    String msj = dmmed.AgregarMedico(medico);
                    mesajes.add(msj);
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
                    String msj = dmlab.AñadirLaboratorista(laboratorista);
                    mesajes.add(msj);
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
                    String msj = dmpac.AgregarPaciente(paciente);
                    mesajes.add(msj);
                }
            }

            NodeList examenes = document.getElementsByTagName("examen");

            for (int i = 0; i < examenes.getLength(); i++) {
                Node nodo = examenes.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String orden = element.getElementsByTagName("ORDEN").item(0).getTextContent().toLowerCase();
                    String descripcion = element.getElementsByTagName("DESCRIPCION").item(0).getTextContent();
                    double costo = Double.parseDouble(element.getElementsByTagName("COSTO").item(0).getTextContent());
                    String informe = element.getElementsByTagName("INFORME").item(0).getTextContent();
                    Tipo_Examen examen = new Tipo_Examen(codigo, nombre, Boolean.valueOf(orden), descripcion, costo, informe);
                    String msj = dmtipoexame.AgregarExamen(examen);
                    mesajes.add(msj);
                }
            }

            NodeList informes = document.getElementsByTagName("reporte");

            for (int i = 0; i < informes.getLength(); i++) {
                Node nodo = informes.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String codigo_paciente = element.getElementsByTagName("PACIENTE").item(0).getTextContent();
                    String codigo_medico = element.getElementsByTagName("MEDICO").item(0).getTextContent();
                    String descripcion = element.getElementsByTagName("INFORME").item(0).getTextContent();
                    Date fecha = Date.valueOf(element.getElementsByTagName("FECHA").item(0).getTextContent());
                    String hora = element.getElementsByTagName("HORA").item(0).getTextContent();
                    Informe informe = new Informe(codigo, codigo_paciente, codigo_medico, descripcion, fecha, hora);
                    String msj = dminf.AgregarInforme(informe);
                    mesajes.add(msj);
                }
            }

            NodeList resultados = document.getElementsByTagName("resultado");

            for (int i = 0; i < resultados.getLength(); i++) {
                Node nodo = resultados.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String codigo_paciente = element.getElementsByTagName("PACIENTE").item(0).getTextContent();
                    String codigo_examen = element.getElementsByTagName("EXAMEN").item(0).getTextContent();
                    String codigo_laboratorista = element.getElementsByTagName("LABORATORISTA").item(0).getTextContent();
                    String orden = element.getElementsByTagName("ORDEN").item(0).getTextContent();
                    String informe = element.getElementsByTagName("INFORME").item(0).getTextContent();
                    Date fecha = Date.valueOf(element.getElementsByTagName("FECHA").item(0).getTextContent());
                    String hora = element.getElementsByTagName("HORA").item(0).getTextContent();
                    Resultado resultado = new Resultado(codigo, codigo_paciente, codigo_examen, codigo_laboratorista, orden, informe, fecha, hora);
                    String msj = dmres.AgregarResultado(resultado);
                    mesajes.add(msj);
                }
            }

            NodeList citas = document.getElementsByTagName("cita");

            for (int i = 0; i < citas.getLength(); i++) {
                Node nodo = citas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    String codigo = element.getElementsByTagName("CODIGO").item(0).getTextContent();
                    String codigo_paciente = element.getElementsByTagName("PACIENTE").item(0).getTextContent();
                    String codigo_medico = element.getElementsByTagName("MEDICO").item(0).getTextContent();
                    Date fecha = Date.valueOf(element.getElementsByTagName("FECHA").item(0).getTextContent());
                    String hora = element.getElementsByTagName("HORA").item(0).getTextContent();
                    Cita cita = new Cita(codigo, codigo_paciente, codigo_medico, fecha, hora);
                    String msj = dmcit.AgregarCita(cita);
                    mesajes.add(msj);
                }
            }

            mensaje = "Datos agregados";
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }

    public String CargarOrden(String tipo) {
        String mensaje = null;
        try {
            JFileChooser seleccionar = new JFileChooser();
            final FileNameExtensionFilter filtro_pdf = new FileNameExtensionFilter("Archivo PDF", "pdf");
            final FileNameExtensionFilter filtro_img = new FileNameExtensionFilter("Archivo IMG", "jpg", "png");
            if ("PDF".equals(tipo)) {
                seleccionar.setFileFilter(filtro_pdf);
            } else if ("IMG".equals(tipo)) {
                seleccionar.setFileFilter(filtro_img);
            }

            int seleccion = seleccionar.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = seleccionar.getSelectedFile();
                if ("PDF".equals(tipo)) {
                    if (fichero.getName().endsWith(".pdf")) {
                        mensaje = fichero.getPath();
                    } else {
                        mensaje = "Archivo incorrecto";
                    }
                }
                if ("IMG".equals(tipo)) {
                    if (fichero.getName().endsWith(".jpg") || fichero.getName().endsWith(".png")) {
                        mensaje = fichero.getPath();
                    } else {
                        mensaje = "Archivo incorrecto";
                    }
                }
            } else {
                mensaje = "No se cargo ningun archivo";
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    
    
}
