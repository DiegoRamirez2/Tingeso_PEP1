package Tingeso.MilkStgo.Services;

import Tingeso.MilkStgo.Entities.LaboratorioEntity;
import Tingeso.MilkStgo.Entities.ProveedorEntity;
import Tingeso.MilkStgo.Repositories.LaboratorioRepository;
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
import java.util.ArrayList;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;
    @Autowired
    private ProveedorService proveedorService;
    private final Logger logg = LoggerFactory.getLogger(LaboratorioService.class);

    public Integer porcentajeGrasaByProveedor(Long id_proveedor) {
        return laboratorioRepository.findByProveedorId(id_proveedor).getPorcentaje_grasa().intValue();
    }
    public Integer porcentajeSolidosByProveedor(Long id_proveedor) {
        return laboratorioRepository.findByProveedorId(id_proveedor).getPorcentaje_solidos_totales().intValue();
    }
    public String getRetencionByProveedor(Long id_proveedor) {
        return laboratorioRepository.findRetencionByProveedorId(id_proveedor).getRetencion();
    }
    public String getNombreByProveedor(Long id_proveedor) {
        return laboratorioRepository.findRetencionByProveedorId(id_proveedor).getNombre();
    }
    public String getCategoriaByProveedor(Long id_proveedor) {
        return laboratorioRepository.findRetencionByProveedorId(id_proveedor).getCategoria();
    }
    public ArrayList<LaboratorioEntity> obtenerData(){
        return (ArrayList<LaboratorioEntity>) laboratorioRepository.findAll();
    }
    public LaboratorioEntity getLaboratorioByProveedor(Long id_proveedor){
        return laboratorioRepository.findByProveedorId(id_proveedor);
    }
    public void guardarData(LaboratorioEntity laboratorioEntity){
        laboratorioRepository.save(laboratorioEntity);
    }
    public void eliminarData(LaboratorioEntity laboratorio) {
        laboratorioRepository.delete(laboratorio);
    }
    @Generated
    public void guardar(MultipartFile file){
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
        }
    }
    @Generated
    public void leerCsv(String direccion){
        String proveedores = "";
        BufferedReader bf = null;
        laboratorioRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            bf.readLine();
            while((bfRead = bf.readLine()) != null){
                String id_proveedor = bfRead.split(";")[0];
                String grasa = bfRead.split(";")[1];
                String solidos = bfRead.split(";")[2];
                ProveedorEntity find_proveedor = proveedorService.findByCodigo(Long.valueOf(id_proveedor));
                if(find_proveedor != null) {
                    guardarDataDB(find_proveedor, grasa,solidos);
                }
                else {
                    proveedores = proveedores  + id_proveedor + " - ";
                }
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
    public void guardarDataDB(ProveedorEntity proveedor, String grasa, String solido){
        LaboratorioEntity data = new LaboratorioEntity();
        data.setProveedor(proveedor);
        data.setPorcentaje_grasa(Double.valueOf(grasa));
        data.setPorcentaje_solidos_totales(Double.valueOf(solido));
        laboratorioRepository.save(data);
    }
}