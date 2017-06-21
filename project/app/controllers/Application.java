package controllers;

import java.util.List;
import models.Insumo;
import models.Producto;
import models.Venta;
import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.toJson;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    /////////////////////////////////
    // Vistas
    /////////////////////////////////
    public static Result productos() {
        return ok(productos.render());
    }
    
    public static Result insumos() {
        return ok(insumos.render());
    }
    
    public static Result ventas() {
        return ok(ventas.render());
    }
    
    /*
    Funcion para retornar la lista de los productos disponibles.
    */
    public static Result getProductos(){
        List<Producto> productos = new Model.Finder(String.class, Producto.class).all();
        return ok(toJson(productos));
    }
    
    /*
    Funcion para agregar un producto.
    */
    public static Result addProducto(){
        Producto producto= Form.form(Producto.class).bindFromRequest().get();
        producto.save();
        return ok();
    }
    
    /*
    Funcion para eliminar un producto.
    */
    public static Result deleteProducto(){
        Producto producto= Form.form(Producto.class).bindFromRequest().get();
        producto.delete();
        return ok();
    }

    /*
    Funcion para modificar un producto.
    */
    public static Result editProducto(){
        Producto producto= Form.form(Producto.class).bindFromRequest().get();
        producto.update();
        return ok();
    }
    
    /*
    Funcion para retornar la lista de los insumos disponibles.
    */
    public static Result getInsumos(){
        List<Insumo> insumos = new Model.Finder(String.class, Insumo.class).all();
        return ok(toJson(insumos));
    }
    
    /*
    Funcion para agregar un insumo.
    */
    public static Result addInsumo(){
        Insumo insumo= Form.form(Insumo.class).bindFromRequest().get();
        insumo.save();
        return ok();
    }
    
    /*
    Funcion para eliminar un insumo.
    */
    public static Result deleteInsumo(){
        Insumo insumo= Form.form(Insumo.class).bindFromRequest().get();
        insumo.delete();
        return ok();
    }

    /*
    Funcion para modificar un insumo.
    */
    public static Result editInsumo(){
        Insumo insumo= Form.form(Insumo.class).bindFromRequest().get();
        insumo.update();
        return ok();
    }
    
    /*
    Funcion para retornar la lista de las ventas.
    */
    public static Result getVentas(){
        List<Venta> venta = new Model.Finder(String.class, Venta.class).all();
        return ok(toJson(venta));
    }
    
    /*
    Funcion para agregar una venta.
    */
    public static Result addVenta(){
        Venta venta= Form.form(Venta.class).bindFromRequest().get();

        for(Insumo insumo:venta.producto.insumos){
            insumo.stock-=venta.cantidad;
            insumo.update();
        }
        venta.save(); 
        return ok();
    }
}
