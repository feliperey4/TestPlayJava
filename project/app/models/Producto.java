/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import play.db.ebean.Model;

/**
 *
 * @author reyherh
 */
@Entity
public class Producto extends Model{
    
    @Id
    @GeneratedValue
    public String idProducto;
    
    @Column(unique=true)
    public String nombre;
    
    @Column(columnDefinition="varchar(500)")
    public String descripcion;
    
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "producto_insumo",
            joinColumns = @JoinColumn ( name = "ID_PRODUCTO" ,  referencedColumnName = "ID_PRODUCTO" ), 
      inverseJoinColumns = @JoinColumn ( name = "ID_INSUMO" ,  referencedColumnName = "ID_INSUMO" ))
    public List<Insumo> insumos;

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", insumos=" + insumos.toString() + '}';
    }
    
    
    
}
