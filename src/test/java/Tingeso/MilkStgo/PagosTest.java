package Tingeso.MilkStgo;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import Tingeso.MilkStgo.Entities.LaboratorioEntity;
import Tingeso.MilkStgo.Entities.PagosEntity;
import Tingeso.MilkStgo.Services.AcopioService;
import Tingeso.MilkStgo.Services.LaboratorioService;
import Tingeso.MilkStgo.Services.PagosService;
import Tingeso.MilkStgo.Services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PagosTest {
    @Autowired
    PagosService pagosService;
    @Autowired
    AcopioService acopioService;
    @Autowired
    LaboratorioService laboratorioService;
    @Autowired
    ProveedorService proveedorService;
    @Test
    void pagoLecheTestA(){
        assertEquals(35000, pagosService.pagoLeche("A", 50));
    }
    @Test
    void pagoLecheTestCasoB(){
        assertEquals(25000, pagosService.pagoLeche("B", 50));
    }
    @Test
    void pagoLecheTestCasoC(){
        assertEquals(20000, pagosService.pagoLeche("C", 50));
    }
    @Test
    void pagoLecheTestCasoD(){
        assertEquals(12500, pagosService.pagoLeche("D", 50));
    }
    @Test
    void pagoLecheTestCasoE(){
        assertEquals(0, pagosService.pagoLeche("E", 50));
    }
    @Test
    void pagoGrasaTest(){
        assertEquals(3000, pagosService.pagoGrasa(15, 100));
    }
    @Test
    void pagoGrasaTestCasoDos(){
        assertEquals(4000, pagosService.pagoGrasa(35, 50));
    }
    @Test
    void pagoGrasaTestCasoTres(){
        assertEquals(9600, pagosService.pagoGrasa(50, 80));
    }
    @Test
    void pagoSolidosTest(){
        assertEquals(-13000, pagosService.pagoSolidos(5, 100));
    }
    @Test
    void pagoSolidosTestCasoDos(){
        assertEquals(-18000, pagosService.pagoSolidos(15, 200));
    }
    @Test
    void pagoSolidosTestCasoTres(){
        assertEquals(28500, pagosService.pagoSolidos(25, 300));
    }
    @Test
    void pagoSolidosTestCasoCuatro(){
        assertEquals(60000, pagosService.pagoSolidos(40, 400));
    }
    @Test
    void bonificacionTestDiaTarde(){
        proveedorService.guardarProveedor(1011L, "QUISPE S. ALCIDES", "A", "Si");
        int dia = 1;
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        for(long i = 0; i < 10; i++){
            LocalDate fecha = LocalDate.of(2023, 4, dia);
            AcopioEntity acopio1 = new AcopioEntity(i+100, "1011", fecha, "M", 45);
            AcopioEntity acopio2 = new AcopioEntity(i+200, "1011", fecha, "T", 45);
            acopioService.guardarData(acopio1);
            acopioService.guardarData(acopio2);
            acopio_list.add(acopio1);
            acopio_list.add(acopio2);
            dia++;
        }
        LocalDate fecha = LocalDate.of(2023, 4, 1);
        assertEquals(180, pagosService.Bonificacion("1011", 900, fecha));
        for(AcopioEntity acopio: acopio_list){
            acopioService.eliminarData(acopio);
        }
        proveedorService.eliminarData(proveedorService.findByCodigo(1011L));
    }
    @Test
    void bonificacionTestDia(){
        proveedorService.guardarProveedor(1012L, "ROMAN A. PABLO", "B", "No");
        int dia = 1;
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        for(long i = 0; i < 10; i++){
            LocalDate fecha = LocalDate.of(2023, 4, dia);
            AcopioEntity acopio3 = new AcopioEntity(i+300, "1012", fecha, "M", 45);
            acopioService.guardarData(acopio3);
            acopio_list.add(acopio3);
            dia++;
        }
        LocalDate fecha = LocalDate.of(2023, 4, 1);
        assertEquals(108, pagosService.Bonificacion("1012", 900, fecha));
        for(AcopioEntity acopio: acopio_list){
            acopioService.eliminarData(acopio);
        }
        proveedorService.eliminarData(proveedorService.findByCodigo(1012L));
    }
    @Test
    void bonificacionTestTarde(){
        proveedorService.guardarProveedor(1013L, "BELLO R. PHILIP", "C", "Si");
        int dia = 1;
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        for(long i = 0; i < 10; i++){
            LocalDate fecha = LocalDate.of(2023, 4, dia);
            AcopioEntity acopio4 = new AcopioEntity(i+400, "1013", fecha, "T", 45);
            acopioService.guardarData(acopio4);
            acopio_list.add(acopio4);
            dia++;
        }
        LocalDate fecha = LocalDate.of(2023, 4, 1);
        assertEquals(72, pagosService.Bonificacion("1013", 900, fecha));
        for(AcopioEntity acopio: acopio_list){
            acopioService.eliminarData(acopio);
        }
        proveedorService.eliminarData(proveedorService.findByCodigo(1013L));
    }
    @Test
    void dctoPagoLecheTestCasoUno(){
        assertEquals(0.0, pagosService.dctoPagoLeche(4.0, 100));
    }
    @Test
    void dctoPagoLecheTestCasoDos(){
        assertEquals(14.0, pagosService.dctoPagoLeche(15.0, 200));
    }
    @Test
    void dctoPagoLecheTestCasoTres(){
        assertEquals(45.0, pagosService.dctoPagoLeche(30.0, 300));
    }
    @Test
    void dctoPagoLecheTestCasoCuatro(){
        assertEquals(120.0, pagosService.dctoPagoLeche(50.0, 400));
    }
    @Test
    void dctoPagoGrasaTestCasoUno(){
        assertEquals(0.0, pagosService.dctoPagoGrasa(4.0, 100));
    }
    @Test
    void dctoPagoGrasaTestCasoDos(){
        assertEquals(24.0, pagosService.dctoPagoGrasa(20.0, 200));
    }
    @Test
    void dctoPagoGrasaTestCasoTre(){
        assertEquals(60.0, pagosService.dctoPagoGrasa(30.0, 300));
    }
    @Test
    void dctoPagoGrasaTestCasoCuatro(){
        assertEquals(120.0, pagosService.dctoPagoGrasa(50.0, 400));
    }
    @Test
    void dctoPagoSolidosTestCasoUno(){
        assertEquals(0.0, pagosService.dctoPagoSolidos(4.0, 100));
    }
    @Test
    void dctoPagoSolidosTestCasoDos(){
        assertEquals(36.0, pagosService.dctoPagoSolidos(11.0, 200));
    }
    @Test
    void dctoPagoSolidosTestCasoTres(){
        assertEquals(81.0, pagosService.dctoPagoSolidos(30.0, 300));
    }
    @Test
    void dctoPagoSolidosTestCasoCuatro(){
        assertEquals(180.0, pagosService.dctoPagoSolidos(50.0, 400));
    }

    @Test
    void calcularRetencionTestCasoSi(){
        assertEquals(130000, pagosService.calcularRetencion("Si",1000000.0));
    }
    @Test
    void calcularRetencionTestCasoNo(){
        assertEquals(0.0, pagosService.calcularRetencion("Si",900000.0));
    }
    @Test
    void getQuincenaTestPrimera(){
        assertEquals("2024/4/1", pagosService.getQuincena(LocalDate.of(2024, 4, 12)));
    }
    @Test
    void getQuincenaTestSegunda(){
        assertEquals("2024/4/2", pagosService.getQuincena(LocalDate.of(2024, 4, 25)));
    }
    @Test
    void getPagoAnteriorTest(){
        PagosEntity pago = new PagosEntity(2000L, "1015", "QUISPE S. ALCIDES",
                2023, 4, 2, 200, 3, 66.0, 500.0,
                5, 500.0, 20, 185.0, 140000, 6000,
                19000, 0.0, 0.0, 0.0,
                0.0, 18000.0,0.0,18000.0);
        pagosService.guardarData(pago);
        assertNull(pagosService.getPagoAnterior("2000", "2024/4/1"));
        pagosService.eliminarData(pago);
    }
    @Test
    void getPagoAnteriorTestEnero(){
        PagosEntity pago = new PagosEntity(2001L, "1015", "QUISPE S. ALCIDES",
                2023, 1, 1, 200, 3, 66.0, 500.0,
                5, 500.0, 20, 185.0, 140000, 6000,
                19000, 0.0, 0.0, 0.0,
                0.0, 18000.0,0.0,18000.0);
        pagosService.guardarData(pago);
        assertNull(pagosService.getPagoAnterior("2001", "2024/1/1"));
        pagosService.eliminarData(pago);
    }
    @Test
    void calcularPagosTest(){
        assertThrows(NullPointerException.class, () -> pagosService.calcularPagos());
    }
    @Test
    void obtenerDataTest(){
        PagosEntity pago = new PagosEntity(2001L, "1015", "QUISPE S. ALCIDES",
                2023, 1, 1, 200, 3, 66.0, 500.0,
                5, 500.0, 20, 185.0, 140000, 6000,
                19000, 0.0, 0.0, 0.0,
                0.0, 18000.0,0.0,18000.0);
        pagosService.guardarData(pago);
        PagosEntity pago2 = new PagosEntity(1L, "1003", "QUISPE S. ALCIDES",
                2023, 4, 1, 5155, 3, 1718.0, 0.0,
                25, 0.0, 37, 0.0, 3608500, 412400,
                773250, 0.0, 0.0, 0.0,
                0.0, 4794150.0, 623239.5,4170910.5);
        pagosService.guardarData(pago2);
        assertEquals(2, pagosService.obtenerData().size());
        pagosService.eliminarData(pago);
        pagosService.eliminarData(pago2);
    }
    // Calculo de pagos cuando ya hay un pago anterior
    @Test
    void  calcularPagoTest(){
        // se inserta un proveedor de prueba
        proveedorService.guardarProveedor(1010L, "QUISPE S. ALCIDES", "A", "Si");
        // Generamos en la base de datos un pago anterior
        PagosEntity pagoAnterior = new PagosEntity();
        pagoAnterior.setBonificacion_por_frecuencia(0.0);
        pagoAnterior.setDcto_var_grasa(0.0);
        pagoAnterior.setDcto_var_leche(0.0);
        pagoAnterior.setDcto_var_solidos_totales(0.0);
        pagoAnterior.setGrasa(25);
        pagoAnterior.setId_proveedor("1010");
        pagoAnterior.setKls_leche(5155);
        pagoAnterior.setMes(4);
        pagoAnterior.setAnio(2023);
        pagoAnterior.setMonto_final(4170910.5);
        pagoAnterior.setMonto_retencion(623239.5);
        pagoAnterior.setNombre_proveedor("QUISPE S. ALCIDES");
        pagoAnterior.setNro_dias_envios(3);
        pagoAnterior.setPago_grasa(412400);
        pagoAnterior.setPago_leche(3608500);
        pagoAnterior.setPago_solidos_totales(773250);
        pagoAnterior.setPago_total(4794150.0);
        pagoAnterior.setProm_diario_kls_leche(1718.0);
        pagoAnterior.setQuincena(1);
        pagoAnterior.setSolidos_totales(37);
        pagoAnterior.setVar_leche(0.0);
        pagoAnterior.setVar_grasa(0.0);
        pagoAnterior.setVar_solidos_totales(0.0);
        pagoAnterior.setQuincena(1);
        pagosService.guardarData(pagoAnterior);
        // Agregamos los datos del acopio y el laboratorio para el pago actual
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        AcopioEntity acopioUno = new AcopioEntity(1L,
                "1010", LocalDate.of(2023, 4, 17), "M", 45);
        AcopioEntity acopioDos = new AcopioEntity(2L,
                "1010", LocalDate.of(2023, 4, 17), "T", 45);
        AcopioEntity acopioTres = new AcopioEntity(3L,
                "1010", LocalDate.of(2023, 4, 18), "M", 35);
        AcopioEntity acopioCuatro = new AcopioEntity(4L,
                "1010", LocalDate.of(2023, 4, 18), "T", 25);
        AcopioEntity acopioCinco = new AcopioEntity(5L,
                "1010", LocalDate.of(2023, 4, 19), "M", 30);
        AcopioEntity acopioSeis = new AcopioEntity(6L,
                "1010", LocalDate.of(2023, 4, 19), "T", 20);
        acopioService.guardarData(acopioUno);
        acopioService.guardarData(acopioDos);
        acopioService.guardarData(acopioTres);
        acopioService.guardarData(acopioCuatro);
        acopioService.guardarData(acopioCinco);
        acopioService.guardarData(acopioSeis);
        acopio_list.add(acopioUno);
        acopio_list.add(acopioDos);
        acopio_list.add(acopioTres);
        acopio_list.add(acopioCuatro);
        acopio_list.add(acopioCinco);
        acopio_list.add(acopioSeis);
        // Resultados de laboratorio
        LaboratorioEntity laboratorioUno = new LaboratorioEntity(500L,5.0, 20.0, proveedorService.findByCodigo(1010L));
        laboratorioService.guardarData(laboratorioUno);
        // Generamos el pago actual
        pagosService.calcularPago("1010", LocalDate.now());
        PagosEntity pagoActual = pagosService.getPagoByIdProveedorAndQuincena("1010", 2023, 4, 2);
        assertEquals(2023, pagoActual.getAnio());
        assertEquals(0, pagoActual.getBonificacion_por_frecuencia());
        assertEquals(42000, pagoActual.getDcto_var_grasa());
        assertEquals(42000, pagoActual.getDcto_var_leche());
        assertEquals(63000, pagoActual.getDcto_var_solidos_totales());
        assertEquals(5, pagoActual.getGrasa());
        assertEquals("1010", pagoActual.getId_proveedor());
        assertEquals(200, pagoActual.getKls_leche());
        assertEquals(4, pagoActual.getMes());
        assertEquals(18000, pagoActual.getMonto_final());
        assertEquals(0, pagoActual.getMonto_retencion());
        assertEquals("QUISPE S. ALCIDES", pagoActual.getNombre_proveedor());
        assertEquals(3, pagoActual.getNro_dias_envios());
        assertEquals(6000, pagoActual.getPago_grasa());
        assertEquals(140000, pagoActual.getPago_leche());
        assertEquals(19000, pagoActual.getPago_solidos_totales());
        assertEquals(18000, pagoActual.getPago_total());
        assertEquals(66, pagoActual.getProm_diario_kls_leche());
        assertEquals(2, pagoActual.getQuincena());
        assertEquals(20, pagoActual.getSolidos_totales());
        assertEquals(500, pagoActual.getVar_grasa());
        assertEquals(2577.5, pagoActual.getVar_leche());
        assertEquals(185, pagoActual.getVar_solidos_totales());
        for(AcopioEntity acopio: acopio_list){
            acopioService.eliminarData(acopio);
        }
        pagosService.eliminarData(pagoAnterior);
        pagosService.eliminarData(pagoActual);
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(1010L));
        proveedorService.eliminarData(proveedorService.findByCodigo(1010L));
    }
    // Calculo de pagos cuando no hay pagos anteriores
    @Test
    void calcularPagosTest2(){
        proveedorService.guardarProveedor(1009L, "QUISPE S. ALCIDES", "A", "Si");
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        AcopioEntity acopioUno = new AcopioEntity(1L,
                "1009", LocalDate.of(2023, 4, 17), "M", 60);
        AcopioEntity acopioDos = new AcopioEntity(2L,
                "1009", LocalDate.of(2023, 4, 17), "T", 60);
        AcopioEntity acopioTres = new AcopioEntity(3L,
                "1009", LocalDate.of(2023, 4, 18), "M", 35);
        AcopioEntity acopioCuatro = new AcopioEntity(4L,
                "1009", LocalDate.of(2023, 4, 18), "T", 25);
        AcopioEntity acopioCinco = new AcopioEntity(5L,
                "1009", LocalDate.of(2023, 4, 19), "M", 30);
        AcopioEntity acopioSeis = new AcopioEntity(6L,
                "1009", LocalDate.of(2023, 4, 19), "T", 20);
        acopioService.guardarData(acopioUno);
        acopioService.guardarData(acopioDos);
        acopioService.guardarData(acopioTres);
        acopioService.guardarData(acopioCuatro);
        acopioService.guardarData(acopioCinco);
        acopioService.guardarData(acopioSeis);
        acopio_list.add(acopioUno);
        acopio_list.add(acopioDos);
        acopio_list.add(acopioTres);
        acopio_list.add(acopioCuatro);
        acopio_list.add(acopioCinco);
        acopio_list.add(acopioSeis);
        // Resultados de laboratorio
        LaboratorioEntity laboratorioUno = new LaboratorioEntity(1L,5.0, 20.0, proveedorService.findByCodigo(1009L));
        laboratorioService.guardarData(laboratorioUno);
        // Generamos el pago actual
        pagosService.calcularPago("1009", LocalDate.now());
        PagosEntity pagoActual = pagosService.getPagoByIdProveedorAndQuincena("1009", 2023, 4, 2);
        assertEquals(2023, pagoActual.getAnio());
        assertEquals(0, pagoActual.getBonificacion_por_frecuencia());
        assertEquals(0.0, pagoActual.getDcto_var_grasa());
        assertEquals(0.0, pagoActual.getDcto_var_leche());
        assertEquals(0.0, pagoActual.getDcto_var_solidos_totales());
        assertEquals(5, pagoActual.getGrasa());
        assertEquals("1009", pagoActual.getId_proveedor());
        assertEquals(230, pagoActual.getKls_leche());
        assertEquals(4, pagoActual.getMes());
        assertEquals(189750.0, pagoActual.getMonto_final());
        assertEquals(0, pagoActual.getMonto_retencion());
        assertEquals("QUISPE S. ALCIDES", pagoActual.getNombre_proveedor());
        assertEquals(3, pagoActual.getNro_dias_envios());
        assertEquals(6900, pagoActual.getPago_grasa());
        assertEquals(161000, pagoActual.getPago_leche());
        assertEquals(21850, pagoActual.getPago_solidos_totales());
        assertEquals(189750.0, pagoActual.getPago_total());
        assertEquals(76, pagoActual.getProm_diario_kls_leche());
        assertEquals(2, pagoActual.getQuincena());
        assertEquals(20, pagoActual.getSolidos_totales());
        assertEquals(0.0, pagoActual.getVar_grasa());
        assertEquals(0.0, pagoActual.getVar_leche());
        assertEquals(0.0, pagoActual.getVar_solidos_totales());
        for(AcopioEntity acopio: acopio_list){
            acopioService.eliminarData(acopio);
        }
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(1009L));
        proveedorService.eliminarData(proveedorService.findByCodigo(1009L));
        pagosService.eliminarData(pagoActual);
    }
    // Calculo de pagos cuando es la primera quincena del mes
    @Test
    void calcularPagoTestQuincenaUno(){
        proveedorService.guardarProveedor(1015L, "QUISPE S. ALCIDES", "A", "Si");
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        AcopioEntity acopioUno = new AcopioEntity(1L,
                "1015", LocalDate.of(2023, 4, 9), "M", 60);
        AcopioEntity acopioDos = new AcopioEntity(2L,
                "1015", LocalDate.of(2023, 4, 9), "T", 60);
        AcopioEntity acopioTres = new AcopioEntity(3L,
                "1015", LocalDate.of(2023, 4, 10), "M", 35);
        AcopioEntity acopioCuatro = new AcopioEntity(4L,
                "1015", LocalDate.of(2023, 4, 10), "T", 25);
        AcopioEntity acopioCinco = new AcopioEntity(5L,
                "1015", LocalDate.of(2023, 4, 12), "M", 30);
        AcopioEntity acopioSeis = new AcopioEntity(6L,
                "1015", LocalDate.of(2023, 4, 12), "T", 20);
        acopioService.guardarData(acopioUno);
        acopioService.guardarData(acopioDos);
        acopioService.guardarData(acopioTres);
        acopioService.guardarData(acopioCuatro);
        acopioService.guardarData(acopioCinco);
        acopioService.guardarData(acopioSeis);
        acopio_list.add(acopioUno);
        acopio_list.add(acopioDos);
        acopio_list.add(acopioTres);
        acopio_list.add(acopioCuatro);
        acopio_list.add(acopioCinco);
        acopio_list.add(acopioSeis);
        LaboratorioEntity laboratorioUno = new LaboratorioEntity(501L,5.0, 20.0, proveedorService.findByCodigo(1015L));
        laboratorioService.guardarData(laboratorioUno);
        pagosService.calcularPago("1015", LocalDate.of(2023, 4, 14));
        PagosEntity pagoActual = pagosService.getPagoByIdProveedorAndQuincena("1015", 2023, 4, 1);
        assertEquals(2023, pagoActual.getAnio());
        assertEquals(0, pagoActual.getBonificacion_por_frecuencia());
        assertEquals(0.0, pagoActual.getDcto_var_grasa());
        assertEquals(0.0, pagoActual.getDcto_var_leche());
        assertEquals(0.0, pagoActual.getDcto_var_solidos_totales());
        assertEquals(5, pagoActual.getGrasa());
        assertEquals("1015", pagoActual.getId_proveedor());
        assertEquals(230, pagoActual.getKls_leche());
        assertEquals(4, pagoActual.getMes());
        assertEquals(189750.0, pagoActual.getMonto_final());
        assertEquals(0, pagoActual.getMonto_retencion());
        assertEquals("QUISPE S. ALCIDES", pagoActual.getNombre_proveedor());
        assertEquals(3, pagoActual.getNro_dias_envios());
        assertEquals(6900, pagoActual.getPago_grasa());
        assertEquals(161000, pagoActual.getPago_leche());
        assertEquals(21850, pagoActual.getPago_solidos_totales());
        assertEquals(189750.0, pagoActual.getPago_total());
        assertEquals(76, pagoActual.getProm_diario_kls_leche());
        assertEquals(1, pagoActual.getQuincena());
        assertEquals(20, pagoActual.getSolidos_totales());
        assertEquals(0.0, pagoActual.getVar_grasa());
        assertEquals(0.0, pagoActual.getVar_leche());
        assertEquals(0.0, pagoActual.getVar_solidos_totales());
        for(AcopioEntity acopio: acopio_list){
            acopioService.eliminarData(acopio);
        }
        pagosService.eliminarData(pagoActual);
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(1015L));
        proveedorService.eliminarData(proveedorService.findByCodigo(1015L));
    }
    // Cuando no hay información de Acopio ni del laboratorio
    @Test
    void calcularPagoTestNoInfo(){
        proveedorService.guardarProveedor(1016L, "QUISPE S. ALCIDES", "A", "Si");
        pagosService.calcularPago("1016", LocalDate.of(2023, 4, 14));
        PagosEntity pago = pagosService.getPagoByIdProveedorAndQuincena("1016", 2023, 4, 1);
        assertEquals(0, pago.getBonificacion_por_frecuencia());
        assertEquals(0.0, pago.getDcto_var_grasa());
        assertEquals(0.0, pago.getDcto_var_leche());
        assertEquals(0.0, pago.getDcto_var_solidos_totales());
        assertEquals(0, pago.getGrasa());
        assertEquals("1016", pago.getId_proveedor());
        assertEquals(0, pago.getKls_leche());
        assertEquals(4, pago.getMes());
        assertEquals(0.0, pago.getMonto_final());
        assertEquals(0, pago.getMonto_retencion());
        assertEquals("QUISPE S. ALCIDES", pago.getNombre_proveedor());
        assertEquals(0, pago.getNro_dias_envios());
        assertEquals(0, pago.getPago_grasa());
        assertEquals(0, pago.getPago_leche());
        assertEquals(0, pago.getPago_solidos_totales());
        assertEquals(0.0, pago.getPago_total());
        assertEquals(0, pago.getProm_diario_kls_leche());
        assertEquals(1, pago.getQuincena());
        assertEquals(0, pago.getSolidos_totales());
        assertEquals(0.0, pago.getVar_grasa());
        assertEquals(0.0, pago.getVar_leche());
        assertEquals(0.0, pago.getVar_solidos_totales());
        pagosService.eliminarData(pago);
        proveedorService.eliminarData(proveedorService.findByCodigo(1016L));
    }
}
