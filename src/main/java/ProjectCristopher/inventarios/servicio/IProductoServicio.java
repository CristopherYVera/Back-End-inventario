package ProjectCristopher.inventarios.servicio;

import ProjectCristopher.inventarios.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarProductos();
    public void guardarProducto(Producto producto);
    public Producto buscarProductoPorId(Integer idProducto);
    public void eliminarProducto(Integer idProducto);
}
