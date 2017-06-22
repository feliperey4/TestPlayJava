package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Insumo;
import models.Producto;
import models.Venta;
import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.toJson;
import play.mvc.*;
import play.Logger.ALogger;
import play.Logger;
import utils.MsjLogs;

import views.html.*;

public class Application extends Controller {
    
   
    
    
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    /////////////////////////////////
    // Vistas
    /////////////////////////////////
    public static Result productos() {
        Logger.debug(MsjLogs.buildMsj(MsjLogs.OPEN_VIEW, "productos"));
        return ok(productos.render());
    }
    
    public static Result insumos() {
        Logger.debug(MsjLogs.buildMsj(MsjLogs.OPEN_VIEW, "insumos"));
        return ok(insumos.render());
    }
    
    public static Result ventas() {
        Logger.debug(MsjLogs.buildMsj(MsjLogs.OPEN_VIEW, "ventas"));
        return ok(ventas.render());
    }
    
    /*
    Funcion para retornar la lista de los productos disponibles.
    */
    public static Result getProductos(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "getProductos"));
        List<Producto> productos = new ArrayList<Producto>();
        try{
            productos = new Model.Finder(String.class, Producto.class).all();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "getProductos"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "getProductos"),e);
        }
        return ok(toJson(productos));
    }
    
    /*
    Funcion para agregar un producto.
    */
    public static Result addProducto(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "addProductos"));
        try{
            Producto producto= Form.form(Producto.class).bindFromRequest().get();
            producto.save();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "addProductos"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "addProductos"),e);
        }
        return ok();
    }
    
    /*
    Funcion para eliminar un producto.
    */
    public static Result deleteProducto(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "deleteProductos"));
        try{
            Producto producto= Form.form(Producto.class).bindFromRequest().get();
            producto.delete();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "deleteProductos"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "deleteProducto"),e);
        }
        return ok();
    }

    /*
    Funcion para modificar un producto.
    */
    public static Result editProducto(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "editProductos"));
        try{
            Producto producto= Form.form(Producto.class).bindFromRequest().get();
            producto.update();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "editProductos"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "editProducto"),e);
        }
        return ok();
    }
    
    /*
    Funcion para retornar la lista de los insumos disponibles.
    */
    public static Result getInsumos(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "getInsumos"));
        List<Insumo> insumos = new ArrayList<Insumo>();
        try{
            insumos = new Model.Finder(String.class, Insumo.class).all();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "getInsumos"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "getInsumos"),e);
        }
        return ok(toJson(insumos));
    }
    
    /*
    Funcion para agregar un insumo.
    */
    public static Result addInsumo(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "addInsumo"));
        try{
            Insumo insumo= Form.form(Insumo.class).bindFromRequest().get();
            insumo.save();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "addInsumo"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "addInsumo"),e);
        }
        return ok();
    }
    
    /*
    Funcion para eliminar un insumo.
    */
    public static Result deleteInsumo(){
        try{
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "deleteInsumo"));
            Insumo insumo= Form.form(Insumo.class).bindFromRequest().get();
            insumo.delete();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "deleteInsumo"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "deleteInsumo"),e);
        }
        return ok();
    }

    /*
    Funcion para modificar un insumo.
    */
    public static Result editInsumo(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "editInsumo"));
        try{
            Insumo insumo= Form.form(Insumo.class).bindFromRequest().get();
            insumo.update();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "editInsumo"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "editInsumo"),e);
        }
        return ok();
    }
    
    /*
    Funcion para retornar la lista de las ventas.
    */
    public static Result getVentas(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "getVentas"));
        List<Venta> venta = new ArrayList<Venta>();
        try{
            venta = new Model.Finder(String.class, Venta.class).all();
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "getVentas"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "getVentas"),e);
        }
        return ok(toJson(venta));
    }
    
    /*
    Funcion para agregar una venta.
    */
    public static Result addVenta(){
        Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_STARTED, "addVenta"));
        try{
            Venta venta= Form.form(Venta.class).bindFromRequest().get();

            for(Insumo insumo:venta.producto.insumos){
                insumo.stock-=venta.cantidad;
                insumo.update();
            }
            venta.save(); 
            Logger.debug(MsjLogs.buildMsj(MsjLogs.SERVICE_END_OK, "addVenta"));
        }catch(Exception e){
            Logger.error(MsjLogs.buildMsj(MsjLogs.SERVICE_END_ERROR, "addVenta"),e);
        }
        return ok();
    }
}
