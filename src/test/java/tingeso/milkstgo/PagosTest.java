package tingeso.milkstgo;

import tingeso.milkstgo.entities.AcopioEntity;
import tingeso.milkstgo.entities.LaboratorioEntity;
import tingeso.milkstgo.entities.PagosEntity;
import tingeso.milkstgo.services.AcopioService;
import tingeso.milkstgo.services.LaboratorioService;
import tingeso.milkstgo.services.PagosService;
import tingeso.milkstgo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PagosTest {
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
        assertEquals(180, pagosService.bonificacion("1011", 900, fecha));
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
        assertEquals(108, pagosService.bonificacion("1012", 900, fecha));
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
        assertEquals(72, pagosService.bonificacion("1013", 900, fecha));
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
    void calcularRetencionTestCasoUno(){
        assertEquals(130000, pagosService.calcularRetencion("Si",1000000.0));
    }
    @Test
    void calcularRetencionTestCasoDos(){
        assertEquals(0.0, pagosService.calcularRetencion("Si",900000.0));
    }
    @Test
    void calcularRetencionTestCasoTres(){assertEquals(0.0, pagosService.calcularRetencion("No",1000000.0));}
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
        assertNull(pagosService.getPagoAnterior("1998", "2024/4/2"));
    }
    @Test
    void getPagoAnteriorTestEneroDos(){
        assertNull(pagosService.getPagoAnterior("1998", "2024/1/2"));
    }
    @Test
    void getPagoAnteriorTestEneroTres(){
        assertNull(pagosService.getPagoAnterior("1998", "2024/1/3"));
    }
    @Test
    void getPagoAnteriorTestEnero(){
        PagosEntity pago = new PagosEntity(2001L, "2001", "QUISPE S. ALCIDES",
                2023, 1, 1, 200, 3, 66.0, 500.0,
                5, 500.0, 20, 185.0, 140000, 6000,
                19000, 0.0, 0.0, 0.0,
                0.0, 18000.0,0.0,18000.0);
        pagosService.guardarData(pago);
        assertNull(pagosService.getPagoAnterior("2001", "2024/1/1"));
        pagosService.eliminarData(pago);
    }
    @Test
    void getPagoAnteriorTestPrimera(){
        PagosEntity pago = new PagosEntity();
        pago.setIdProveedor("1999");
        pago.setNombreProveedor("QUISPE S. ALCIDES");
        pago.setAnio(2023);
        pago.setMes(3);
        pago.setQuincena(2);
        pago.setKlsLeche(200);
        pago.setNroDiasEnvios(3);
        pago.setPromDiarioKlsLeche(66.0);
        pago.setPagoLeche(140000);
        pago.setGrasa(5);
        pago.setPagoGrasa(6000);
        pago.setSolidosTotales(20);
        pago.setPagoSolidosTotales(19000);
        pago.setVarLeche(0.0);
        pago.setDctoVarLeche(0.0);
        pago.setVarGrasa(0.0);
        pago.setDctoVarGrasa(0.0);
        pago.setVarSolidosTotales(0.0);
        pago.setDctoVarSolidosTotales(0.0);
        pago.setPagoTotal(18000.0);
        pago.setBonificacionPorFrecuencia(0.0);
        pago.setMontoRetencion(0.0);
        pago.setMontoFinal(18000.0);
        pagosService.guardarData(pago);
        PagosEntity pagoAnterior = pagosService.getPagoAnterior("1999", "2023/4/1");
        assertEquals("1999", pagoAnterior.getIdProveedor());
        assertEquals("QUISPE S. ALCIDES", pagoAnterior.getNombreProveedor());
        assertEquals(2023, pagoAnterior.getAnio());
        assertEquals(3, pagoAnterior.getMes());
        assertEquals(2, pagoAnterior.getQuincena());
        assertEquals(200, pagoAnterior.getKlsLeche());
        assertEquals(3, pagoAnterior.getNroDiasEnvios());
        assertEquals(66.0, pagoAnterior.getPromDiarioKlsLeche());
        assertEquals(140000, pagoAnterior.getPagoLeche());
        assertEquals(5, pagoAnterior.getGrasa());
        assertEquals(6000, pagoAnterior.getPagoGrasa());
        assertEquals(20, pagoAnterior.getSolidosTotales());
        assertEquals(19000, pagoAnterior.getPagoSolidosTotales());
        assertEquals(0.0, pagoAnterior.getVarLeche());
        assertEquals(0.0, pagoAnterior.getDctoVarLeche());
        assertEquals(0.0, pagoAnterior.getVarGrasa());
        assertEquals(0.0, pagoAnterior.getDctoVarGrasa());
        assertEquals(0.0, pagoAnterior.getVarSolidosTotales());
        assertEquals(0.0, pagoAnterior.getDctoVarSolidosTotales());
        assertEquals(18000.0, pagoAnterior.getPagoTotal());
        assertEquals(0.0, pagoAnterior.getBonificacionPorFrecuencia());
        assertEquals(0.0, pagoAnterior.getMontoRetencion());
        assertEquals(18000.0, pagoAnterior.getMontoFinal());
        pagosService.eliminarData(pago);
    }
    @Test
    void obtenerDataTest(){
        PagosEntity pago = new PagosEntity(2026L, "1015", "QUISPE S. ALCIDES",
                2023, 1, 1, 200, 3, 66.0, 500.0,
                5, 500.0, 20, 185.0, 140000, 6000,
                19000, 0.0, 0.0, 0.0,
                0.0, 18000.0,0.0,18000.0);
        pagosService.guardarData(pago);
        PagosEntity pago2 = new PagosEntity(2025L, "1003", "QUISPE S. ALCIDES",
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
        pagoAnterior.setBonificacionPorFrecuencia(0.0);
        pagoAnterior.setDctoVarGrasa(0.0);
        pagoAnterior.setDctoVarLeche(0.0);
        pagoAnterior.setDctoVarSolidosTotales(0.0);
        pagoAnterior.setGrasa(25);
        pagoAnterior.setIdProveedor("1010");
        pagoAnterior.setKlsLeche(5155);
        pagoAnterior.setMes(4);
        pagoAnterior.setAnio(2023);
        pagoAnterior.setMontoFinal(4170910.5);
        pagoAnterior.setMontoRetencion(623239.5);
        pagoAnterior.setNombreProveedor("QUISPE S. ALCIDES");
        pagoAnterior.setNroDiasEnvios(3);
        pagoAnterior.setPagoGrasa(412400);
        pagoAnterior.setPagoLeche(3608500);
        pagoAnterior.setPagoSolidosTotales(773250);
        pagoAnterior.setPagoTotal(4794150.0);
        pagoAnterior.setPromDiarioKlsLeche(1718.0);
        pagoAnterior.setQuincena(1);
        pagoAnterior.setSolidosTotales(37);
        pagoAnterior.setVarLeche(0.0);
        pagoAnterior.setVarGrasa(0.0);
        pagoAnterior.setVarSolidosTotales(0.0);
        pagoAnterior.setQuincena(1);
        pagosService.guardarData(pagoAnterior);
        // Agregamos los datos del acopio y el laboratorio para el pago actual
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
        // Resultados de laboratorio
        LaboratorioEntity laboratorioUno = new LaboratorioEntity(500L,5.0, 20.0, proveedorService.findByCodigo(1010L));
        laboratorioService.guardarData(laboratorioUno);
        // Generamos el pago actual
        pagosService.calcularPago("1010", LocalDate.now().minusMonths(1));
        PagosEntity pagoActual = pagosService.getPagoByIdProveedorAndQuincena("1010", 2023, 4, 2);
        assertEquals(2023, pagoActual.getAnio());
        assertEquals(0, pagoActual.getBonificacionPorFrecuencia());
        assertEquals(42000, pagoActual.getDctoVarGrasa());
        assertEquals(42000, pagoActual.getDctoVarLeche());
        assertEquals(63000, pagoActual.getDctoVarSolidosTotales());
        assertEquals(5, pagoActual.getGrasa());
        assertEquals("1010", pagoActual.getIdProveedor());
        assertEquals(200, pagoActual.getKlsLeche());
        assertEquals(4, pagoActual.getMes());
        assertEquals(18000, pagoActual.getMontoFinal());
        assertEquals(0, pagoActual.getMontoRetencion());
        assertEquals("QUISPE S. ALCIDES", pagoActual.getNombreProveedor());
        assertEquals(3, pagoActual.getNroDiasEnvios());
        assertEquals(6000, pagoActual.getPagoGrasa());
        assertEquals(140000, pagoActual.getPagoLeche());
        assertEquals(19000, pagoActual.getPagoSolidosTotales());
        assertEquals(18000, pagoActual.getPagoTotal());
        assertEquals(66.67, pagoActual.getPromDiarioKlsLeche());
        assertEquals(2, pagoActual.getQuincena());
        assertEquals(20, pagoActual.getSolidosTotales());
        assertEquals(500, pagoActual.getVarGrasa());
        assertEquals(2577.5, pagoActual.getVarLeche());
        assertEquals(185, pagoActual.getVarSolidosTotales());
        acopioService.eliminarData(acopioService.getAcopioByFechaTurnoProveedor(LocalDate.of(2023, 4, 17), "M", "1010"));
        acopioService.eliminarData(acopioService.getAcopioByFechaTurnoProveedor(LocalDate.of(2023, 4, 17), "T", "1010"));
        acopioService.eliminarData(acopioService.getAcopioByFechaTurnoProveedor(LocalDate.of(2023, 4, 18), "M", "1010"));
        acopioService.eliminarData(acopioService.getAcopioByFechaTurnoProveedor(LocalDate.of(2023, 4, 18), "T", "1010"));
        acopioService.eliminarData(acopioService.getAcopioByFechaTurnoProveedor(LocalDate.of(2023, 4, 19), "M", "1010"));
        acopioService.eliminarData(acopioService.getAcopioByFechaTurnoProveedor(LocalDate.of(2023, 4, 19), "T", "1010"));
        pagosService.eliminarData(pagoAnterior);
        pagosService.eliminarData(pagoActual);
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(1010L));
        proveedorService.eliminarData(proveedorService.findByCodigo(1010L));
    }
    // Calculo de pagos cuando no hay pagos anteriores
    @Test
    void calcularPagoTest2(){
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
        pagosService.calcularPago("1009", LocalDate.now().minusMonths(1));
        PagosEntity pagoActual = pagosService.getPagoByIdProveedorAndQuincena("1009", 2023, 4, 2);
        assertEquals(2023, pagoActual.getAnio());
        assertEquals(0, pagoActual.getBonificacionPorFrecuencia());
        assertEquals(0.0, pagoActual.getDctoVarGrasa());
        assertEquals(0.0, pagoActual.getDctoVarLeche());
        assertEquals(0.0, pagoActual.getDctoVarSolidosTotales());
        assertEquals(5, pagoActual.getGrasa());
        assertEquals("1009", pagoActual.getIdProveedor());
        assertEquals(230, pagoActual.getKlsLeche());
        assertEquals(4, pagoActual.getMes());
        assertEquals(189750.0, pagoActual.getMontoFinal());
        assertEquals(0, pagoActual.getMontoRetencion());
        assertEquals("QUISPE S. ALCIDES", pagoActual.getNombreProveedor());
        assertEquals(3, pagoActual.getNroDiasEnvios());
        assertEquals(6900, pagoActual.getPagoGrasa());
        assertEquals(161000, pagoActual.getPagoLeche());
        assertEquals(21850, pagoActual.getPagoSolidosTotales());
        assertEquals(189750.0, pagoActual.getPagoTotal());
        assertEquals(76.67, pagoActual.getPromDiarioKlsLeche());
        assertEquals(2, pagoActual.getQuincena());
        assertEquals(20, pagoActual.getSolidosTotales());
        assertEquals(0.0, pagoActual.getVarGrasa());
        assertEquals(0.0, pagoActual.getVarLeche());
        assertEquals(0.0, pagoActual.getVarSolidosTotales());
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
        assertEquals(0, pagoActual.getBonificacionPorFrecuencia());
        assertEquals(0.0, pagoActual.getDctoVarGrasa());
        assertEquals(0.0, pagoActual.getDctoVarLeche());
        assertEquals(0.0, pagoActual.getDctoVarSolidosTotales());
        assertEquals(5, pagoActual.getGrasa());
        assertEquals("1015", pagoActual.getIdProveedor());
        assertEquals(230, pagoActual.getKlsLeche());
        assertEquals(4, pagoActual.getMes());
        assertEquals(189750.0, pagoActual.getMontoFinal());
        assertEquals(0, pagoActual.getMontoRetencion());
        assertEquals("QUISPE S. ALCIDES", pagoActual.getNombreProveedor());
        assertEquals(3, pagoActual.getNroDiasEnvios());
        assertEquals(6900, pagoActual.getPagoGrasa());
        assertEquals(161000, pagoActual.getPagoLeche());
        assertEquals(21850, pagoActual.getPagoSolidosTotales());
        assertEquals(189750.0, pagoActual.getPagoTotal());
        assertEquals(76.67, pagoActual.getPromDiarioKlsLeche());
        assertEquals(1, pagoActual.getQuincena());
        assertEquals(20, pagoActual.getSolidosTotales());
        assertEquals(0.0, pagoActual.getVarGrasa());
        assertEquals(0.0, pagoActual.getVarLeche());
        assertEquals(0.0, pagoActual.getVarSolidosTotales());
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
        assertEquals(0, pago.getBonificacionPorFrecuencia());
        assertEquals(0.0, pago.getDctoVarGrasa());
        assertEquals(0.0, pago.getDctoVarLeche());
        assertEquals(0.0, pago.getDctoVarSolidosTotales());
        assertEquals(0, pago.getGrasa());
        assertEquals("1016", pago.getIdProveedor());
        assertEquals(0, pago.getKlsLeche());
        assertEquals(4, pago.getMes());
        assertEquals(0.0, pago.getMontoFinal());
        assertEquals(0, pago.getMontoRetencion());
        assertEquals("QUISPE S. ALCIDES", pago.getNombreProveedor());
        assertEquals(0, pago.getNroDiasEnvios());
        assertEquals(0, pago.getPagoGrasa());
        assertEquals(0, pago.getPagoLeche());
        assertEquals(0, pago.getPagoSolidosTotales());
        assertEquals(0.0, pago.getPagoTotal());
        assertEquals(0, pago.getPromDiarioKlsLeche());
        assertEquals(1, pago.getQuincena());
        assertEquals(0, pago.getSolidosTotales());
        assertEquals(0.0, pago.getVarGrasa());
        assertEquals(0.0, pago.getVarLeche());
        assertEquals(0.0, pago.getVarSolidosTotales());
        pagosService.eliminarData(pago);
        proveedorService.eliminarData(proveedorService.findByCodigo(1016L));
    }
    @Test
    void calcularPagoExistePago(){
        PagosEntity pago = new PagosEntity();
        pago.setIdProveedor("1020");
        pago.setNombreProveedor("QUISPE S. ALCIDES");
        pago.setAnio(2023);
        pago.setMes(4);
        pago.setQuincena(1);
        pago.setKlsLeche(200);
        pago.setNroDiasEnvios(3);
        pago.setPromDiarioKlsLeche(66.0);
        pago.setPagoLeche(140000);
        pago.setGrasa(5);
        pago.setPagoGrasa(6000);
        pago.setSolidosTotales(20);
        pago.setPagoSolidosTotales(19000);
        pago.setVarLeche(0.0);
        pago.setDctoVarLeche(0.0);
        pago.setVarGrasa(0.0);
        pago.setDctoVarGrasa(0.0);
        pago.setVarSolidosTotales(0.0);
        pago.setDctoVarSolidosTotales(0.0);
        pago.setPagoTotal(18000.0);
        pago.setBonificacionPorFrecuencia(0.0);
        pago.setMontoRetencion(0.0);
        pago.setMontoFinal(18000.0);
        pagosService.guardarData(pago);
        pagosService.calcularPago("1020", LocalDate.of(2023, 4, 14));
        PagosEntity pagoActual = pagosService.getPagoByIdProveedorAndQuincena("1020", 2023, 4, 1);
        assertEquals(pago, pagoActual);
        pagosService.eliminarData(pago);
    }
    @Test
    void calcularPagoTestNoGrasa(){
        proveedorService.guardarProveedor(2019L, "QUISPE S. ALCIDES", "A", "Si");
        AcopioEntity acopioUno = new AcopioEntity(1L,
                "2019", LocalDate.of(2023, 4, 9), "M", 60);
        acopioService.guardarData(acopioUno);
        LaboratorioEntity laboratorioUno = new LaboratorioEntity(501L,null, 20.0, proveedorService.findByCodigo(2019L));
        laboratorioService.guardarData(laboratorioUno);
        pagosService.calcularPago("2019", LocalDate.of(2023, 4, 14));
        PagosEntity pago = pagosService.getPagoByIdProveedorAndQuincena("2019", 2023, 4, 1);
        assertEquals(1, pago.getQuincena());
        assertEquals(0, pago.getBonificacionPorFrecuencia());
        assertEquals(0.0, pago.getDctoVarGrasa());
        assertEquals(0.0, pago.getDctoVarLeche());
        assertEquals(0.0, pago.getDctoVarSolidosTotales());
        assertEquals(0, pago.getGrasa());
        assertEquals("2019", pago.getIdProveedor());
        assertEquals(0, pago.getKlsLeche());
        assertEquals(4, pago.getMes());
        assertEquals(0.0, pago.getMontoFinal());
        assertEquals(0, pago.getMontoRetencion());
        assertEquals("QUISPE S. ALCIDES", pago.getNombreProveedor());
        assertEquals(0, pago.getNroDiasEnvios());
        assertEquals(0, pago.getPagoGrasa());
        assertEquals(0, pago.getPagoLeche());
        assertEquals(0, pago.getPagoSolidosTotales());
        assertEquals(0.0, pago.getPagoTotal());
        assertEquals(0, pago.getPromDiarioKlsLeche());
        assertEquals(2023, pago.getAnio());
        assertEquals(0.0, pago.getVarGrasa());
        assertEquals(0.0, pago.getVarLeche());
        assertEquals(0.0, pago.getVarSolidosTotales());
        acopioService.eliminarData(acopioUno);
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(2019L));
        proveedorService.eliminarData(proveedorService.findByCodigo(2019L));
        pagosService.eliminarData(pago);
    }
    @Test
    void calcularPagoTestNoSolidosTotales(){
        proveedorService.guardarProveedor(2020L, "QUISPE S. ALCIDES", "A", "Si");
        AcopioEntity acopioUno = new AcopioEntity(1L,
                "2020", LocalDate.of(2023, 4, 9), "M", 60);
        acopioService.guardarData(acopioUno);
        LaboratorioEntity laboratorioUno = new LaboratorioEntity(501L, 5.0, null, proveedorService.findByCodigo(2020L));
        laboratorioService.guardarData(laboratorioUno);
        pagosService.calcularPago("2020", LocalDate.of(2023, 4, 14));
        PagosEntity pago = pagosService.getPagoByIdProveedorAndQuincena("2020", 2023, 4, 1);
        assertEquals(1, pago.getQuincena());
        assertEquals(0, pago.getBonificacionPorFrecuencia());
        assertEquals(0.0, pago.getDctoVarGrasa());
        assertEquals(0.0, pago.getDctoVarLeche());
        assertEquals(0.0, pago.getDctoVarSolidosTotales());
        assertEquals(0, pago.getGrasa());
        assertEquals("2020", pago.getIdProveedor());
        assertEquals(0, pago.getKlsLeche());
        assertEquals(4, pago.getMes());
        assertEquals(0.0, pago.getMontoFinal());
        assertEquals(0, pago.getMontoRetencion());
        assertEquals("QUISPE S. ALCIDES", pago.getNombreProveedor());
        assertEquals(0, pago.getNroDiasEnvios());
        assertEquals(0, pago.getPagoGrasa());
        assertEquals(0, pago.getPagoLeche());
        assertEquals(0, pago.getPagoSolidosTotales());
        assertEquals(0.0, pago.getPagoTotal());
        assertEquals(0, pago.getPromDiarioKlsLeche());
        assertEquals(2023, pago.getAnio());
        assertEquals(0.0, pago.getVarGrasa());
        assertEquals(0.0, pago.getVarLeche());
        assertEquals(0.0, pago.getVarSolidosTotales());
        acopioService.eliminarData(acopioUno);
        laboratorioService.eliminarData(laboratorioService.getLaboratorioByProveedor(2020L));
        proveedorService.eliminarData(proveedorService.findByCodigo(2020L));
        pagosService.eliminarData(pago);
    }
    @Test
    void obtenerProveedoresTest(){
        proveedorService.guardarProveedor(2030L, "QUISPE S. ALCIDES", "A", "Si");
        proveedorService.guardarProveedor(2031L, "BELLO R. PHILIP", "B", "Si");
        proveedorService.guardarProveedor(2032L, "ROMAN A. PABLO", "C", "No");
        AcopioEntity acopioUno = new AcopioEntity(1L,
                "2030", LocalDate.of(2023, 4, 9), "M", 60);
        acopioService.guardarData(acopioUno);
        AcopioEntity acopioDos = new AcopioEntity(2L,
                "2031", LocalDate.of(2023, 4, 9), "M", 60);
        acopioService.guardarData(acopioDos);
        AcopioEntity acopioTres = new AcopioEntity(3L,
                "2032", LocalDate.of(2023, 4, 9), "M", 60);
        acopioService.guardarData(acopioTres);
        assertEquals(3, pagosService.getProveedores().size());
        proveedorService.eliminarData(proveedorService.findByCodigo(2030L));
        proveedorService.eliminarData(proveedorService.findByCodigo(2031L));
        proveedorService.eliminarData(proveedorService.findByCodigo(2032L));
        acopioService.eliminarData(acopioUno);
        acopioService.eliminarData(acopioDos);
        acopioService.eliminarData(acopioTres);
    }
}
