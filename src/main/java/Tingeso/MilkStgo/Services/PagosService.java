package Tingeso.MilkStgo.Services;

import Tingeso.MilkStgo.Entities.PagosEntity;
import Tingeso.MilkStgo.Repositories.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class PagosService {
    @Autowired
    private PagosRepository pagosRepository;
    @Autowired
    private LaboratorioService laboratorioService;
    @Autowired
    private AcopioService acopioService;

    public Integer pagoLeche(String categoria, Integer kls_leche){
        return switch (categoria) {
            case "A" -> kls_leche * 700;
            case "B" -> kls_leche * 500;
            case "C" -> kls_leche * 400;
            case "D" -> kls_leche * 250;
            default -> 0;
        };
    }
    public Integer pagoGrasa(Integer grasa, Integer kls_leche){
        if(grasa <= 20){
            return kls_leche * 30;
        }
        else if(grasa <= 45){
            return kls_leche * 80;
        }
        else {
            return kls_leche * 120;
        }
    }
    public Integer pagoSolidos(Integer solidos, Integer kls_leche){
        if(solidos <= 7){
            return kls_leche * -130;
        }
        else if(solidos > 8 && solidos <= 18){
            return kls_leche * -90;
        }
        else if(solidos > 18 && solidos <= 35) {
            return kls_leche * 95;
        }
        else if(solidos > 36) {
            return kls_leche * 150;
        }
        else{
            return 0;
        }
    }
    public Double Bonificacion(String id_proveedor, Integer pago_leche, LocalDate fecha){
        Integer M = acopioService.countAcopioByFecha(fecha, "M", id_proveedor);
        Integer T = acopioService.countAcopioByFecha(fecha, "T", id_proveedor);
        if(M >= 10 && T >= 10){
            return pago_leche * 0.2;
        }
        else if(M >= 10){
            return pago_leche * 0.12;
        }
        else if(T >= 10){
            return pago_leche * 0.08;
        }
        else{
            return 0.0;
        }
    }
    public Double dctoPagoLeche(Double variacion, Integer pago_leche){
        double dcto = 0.0;
        if(variacion > 0 && variacion <= 8){
            System.out.println("Entramos a dctoLeche 0");
            dcto = pago_leche * 0.0;
        }
        else if(variacion > 8 && variacion <= 25){
            System.out.println("Entramos a dctoLeche 1");
            dcto = pago_leche * 0.07;
        }
        else if(variacion > 25 && variacion <= 45){
            System.out.println("Entramos a dctoLeche 2");
            dcto = pago_leche * 0.15;
        }
        else if(variacion > 45){
            System.out.println("Entramos a dctoLeche 3");
            dcto = pago_leche * 0.3;
        }
        dcto = Math.round(dcto * 100.0) / 100.0;
        return dcto;
    }
    public Double dctoPagoGrasa(Double variacion, Integer pagoLeche) {
        double dcto = 0.0;
        if (variacion > 0 && variacion <= 15) {
            System.out.println("Entramos a dctoGrasa 0");
            dcto = 0.0 * pagoLeche;
        }
        if (variacion >= 16 && variacion <= 25) {
            System.out.println("Entramos a dctoGrasa 1");
            dcto = 0.12 * pagoLeche;
        }
        if (variacion >= 26 && variacion <= 40) {
            System.out.println("Entramos a dctoGrasa 2");
            dcto = 0.20 * pagoLeche;
        }
        if (variacion >= 41) {
            System.out.println("Entramos a dctoGrasa 3");
            dcto = 0.3 * pagoLeche;
        }
        dcto = Math.round(dcto * 100.0) / 100.0;
        return dcto;
    }
    public Double dctoPagoSolidos(Double variacion, Integer pagoLeche) {
        double dcto = 0.0;
        if (variacion > 0 && variacion <= 6) {
            System.out.println("Entramos a dctoSolidos 0");
            dcto = 0.0 * pagoLeche;
        }
        if (variacion >= 7 && variacion <= 12) {
            System.out.println("Entramos a dctoSolidos 1");
            dcto = 0.18 * pagoLeche;
        }
        if (variacion >= 13 && variacion <= 35) {
            System.out.println("Entramos a dctoSolidos 2");
            dcto = 0.27 * pagoLeche;
        }
        if (variacion >= 36) {
            System.out.println("Entramos a dctoSolidos 3");
            dcto = 0.45 * pagoLeche;
        }
        dcto = Math.round(dcto * 100.0) / 100.0;
        return dcto;
    }
    public Double CalcularRetencion(String retencion, Double pagototal) {
        double MontoRetencion = 0.0;
        if (retencion.equals("Si") && pagototal > 950000.0) {
            MontoRetencion = pagototal * 0.13;
        }
        MontoRetencion = Math.round(MontoRetencion * 100.0) / 100.0;
        return MontoRetencion;
    }
    public Double variacionPorcentual(Integer valorAntiguo, Integer valorNuevo) {
        double variacion = (valorAntiguo.doubleValue() / valorNuevo.doubleValue()) * 100;
        variacion = Math.round(variacion * 100.0) / 100.0;
        return variacion;
    }
    public Double variacionPlana(Integer valorAntiguo, Integer valorNuevo) {
        return valorAntiguo - valorNuevo.doubleValue();
    }
    public String getQuincena(){
        LocalDate fecha = LocalDate.now();
        int dia = fecha.getDayOfMonth();
        if(dia <= 15){
            return fecha.getYear() + "/" + fecha.getMonthValue() + "/1";
        }
        else{
            return fecha.getYear() + "/" + fecha.getMonthValue() + "/2";
        }
    }
    public PagosEntity getPagoAnterior(String id_proveedor, String quincena){
        String[] fechaActual = quincena.split("/");
        int anio = Integer.parseInt(fechaActual[0]);
        int mes = Integer.parseInt(fechaActual[1]);
        int quincenaAnterior = 0;
        if(Integer.parseInt(fechaActual[2]) == 2){
            quincenaAnterior = 1;
        }else if(Integer.parseInt(fechaActual[2]) == 1){
            mes -= 1;
            if(mes == 0){
                mes = 12;
                anio -= 1;
            }
            quincenaAnterior = 2;
        }
        return pagosRepository.findPagosByQuincena(id_proveedor, anio, mes, quincenaAnterior);
    }
    public void calcularPago(String id_proveedor){
        PagosEntity pago = new PagosEntity();
        String quincena = getQuincena();
        LocalDate fechaBase;
        // Dependiendo de la quincena, se determina si se empieza a buscar desde el 1 o desde el 16
        if(quincena.split("/")[2].equals("1")){
            fechaBase = LocalDate.of(Integer.parseInt(quincena.split("/")[0]),
                    Integer.parseInt(quincena.split("/")[1]), 1);
        }
        else{
            fechaBase = LocalDate.of(Integer.parseInt(quincena.split("/")[0]),
                    Integer.parseInt(quincena.split("/")[1]), 16);
        }
        // Se hace Set de los datos que no se calculan
        pago.setAnio(Integer.parseInt(quincena.split("/")[0]));
        pago.setMes(Integer.parseInt(quincena.split("/")[1]));
        pago.setQuincena(Integer.parseInt(quincena.split("/")[2]));
        pago.setId_proveedor(id_proveedor);
        pago.setNombre_proveedor(laboratorioService.getNombreByProveedor(id_proveedor));
        pago.setKls_leche(acopioService.LecheByProveedor(id_proveedor, fechaBase));
        pago.setGrasa(laboratorioService.GrasaByProveedor(id_proveedor));
        pago.setSolidos_totales(laboratorioService.SolidosByProveedor(id_proveedor));
        // Se hace Set de los datos que se calculan
        pago.setNro_dias_envios(acopioService.CountDaysAcopioByProveedor(id_proveedor, fechaBase));
        pago.setProm_diario_kls_leche((double) (pago.getKls_leche() / pago.getNro_dias_envios()));
        pago.setPago_leche(pagoLeche(laboratorioService.getCategoriaByProveedor(id_proveedor), pago.getKls_leche()));
        pago.setPago_grasa(pagoGrasa(pago.getGrasa(), pago.getKls_leche()));
        pago.setPago_solidos_totales(pagoSolidos(pago.getSolidos_totales(), pago.getKls_leche()));
        // Se busca si es que hay un pago anterior para calcular las variaciones
        PagosEntity pagoAnterior = getPagoAnterior(id_proveedor, quincena);
        // Si hay un pago anterior, se calculan las variaciones
        if(pagoAnterior != null){
            pago.setVar_leche(variacionPorcentual(pagoAnterior.getKls_leche(), pago.getKls_leche()));
            pago.setVar_grasa(variacionPorcentual(pagoAnterior.getGrasa(), pago.getGrasa()));
            pago.setVar_solidos_totales(variacionPorcentual(pagoAnterior.getSolidos_totales(), pago.getSolidos_totales()));
        }
        // Si no hay un pago anterior, se deja en 0 las variaciones
        else{
            pago.setVar_leche(0.0);
            pago.setVar_grasa(0.0);
            pago.setVar_solidos_totales(0.0);
        }
        // Se calculan y se hace set de los descuentos
        pago.setDcto_var_leche(dctoPagoLeche(pago.getVar_leche(), pago.getPago_leche()));
        pago.setDcto_var_grasa(dctoPagoGrasa(pago.getVar_grasa(), pago.getPago_leche()));
        pago.setDcto_var_solidos_totales(dctoPagoSolidos(pago.getVar_solidos_totales(), pago.getPago_leche()));
        // Se calculan y se hace set de la bonificacion por frecuencia
        Double Bonificacion = Bonificacion(id_proveedor, pago.getPago_leche(), fechaBase);
        pago.setBonificacion_por_frecuencia(Bonificacion);
        // Se calcula y se hace set del pago por acopio de leche
        Double PagoAcopioLeche = pago.getPago_leche() + pago.getPago_grasa() + pago.getPago_solidos_totales() + Bonificacion;
        // Se calculan y se hace set de los descuentos totales y el pago final
        Double Descuentos = pago.getDcto_var_grasa() + pago.getDcto_var_leche() + pago.getDcto_var_solidos_totales();
        Double PagoTotal = PagoAcopioLeche - Descuentos;
        pago.setPago_total(PagoTotal);
        // Se calcula y se hace set de la retencion
        Double Retencion = CalcularRetencion(laboratorioService.getRetencionByProveedor(id_proveedor), PagoTotal);
        pago.setMonto_retencion(Retencion);
        // Se calcula y se hace set del pago final
        Double PagoFinal = PagoTotal - Retencion;
        pago.setMonto_final(PagoFinal);
        // Se guarda el pago
        pagosRepository.save(pago);
    }
    public void calcularPagos(){
        ArrayList<String> proveedores = acopioService.getProveedores();
        for(String proveedor : proveedores){
            calcularPago(proveedor);
        }
    }
    public ArrayList<PagosEntity> ObtenerData(){
        return (ArrayList<PagosEntity>) pagosRepository.findAll();
    }
}
