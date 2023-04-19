package tingeso.milkstgo.services;

import tingeso.milkstgo.entities.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.milkstgo.repositories.ProveedorRepository;

import java.util.List;


@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public void guardarProveedor(Long idProveedor, String nombre, String categoria, String retencion) {
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(idProveedor);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedor.setRetencion(retencion);
        proveedorRepository.save(proveedor);
    }
    public List<ProveedorEntity> obtenerProveedores() {
        return proveedorRepository.findAll();
    }
    public void guardarData(ProveedorEntity proveedor) {
        proveedorRepository.save(proveedor);
    }
    public void eliminarData(ProveedorEntity proveedor) {
        proveedorRepository.delete(proveedor);
    }
    public ProveedorEntity findByCodigo(Long idProveedor) {
        return proveedorRepository.findByIdProveedor(idProveedor);
    }
    public void eliminarTodo() {
        proveedorRepository.deleteAll();
    }
}
