package ProjectCristopher.inventarios.servicio;

import ProjectCristopher.inventarios.modelo.Producto;
import ProjectCristopher.inventarios.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio{

   @Autowired
   private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = this.productoRepositorio.findAll();
        return productos;
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public void guardarProducto(Producto producto) {
        this.productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProducto(Integer idProducto) {
        this.productoRepositorio.deleteById(idProducto);
    }
}
