var controllers = angular.module('controllers', []);

controllers.controller('VentaCtrl', ['$scope','$http', function ($scope,$http) {
    var vm=this;
    vm.producto={idProducto:null,
                        nombre:"",
                        descripcion:"",
                        insumos:[]};
    vm.venta={idVenta:null,comprador:"",cantidad:"",producto:vm.producto};

    getProductos();
    getVentas()

    function getProductos() {
      $http.get("productos")
      .then(function(response) {
          vm.productos = response.data;
      });
    }

    function getVentas() {
      $http.get("ventas")
      .then(function(response) {
          vm.ventas = response.data;
      });
    }

    ////////////////////////////////////
    // Funciones de botones.
    ////////////////////////////////////

    vm.addVenta=function() {
        // Validacion de campos vacios.
        if(vm.venta.comprador==='' || vm.venta.cantidad<0 || vm.comboProducto<1){
            alert("Los campos no pueden estar vacios.");
            return;
        }
        
        //Valida cantidad de insumos.
        vm.venta.producto=vm.productos[vm.comboProducto-1];
        for(i in vm.venta.producto.insumos){
            if(vm.venta.producto.insumos[i].stock-vm.venta.cantidad<0){
                alert("Insumos insuficientes.");
                return;
            }
        }
        
        $http.post("venta",vm.venta)
        .then(function(response) {
            vm.cancelar();
        });
      }
    
    vm.cancelar=function() {
        vm.producto={idProducto:null,
                        nombre:"",
                        descripcion:"",
                        insumos:[]};
        vm.venta={idVenta:null,comprador:"",cantidad:"",producto:vm.producto};

        getVentas()
      }

}]);

controllers.controller('InsumoCtrl', ['$scope','$http', function ($scope,$http) {
    var vm=this;

    // Modelos
    vm.modo="normal";
    vm.insumo={idInsumo:null,
                      nombre:"",
                      stock:""};

    getInsumos();

    function getInsumos() {
            $http.get("insumos")
            .then(function(response) {
                vm.insumos = response.data;
            });
      }

    ////////////////////////////////////
    // Funciones de botones.
    ////////////////////////////////////

    vm.addInsumo=function() {
        // Validacion de campos vacios.
        if(vm.insumo.nombre==='' || vm.insumo.stock<0){
            alert("Los campos no pueden estar vacios.");
            return;
        }
        
        $http.post("insumo",vm.insumo)
        .then(function(response) {
            vm.cancelar();
        });
      }
    
    vm.cancelar=function() {
        vm.insumo={idInsumo:null,
                      nombre:"",
                      stock:""};
        vm.modo="normal";
        getInsumos();
      }

    vm.editInsumo=function(ins) {
        vm.insumo=ins;
        vm.modo="edit";
      }

    vm.updateInsumo=function() {
        // Validacion de campos vacios.
        if(vm.insumo.nombre==='' || vm.insumo.stock<0){
            alert("Los campos no pueden estar vacios.");
            return;
        }
        
        $http.post("editInsumo",vm.insumo)
        .then(function(response) {
            vm.cancelar();
        });
      }

    vm.deleteProducto=function(ins) {     
        $http.post("deleteInsumo",ins)
        .then(function(response) {
            vm.cancelar();
        });
      }

}]);


controllers.controller('ProductoCtrl', ['$scope','$http', function ($scope,$http) {
      
      var vm=this;

      // Modelos
      vm.modo="normal";
      vm.producto={idProducto:null,
                        nombre:"",
                        descripcion:"",
                        insumos:[]};

      // Carga inicial de modelos.                  
      getProductos();
      getInsumos();
      
      function getProductos() {
        $http.get("productos")
        .then(function(response) {
            vm.productos = response.data;
        });
      }

      function getInsumos() {
        $http.get("insumos")
        .then(function(response) {
            vm.insumos = response.data;
            vm.insumosAux = response.data;
        });
      }

      ////////////////////////////////////
      // Funciones de botones.
      ////////////////////////////////////
      vm.addProducto=function() {
        // Validacion de campos vacios.
        if(vm.producto.nombre==='' || vm.producto.descripcion===''){
            alert("Los campos no pueden estar vacios.");
            return;
        }
        
        $http.post("producto",vm.producto)
        .then(function(response) {
            vm.cancelarProducto();
        });
      }

      vm.updateProducto=function() {
        // Validacion de campos vacios.
        if(vm.producto.nombre==='' || vm.producto.descripcion===''){
            alert("Los campos no pueden estar vacios.");
            return;
        }
        
        $http.post("editProducto",vm.producto)
        .then(function(response) {
            vm.cancelarProducto();
        });
      }
      
      vm.deleteProducto=function(prod) {
        $http.post("deleteProducto",prod)
        .then(function(response) {
            getProductos();
        });
      }
      
      vm.editProducto=function(prod) {
        vm.producto=prod;
        var aux=vm.insumosAux;
        for(insu in prod.insumos){
            aux = aux.filter(function(item) { 
                    return item.idInsumo !== prod.insumos[insu].idInsumo;
                });
        }
        vm.insumos=aux;
        vm.modo="edit";
      }

      vm.cancelarProducto=function() {
        vm.producto={idProducto:null,
                        nombre:"",
                        descripcion:"",
                        insumos:[]};
        vm.modo="normal";
        vm.insumos=vm.insumosAux;
        getProductos();
        getInsumos();
      }
      
      
      // Tabla de insumos
      vm.addInsumoProducto=function(insumo){
        vm.insumos= vm.insumos.filter(function(item) { 
                    return item.idInsumo !== insumo.idInsumo;
                });
        vm.producto.insumos.push(insumo);
      }
      
      vm.deleteInsumoProducto=function(insumo){
        vm.producto.insumos= vm.producto.insumos.filter(function(item) { 
                    return item.idInsumo !== insumo.idInsumo;
                });
        vm.insumos.push(insumo);
      }
}]);