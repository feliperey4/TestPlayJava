/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

/**
 *
 * @author reyherh
 */
@Entity
public class Venta extends Model{
    
    @Id
    @GeneratedValue
    public String idVenta;
    
    public String comprador;
    
    public Integer cantidad;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_producto")
    public Producto producto;  
    
}
