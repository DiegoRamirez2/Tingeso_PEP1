package Tingeso.MilkStgo.Services;

import Tingeso.MilkStgo.Entities.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Tingeso.MilkStgo.Repositories.ProveedorRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public void guardarProveedor(Long id_proveedor, String nombre, String categoria, String retencion){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(id_proveedor);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedor.setRetencion(retencion);
        proveedorRepository.save(proveedor);
    }
    public ArrayList<ProveedorEntity> obtenerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();

    }

    public ProveedorEntity findByCodigo(String id_proveedor) {
        ProveedorEntity proveedor = proveedorRepository.findByIdProveedor(Long.parseLong(id_proveedor));
        if(proveedor == null)
            System.out.println("No se encontro el proveedor");
        return proveedor;
    }
}
