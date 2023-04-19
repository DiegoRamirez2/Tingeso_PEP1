package tingeso.milkstgo.services;

import tingeso.milkstgo.entities.PagosEntity;
import tingeso.milkstgo.repositories.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagosService {
    @Autowired
    private PagosRepository pagosRepository;
    @Autowired
    private LaboratorioService laboratorioService;
    @Autowired
    private AcopioService acopioService;
    @Autowired
    private ProveedorService proveedorService;
    public Integer pagoLeche(String categoria, Integer klsLeche){
        return switch (categoria) {
            case "A" -> klsLeche * 700;
            case "B" -> klsLeche * 500;
            case "C" -> klsLeche * 400;
            case "D" -> klsLeche * 250;
            default -> 0;
        };
    }
    public Integer pagoGrasa(Integer grasa, Integer klsLeche){
        if(grasa <= 20){
            return klsLeche * 30;
        }
        else if(grasa <= 45){
            return klsLeche * 80;
        }
        else {
            return klsLeche * 120;
        }
    }
    public Integer pagoSolidos(Integer solidos, Integer klsLeche){
        if(solidos <= 7){
            return klsLeche * -130;
        }
        else if(solidos <= 18){
            return klsLeche * -90;
        }
        else if(solidos <= 35) {
            return klsLeche * 95;
        }
        return klsLeche * 150;
    }
    public Double bonificacion(String idProveedor, Integer pagoLeche, LocalDate fecha){
        Integer m = acopioService.countAcopioSinceFecha(fecha, "M", idProveedor);
        Integer t = acopioService.countAcopioSinceFecha(fecha, "T", idProveedor);
        if(m >= 10 && t >= 10){
            return pagoLeche * 0.2;}
        else if(m >= 10){
            return pagoLeche * 0.12;}
        else if(t >= 10){
            return pagoLeche * 0.08;}
        else{
            return 0.0;}
    }
    public Double dctoPagoLeche(Double variacion, Integer pagoLeche) {
        double dctoLeche = 0.0;
        if(variacion > 0 && variacion <= 8){
            dctoLeche = pagoLeche * 0.0;}
        else if(variacion > 8 && variacion <= 25){
            dctoLeche = pagoLeche * 0.07;}
        else if(variacion > 25 && variacion <= 45){
            dctoLeche = pagoLeche * 0.15;}
        else if(variacion > 45){
            dctoLeche = pagoLeche * 0.3;}
        dctoLeche = Math.round(dctoLeche * 100.0) / 100.0;
        return dctoLeche;
    }
    public Double dctoPagoGrasa(Double variacion, Integer pagoLeche) {
        double dctoGrasa = 0.0;
        if (variacion > 0 && variacion <= 15) {
            dctoGrasa = 0.0 * pagoLeche;}
        if (variacion >= 16 && variacion <= 25) {
            dctoGrasa = 0.12 * pagoLeche;}
        if (variacion >= 26 && variacion <= 40) {
            dctoGrasa = 0.20 * pagoLeche;}
        if (variacion >= 41) {
            dctoGrasa = 0.3 * pagoLeche;
        }
        dctoGrasa = Math.round(dctoGrasa * 100.0) / 100.0;
        return dctoGrasa;
    }
    public Double dctoPagoSolidos(Double variacion, Integer pagoLeche) {
        double dctoSolidos = 0.0;
        if (variacion > 0 && variacion <= 6) {
            dctoSolidos = 0.0 * pagoLeche;}
        if (variacion >= 7 && variacion <= 12) {
            dctoSolidos = 0.18 * pagoLeche;}
        if (variacion >= 13 && variacion <= 35) {
            dctoSolidos = 0.27 * pagoLeche;}
        if (variacion >= 36) {
            dctoSolidos = 0.45 * pagoLeche;}
        dctoSolidos = Math.round(dctoSolidos * 100.0) / 100.0;
        return dctoSolidos;
    }
    public Double calcularRetencion(String retencion, Double pagototal) {
        double montoRetencion = 0.0;
        if (retencion.equals("Si") && pagototal > 950000.0) {
            montoRetencion = pagototal * 0.13;}
        montoRetencion = Math.round(montoRetencion * 100.0) / 100.0;
        return montoRetencion;
    }
    public Double variacionPorcentual(Integer valorAntiguo, Integer valorNuevo) {
        double variacion = (valorAntiguo.doubleValue() / valorNuevo.doubleValue()) * 100;
        variacion = Math.round(variacion * 100.0) / 100.0;
        return variacion;
    }
    public String getQuincena(LocalDate fecha){
        int dia = fecha.getDayOfMonth();
        if(dia <= 15){
            return fecha.getYear() + "/" + fecha.getMonthValue() + "/1";
        }else{
            return fecha.getYear() + "/" + fecha.getMonthValue() + "/2";
        }
    }
    public PagosEntity getPagoAnterior(String idProveedor, String quincena){
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
        return pagosRepository.findPagosByQuincena(idProveedor, anio, mes, quincenaAnterior);
    }
    public void calcularPago(String idProveedor, LocalDate fecha){
        Integer anio = Integer.parseInt(getQuincena(fecha).split("/")[0]);
        Integer mes = Integer.parseInt(getQuincena(fecha).split("/")[1]);
        Integer quincena1 = Integer.parseInt(getQuincena(fecha).split("/")[2]);
        if(!existePago(idProveedor, anio, mes, quincena1)){
            PagosEntity pago = new PagosEntity();
            String quincena = getQuincena(fecha);
            LocalDate fechaBase;
            // Dependiendo de la quincena, se determina si se empieza a buscar desde el 1 o desde el 16
            if(quincena.split("/")[2].equals("1")){
                fechaBase = LocalDate.of(Integer.parseInt(quincena.split("/")[0]), Integer.parseInt(quincena.split("/")[1]), 1);
            } else{
                fechaBase = LocalDate.of(Integer.parseInt(quincena.split("/")[0]), Integer.parseInt(quincena.split("/")[1]), 16);
            }
            // Se hace Set de los datos que no se calculan
            pago.setAnio(Integer.parseInt(quincena.split("/")[0]));
            pago.setMes(Integer.parseInt(quincena.split("/")[1]));
            pago.setQuincena(Integer.parseInt(quincena.split("/")[2]));
            pago.setIdProveedor(idProveedor);
            pago.setNombreProveedor(proveedorService.findByCodigo(Long.valueOf(idProveedor)).getNombre());
            if(acopioService.lecheByProveedor(idProveedor, fechaBase) != null
                    && laboratorioService.porcentajeGrasaByProveedor(Long.valueOf(idProveedor)) != null
                    && laboratorioService.porcentajeSolidosByProveedor(Long.valueOf(idProveedor)) != null){
                pago.setKlsLeche(acopioService.lecheByProveedor(idProveedor, fechaBase));
                pago.setGrasa(laboratorioService.porcentajeGrasaByProveedor(Long.valueOf(idProveedor)).intValue());
                pago.setSolidosTotales(laboratorioService.porcentajeSolidosByProveedor(Long.valueOf(idProveedor)).intValue());
                // Se hace Set de los datos que se calculan
                pago.setNroDiasEnvios(acopioService.countDaysAcopioByProveedor(idProveedor, fechaBase));
                pago.setPromDiarioKlsLeche(Math.round((double) pago.getKlsLeche() / pago.getNroDiasEnvios() * 100.0) / 100.0);
                pago.setPagoLeche(pagoLeche(laboratorioService.getCategoriaByProveedor(Long.valueOf(idProveedor)), pago.getKlsLeche()));
                pago.setPagoGrasa(pagoGrasa(pago.getGrasa(), pago.getKlsLeche()));
                pago.setPagoSolidosTotales(pagoSolidos(pago.getSolidosTotales(), pago.getKlsLeche()));
                // Se busca si es que hay un pago anterior para calcular las variaciones
                PagosEntity pagoAnterior = getPagoAnterior(idProveedor, quincena);
                // Si hay un pago anterior, se calculan las variaciones
                if(pagoAnterior != null){
                    pago.setVarLeche(variacionPorcentual(pagoAnterior.getKlsLeche(), pago.getKlsLeche()));
                    pago.setVarGrasa(variacionPorcentual(pagoAnterior.getGrasa(), pago.getGrasa()));
                    pago.setVarSolidosTotales(variacionPorcentual(pagoAnterior.getSolidosTotales(), pago.getSolidosTotales()));
                }
                // Si no hay un pago anterior, se deja en 0 las variaciones
                else{
                    pago.setVarLeche(0.0);
                    pago.setVarGrasa(0.0);
                    pago.setVarSolidosTotales(0.0);
                }
                // Se calculan y se hace set de los descuentos
                pago.setDctoVarLeche(dctoPagoLeche(pago.getVarLeche(), pago.getPagoLeche()));
                pago.setDctoVarGrasa(dctoPagoGrasa(pago.getVarGrasa(), pago.getPagoLeche()));
                pago.setDctoVarSolidosTotales(dctoPagoSolidos(pago.getVarSolidosTotales(), pago.getPagoLeche()));
                // Se calculan y se hace set de la bonificacion por frecuencia
                Double bonificacion = bonificacion(idProveedor, pago.getPagoLeche(), fechaBase);
                pago.setBonificacionPorFrecuencia(bonificacion);
                // Se calcula y se hace set del pago por acopio de leche
                Double pagoAcopioLeche = pago.getPagoLeche() + pago.getPagoGrasa() + pago.getPagoSolidosTotales() + bonificacion;
                // Se calculan y se hace set de los descuentos totales y el pago final
                Double descuentos = pago.getDctoVarGrasa() + pago.getDctoVarLeche() + pago.getDctoVarSolidosTotales();
                Double pagoTotal = pagoAcopioLeche - descuentos;
                pago.setPagoTotal(pagoTotal);
                // Se calcula y se hace set de la retencion
                Double retencion = calcularRetencion(laboratorioService.getRetencionByProveedor(Long.valueOf(idProveedor)), pagoTotal);
                pago.setMontoRetencion(retencion);
                // Se calcula y se hace set del pago final
                Double pagoFinal = pagoTotal - retencion;
                pago.setMontoFinal(pagoFinal);
            }else{
                pago.setKlsLeche(0);
                pago.setGrasa(0);
                pago.setSolidosTotales(0);
                pago.setNroDiasEnvios(0);
                pago.setPromDiarioKlsLeche(0.0);
                pago.setPagoLeche(0);
                pago.setPagoGrasa(0);
                pago.setPagoSolidosTotales(0);
                pago.setVarLeche(0.0);
                pago.setVarGrasa(0.0);
                pago.setVarSolidosTotales(0.0);
                pago.setDctoVarLeche(0.0);
                pago.setDctoVarGrasa(0.0);
                pago.setDctoVarSolidosTotales(0.0);
                pago.setBonificacionPorFrecuencia(0.0);
                pago.setPagoTotal(0.0);
                pago.setMontoRetencion(0.0);
                pago.setMontoFinal(0.0);
            }
            // Se guarda el pago
            pagosRepository.save(pago);
        }
    }
    public List<String> getProveedores(){
        return acopioService.getProveedores();
    }
    public List<PagosEntity> obtenerData(){
        return pagosRepository.findAll();
    }
    public void guardarData(PagosEntity pago){
        pagosRepository.save(pago);
    }
    public void eliminarData(PagosEntity pago){
        pagosRepository.delete(pago);}
    public boolean existePago(String idProveedor, Integer anio, Integer mes, Integer quincena){
        return pagosRepository.findPagosByQuincena(idProveedor, anio, mes, quincena) != null;
    }
    public PagosEntity getPagoByIdProveedorAndQuincena(String idProveedor, Integer anio, Integer mes, Integer quincena){
        return pagosRepository.findPagosByQuincena(idProveedor, anio, mes, quincena);
    }
}
