package tingeso.milkstgo;

import tingeso.milkstgo.entities.ProveedorEntity;
import tingeso.milkstgo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProveedorTest {
    @Autowired
    ProveedorService proveedorService;
    @Test
    void guardarProveedorTest(){
        proveedorService.guardarProveedor(1003L, "ALCIDES QUISPE", "A", "Si");
        ProveedorEntity proveedor = proveedorService.findByCodigo(1003L);
        assertEquals("ALCIDES QUISPE", proveedor.getNombre());
        proveedorService.eliminarData(proveedor);
    }
    @Test
    void obtenerProveedoresTest(){
        proveedorService.eliminarTodo();
        proveedorService.guardarProveedor(1003L, "ALCIDES QUISPE", "A", "Si");
        proveedorService.guardarProveedor(1004L, "MARTA GONZALEZ", "B", "Si");
        proveedorService.guardarProveedor(1005L, "LUIS PEREZ", "C", "No");
        proveedorService.guardarProveedor(1006L, "ANA LOPEZ", "A", "Si");
        proveedorService.guardarProveedor(1008L, "CARLOS MARTINEZ", "D", "Si");
        assertEquals(5, proveedorService.obtenerProveedores().size());
        proveedorService.eliminarData(proveedorService.findByCodigo(1003L));
    }
    @Test
void eliminarProveedorTest(){
        proveedorService.guardarProveedor(1003L, "ALCIDES QUISPE", "A", "Si");
        proveedorService.eliminarTodo();
        assertEquals(0, proveedorService.obtenerProveedores().size());
    }
}
