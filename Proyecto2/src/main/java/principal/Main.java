
package principal;

import java.sql.Connection;


public class Main {
    
    public static void main(String[] args) {
        
        Conexion clase_conexion = new Conexion("jdbc:mysql://localhost:3306/Proyecto1", "root", "mariobros99");
        Connection con = clase_conexion.getConnection();
        if(clase_conexion.isConexion_Exitosa() == true){
            System.out.println("Exito");
        } else{
            System.out.println("False");
        }
    }
}
