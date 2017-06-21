/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.db.ebean.Model;

/**
 *
 * @author reyherh
 */
@Entity
public class Insumo extends Model{
    
    @Id
    @GeneratedValue
    public String idInsumo;
    
    @Column(unique=true)
    public String nombre;
    
    public Integer stock;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "insumos",cascade = CascadeType.REMOVE)
    public List<Producto> productos;
    
    @Override
    public String toString() {
        return "Insumo{" + "idInsumo=" + idInsumo + ", nombre=" + nombre + ", stock=" + stock + '}';
    }
    
    
   
}
