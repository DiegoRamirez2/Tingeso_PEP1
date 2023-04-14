package Tingeso.MilkStgo;

import Tingeso.MilkStgo.Entities.LaboratorioEntity;
import Tingeso.MilkStgo.Entities.ProveedorEntity;
import Tingeso.MilkStgo.Services.LaboratorioService;
import Tingeso.MilkStgo.Services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LaboratorioTest {
    @Autowired
    LaboratorioService laboratorioService;
    @Autowired
    ProveedorService proveedorService;

    @Test
    void GrasaByProveedorTest() {
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentaje_grasa(24.0);
        laboratorio.setPorcentaje_solidos_totales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals(24, laboratorioService.porcentajeGrasaByProveedor(proveedor.getId_proveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void PorcentajeSolidosByProveedorTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentaje_grasa(24.0);
        laboratorio.setPorcentaje_solidos_totales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals(31, laboratorioService.porcentajeSolidosByProveedor(proveedor.getId_proveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void getRetencionByProveedor(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentaje_grasa(24.0);
        laboratorio.setPorcentaje_solidos_totales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals("Si", laboratorioService.getRetencionByProveedor(proveedor.getId_proveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void getNombreByProveedorTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentaje_grasa(24.0);
        laboratorio.setPorcentaje_solidos_totales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals("ALCIDES QUISPE", laboratorioService.getNombreByProveedor(proveedor.getId_proveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void getCategoriaByProveedorTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentaje_grasa(24.0);
        laboratorio.setPorcentaje_solidos_totales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals("A", laboratorioService.getCategoriaByProveedor(proveedor.getId_proveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void obtenerDataTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentaje_grasa(24.0);
        laboratorio.setPorcentaje_solidos_totales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        ProveedorEntity proveedor1 = new ProveedorEntity();
        proveedor1.setId_proveedor(1004L);
        proveedor1.setNombre("PABLO ROMAN");
        proveedor1.setRetencion("No");
        proveedor1.setCategoria("D");
        proveedorService.guardarData(proveedor1);
        LaboratorioEntity laboratorio1 = new LaboratorioEntity();
        laboratorio1.setPorcentaje_grasa(12.0);
        laboratorio1.setPorcentaje_solidos_totales(17.0);
        laboratorio1.setProveedor(proveedor1);
        laboratorioService.guardarData(laboratorio1);
        assertEquals(2, laboratorioService.obtenerData().size());
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
        laboratorioService.eliminarData(laboratorio1);
        proveedorService.eliminarData(proveedor1);
    }
    @Test
    void guardarDataDBTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setId_proveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedorService.guardarData(proveedor);
        laboratorioService.guardarDataDB(proveedor, "12.0", "17.0");
        assertEquals(1, laboratorioService.obtenerData().size());
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(proveedor.getId_proveedor()));
    }
}
