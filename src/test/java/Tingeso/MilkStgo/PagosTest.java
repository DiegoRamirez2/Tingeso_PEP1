package Tingeso.MilkStgo;

import Tingeso.MilkStgo.Entities.PagosEntity;
import Tingeso.MilkStgo.Services.PagosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PagosTest {
    @Autowired
    PagosService pagosService;

//    @Test
//    void  pagoLecheTest(){
//        PagosEntity pago = new PagosEntity();
//        pago.setId_proveedor("1003");
//        pago.setNombre_proveedor("ALCIDES QUISPE");
//        pago.setAnio(2023);
//        pago.setMes(4);
//        pago.setQuincena(1);
//        pago.setKls_leche(1000);
//    }
}
