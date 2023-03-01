package org.utl.dsm.crud.db;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Israel
 */
public class ConexionMongoDB {
 DB BaseDatos;
    DBCollection coleccion;
    BasicDBObject document = new BasicDBObject();
    
    public ConexionMongoDB(){
    try{
    Mongo mongo = new Mongo("localhost",27017);
    BaseDatos = mongo.getDB("acciones");
    coleccion = BaseDatos.getCollection("persona");
    System.out.println("Conexion Exitosa :)");
    }catch(Exception ex){
    Logger.getLogger(ConexionMongoDB.class.getName()).log(Level.SEVERE,null,ex);
      }
    }
    // Metodos CRUD
    // INSERTAR
    public boolean insertar(String accion){
        document.put("accion",accion);
        coleccion.insert(document);
        return true;
    }
    // MOSTRAR
    public void Mostrar(){
        DBCursor cursor = coleccion.find();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
    //Actualizar
    public boolean Actualizar(String accionVieja,String accionNueva){
        document.put("accion",accionVieja);
        BasicDBObject documentoNuevo = new BasicDBObject();
        documentoNuevo.put("accion",accionNueva);
        coleccion.findAndModify(document,documentoNuevo);
        return true;
    }
    // ELIMINAR
    public boolean eliminar(String accion){
        document.put("accion",accion);
        coleccion.remove(document);
        return true;
    }   
}