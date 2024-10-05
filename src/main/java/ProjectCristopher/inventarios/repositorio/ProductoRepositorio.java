package ProjectCristopher.inventarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ProjectCristopher.inventarios.modelo.Producto;
public interface ProductoRepositorio extends JpaRepository<Producto,Integer> {
}
