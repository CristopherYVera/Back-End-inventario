package ProjectCristopher.inventarios.controlador;

import ProjectCristopher.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import ProjectCristopher.inventarios.modelo.Producto;
import ProjectCristopher.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//http://localhost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProdcutoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProdcutoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    //http://localhost:8080/inventario-app/productos
    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    //http://localhost:8080/inventario-app/productos
    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar: " + producto);
        return this.productoServicio.guardarProducto(producto);
    }

    //http://localhost:8080/inventario-app/productos{id}
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if (producto != null){
            return ResponseEntity.ok(producto);
        }
        else {
           throw  new RecursoNoEncontradoExcepcion("No se encontro el producto");
        }
    }

    //http://localhost:8080/inventario-app/productos{id}
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id,@RequestBody Producto productoRecibido){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setExistencia(productoRecibido.getExistencia());
        producto.setPrecio(productoRecibido.getPrecio());
        this.productoServicio.guardarProducto(producto);

        return ResponseEntity.ok(producto);
    }

    //http://localhost:8080/inventario-app/productos{id}
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarProducto(@PathVariable int id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if (producto == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id" +id);
        }
        else {
            this.productoServicio.eliminarProducto(producto.getIdProducto());
            Map<String,Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado",Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }
}
