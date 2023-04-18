package Tingeso.MilkStgo.Services;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import Tingeso.MilkStgo.Repositories.AcopioRepository;
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
import java.util.ArrayList;

@Service
public class AcopioService {
    @Autowired
    private AcopioRepository AcopioRepository;
    private final Logger logg = LoggerFactory.getLogger(AcopioService.class);
    public ArrayList<AcopioEntity> ObtenerData(){
        return (ArrayList<AcopioEntity>) AcopioRepository.findAll();
    }

    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
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
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String direccion){
        BufferedReader bf = null;
        AcopioRepository.deleteAll();
        try{
            String temp = "";
            String bfRead;
            bf = new BufferedReader(new FileReader(direccion));
            while((bfRead = bf.readLine()) != null){
                guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], bfRead.split(";")[3]);
                temp = temp + "\n" + bfRead;
                }
            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }
    public void guardarDataDB(String fecha, String turno, String proveedor, String kls_leche){
        LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if(getAcopioByFechaTurnoProveedor(date, turno, proveedor) == null){
            AcopioEntity data = new AcopioEntity();
            data.setFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            data.setTurno(turno);
            data.setProveedor(proveedor);
            data.setKls_leche(Integer.parseInt(kls_leche));
            guardarData(data);
        }
    }
    public void guardarData(AcopioEntity acopio){
        AcopioRepository.save(acopio);
    }
    public void eliminarData(AcopioEntity acopio){
        AcopioRepository.delete(acopio);
    }
    public Integer countAcopioSinceFecha(LocalDate fecha, String turno, String proveedor){
        return AcopioRepository.findAcopioSinceFecha(fecha, turno, proveedor).size();
    }
    public Integer countDaysAcopioByProveedor(String proveedor, LocalDate fecha){
        return AcopioRepository.CountDaysAcopioByProveedor(proveedor, fecha);
    }
    public Integer lecheByProveedor(String proveedor, LocalDate fecha){
        return AcopioRepository.LecheByProveedor(proveedor, fecha);
    }
    public ArrayList<String> getProveedores() {return AcopioRepository.getProveedores();}
    public AcopioEntity getAcopioByFechaTurnoProveedor(LocalDate fecha, String turno, String proveedor){
        return AcopioRepository.findAcopioByFechaTurnoProveedor(fecha, turno, proveedor);
    }
}