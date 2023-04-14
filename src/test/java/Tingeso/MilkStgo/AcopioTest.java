package Tingeso.MilkStgo;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import Tingeso.MilkStgo.Repositories.AcopioRepository;
import Tingeso.MilkStgo.Repositories.ProveedorRepository;
import Tingeso.MilkStgo.Services.AcopioService;
import Tingeso.MilkStgo.Services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AcopioTest {
    @Autowired
    AcopioService acopioService;
    @Autowired
    AcopioRepository acopioRepository;
    @Autowired
    ProveedorService proveedorService;
    @Autowired
    ProveedorRepository proveedorRepository;

    @Test
    void testGuardarDataDB(){
        acopioService.guardarDataDB("17-04-2023", "M", "1003", "45");
        AcopioEntity acopioEntity = acopioService.getAcopioByFechaTurnoProveedor(
                LocalDate.parse("17-04-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "M", "1003");
        assert(acopioEntity.getKls_leche() == 45);
        acopioService.eliminarData(acopioEntity);
    }
    @Test
    void testObtenerData(){
        AcopioEntity acopio = new AcopioEntity();
        acopio.setFecha(LocalDate.parse("17-03-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        acopio.setTurno("T");
        acopio.setProveedor("1003");
        acopio.setKls_leche(45);
        acopioService.guardarData(acopio);
        assertNotNull(acopioService.ObtenerData());
        acopioService.eliminarData(acopio);
    }
    @Test
        void countAcopioSinceFecha() {
            ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
            for (int i = 10; i < 20; i++) {
                AcopioEntity acopio = new AcopioEntity();
                String fecha = i + "-03-2023";
                acopio.setFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                acopio.setTurno("T");
                acopio.setProveedor("1003");
                acopio.setKls_leche(45);
                acopio_list.add(acopio);
                System.out.println();
                acopioService.guardarData(acopio);
            }
            assertEquals(10, acopioService.countAcopioSinceFecha(LocalDate.parse("10-03-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")), "T", "1003"));
            for(AcopioEntity acopioI : acopio_list) {
                acopioService.eliminarData(acopioI);
            }
    }
    @Test
    void countDaysAcopioByProveedor(){
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        for(int i = 10; i < 20; i++){
            String fecha = i + "-03-2023";
            LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            AcopioEntity acopioManana = new AcopioEntity();
            acopioManana.setFecha(date);
            acopioManana.setTurno("M");
            acopioManana.setProveedor("1003");
            acopioManana.setKls_leche(45);
            acopioService.guardarData(acopioManana);
            AcopioEntity acopioTarde = new AcopioEntity();
            acopioTarde.setFecha(date.plusDays(5));
            acopioTarde.setTurno("T");
            acopioTarde.setProveedor("1003");
            acopioTarde.setKls_leche(45);
            acopioService.guardarData(acopioTarde);
            System.out.println("La fecha de acopio1 es: " + acopioManana.getFecha());
            acopio_list.add(acopioManana);
            System.out.println("La fecha de acopio2 es: " + acopioTarde.getFecha());
            acopio_list.add(acopioTarde);
        }
        System.out.println(acopio_list.size());
        assertEquals(15, acopioService.countDaysAcopioByProveedor("1003", LocalDate.parse("10-03-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        for(AcopioEntity acopioI : acopio_list) {
            acopioService.eliminarData(acopioI);
        }
    }
    @Test
    void lecheByProveedorTest(){
        ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        for(int i = 5; i < 10; i++){
            AcopioEntity acopio = new AcopioEntity();
            String fecha = "0" + i + "-03-2023";
            LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            acopio.setFecha(date);
            acopio.setTurno("M");
            acopio.setProveedor("1003");
            acopio.setKls_leche(45 * i);
            acopioService.guardarData(acopio);
            acopio_list.add(acopio);
        }
        assertEquals(1575, acopioService.lecheByProveedor("1003", LocalDate.parse("05-03-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        for(AcopioEntity acopioI : acopio_list) {
            acopioService.eliminarData(acopioI);
        }
    }
    @Test
    void getProveedoresTest(){
ArrayList<AcopioEntity> acopio_list = new ArrayList<>();
        for(int i = 10; i < 15; i++){
            for(int j = 0; j < 2; j++){
                AcopioEntity acopio = new AcopioEntity();
                String fecha = i + "-03-2023";
                LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                acopio.setFecha(date);
                if(j == 0){
                    acopio.setTurno("M");
                }else{
                    acopio.setTurno("T");
                }
                acopio.setProveedor("1003");
                acopio.setKls_leche(45);
                acopioService.guardarData(acopio);
                acopio_list.add(acopio);
            }
        }
        assertNotNull(acopioService.getProveedores());
        for(AcopioEntity acopioI : acopio_list) {
            acopioService.eliminarData(acopioI);
        }
    }
}
