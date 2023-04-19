package tingeso.milkstgo;

import tingeso.milkstgo.entities.LaboratorioEntity;
import tingeso.milkstgo.entities.ProveedorEntity;
import tingeso.milkstgo.services.LaboratorioService;
import tingeso.milkstgo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LaboratorioTest {
    @Autowired
    LaboratorioService laboratorioService;
    @Autowired
    ProveedorService proveedorService;

    @Test
    void GrasaByProveedorTest() {
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentajeGrasa(24.0);
        laboratorio.setPorcentajeSolidosTotales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals(24, laboratorioService.porcentajeGrasaByProveedor(proveedor.getIdProveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void PorcentajeSolidosByProveedorTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentajeGrasa(24.0);
        laboratorio.setPorcentajeSolidosTotales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals(31, laboratorioService.porcentajeSolidosByProveedor(proveedor.getIdProveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void getRetencionByProveedor(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentajeGrasa(24.0);
        laboratorio.setPorcentajeSolidosTotales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals("Si", laboratorioService.getRetencionByProveedor(proveedor.getIdProveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void getNombreByProveedorTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentajeGrasa(24.0);
        laboratorio.setPorcentajeSolidosTotales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals("ALCIDES QUISPE", laboratorioService.getNombreByProveedor(proveedor.getIdProveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void getCategoriaByProveedorTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentajeGrasa(24.0);
        laboratorio.setPorcentajeSolidosTotales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        assertEquals("A", laboratorioService.getCategoriaByProveedor(proveedor.getIdProveedor()));
        laboratorioService.eliminarData(laboratorio);
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void obtenerDataTest(){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedor.setCategoria("A");
        proveedorService.guardarData(proveedor);
        LaboratorioEntity laboratorio = new LaboratorioEntity();
        laboratorio.setPorcentajeGrasa(24.0);
        laboratorio.setPorcentajeSolidosTotales(31.0);
        laboratorio.setProveedor(proveedor);
        laboratorioService.guardarData(laboratorio);
        ProveedorEntity proveedor1 = new ProveedorEntity();
        proveedor1.setIdProveedor(1004L);
        proveedor1.setNombre("PABLO ROMAN");
        proveedor1.setRetencion("No");
        proveedor1.setCategoria("D");
        proveedorService.guardarData(proveedor1);
        LaboratorioEntity laboratorio1 = new LaboratorioEntity();
        laboratorio1.setPorcentajeGrasa(12.0);
        laboratorio1.setPorcentajeSolidosTotales(17.0);
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
        proveedor.setIdProveedor(1003L);
        proveedor.setNombre("ALCIDES QUISPE");
        proveedor.setRetencion("Si");
        proveedorService.guardarData(proveedor);
        laboratorioService.guardarDataDB(proveedor, "12.0", "17.0");
        assertEquals(1, laboratorioService.obtenerData().size());
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(proveedor.getIdProveedor()));
    }
}
