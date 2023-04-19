package tingeso.milkstgo.services;

import tingeso.milkstgo.entities.AcopioEntity;
import tingeso.milkstgo.repositories.AcopioRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AcopioService {
    @Autowired
    private AcopioRepository acopioRepository;
    private final Logger logg = LoggerFactory.getLogger(AcopioService.class);
    public List<AcopioEntity> obtenerData(){
        return acopioRepository.findAll();
    }

    @Generated
    public void guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null && !file.isEmpty()){
            try{
                byte [] bytes = file.getBytes();
                Path path  = Paths.get(file.getOriginalFilename());
                Files.write(path, bytes);
                logg.info("Archivo guardado");
            }
            catch (IOException e){
                logg.error("ERROR", e);
            }
        }
    }

    @Generated
    public void leerCsv(String direccion) {
        acopioRepository.deleteAll();
        boolean primeraLineaLeida = false;
        try (BufferedReader bf = new BufferedReader(new FileReader(direccion))) {
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                if (!primeraLineaLeida) {
                    primeraLineaLeida = true;
                    continue;
                }
                guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], bfRead.split(";")[3]);
            }
            logg.info("Archivo leído exitosamente");
        } catch (IOException e) {
            logg.error("No se encontró el archivo", e);
        }
    }

    public void guardarDataDB(String fecha, String turno, String proveedor, String klsLeche){
        LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if(getAcopioByFechaTurnoProveedor(date, turno, proveedor) == null){
            AcopioEntity data = new AcopioEntity();
            data.setFecha(date);
            data.setTurno(turno);
            data.setProveedor(proveedor);
            data.setKlsLeche(Integer.parseInt(klsLeche));
            guardarData(data);
        }
    }
    public void guardarData(AcopioEntity acopio){
        acopioRepository.save(acopio);
    }
    public void eliminarData(AcopioEntity acopio){
        acopioRepository.delete(acopio);
    }
    public Integer countAcopioSinceFecha(LocalDate fecha, String turno, String proveedor){
        return acopioRepository.findAcopioSinceFecha(fecha, turno, proveedor).size();
    }
    public Integer countDaysAcopioByProveedor(String proveedor, LocalDate fecha){
        return acopioRepository.countDaysAcopioByProveedor(proveedor, fecha);
    }
    public Integer lecheByProveedor(String proveedor, LocalDate fecha){
        return acopioRepository.lecheByProveedor(proveedor, fecha);
    }
    public List<String> getProveedores() {return acopioRepository.getProveedores();}
    public AcopioEntity getAcopioByFechaTurnoProveedor(LocalDate fecha, String turno, String proveedor){
        return acopioRepository.findAcopioByFechaTurnoProveedor(fecha, turno, proveedor);
    }
}