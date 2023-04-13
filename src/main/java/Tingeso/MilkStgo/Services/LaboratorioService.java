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

    public Integer GrasaByProveedor(String id_proveedor) {
        return laboratorioRepository.findByProveedorId(Long.parseLong(id_proveedor)).getPorcentaje_grasa().intValue();
    }
    public Integer SolidosByProveedor(String id_proveedor) {
        return laboratorioRepository.findByProveedorId(Long.parseLong(id_proveedor)).getPorcentaje_solidos_totales().intValue();
    }
    public String getRetencionByProveedor(String idProveedor) {
        return laboratorioRepository.findRetencionByProveedorId(Long.parseLong(idProveedor)).getRetencion();
    }
    public String getNombreByProveedor(String idProveedor) {
        return laboratorioRepository.findRetencionByProveedorId(Long.parseLong(idProveedor)).getNombre();
    }
    public String getCategoriaByProveedor(String idProveedor) {
        return laboratorioRepository.findRetencionByProveedorId(Long.parseLong(idProveedor)).getCategoria();
    }
    public ArrayList<LaboratorioEntity> ObtenerData(){
        return (ArrayList<LaboratorioEntity>) laboratorioRepository.findAll();
    }
    @Generated
    public String Guardar(MultipartFile file){
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
    public String leerCsv(String direccion){
        String texto = "";
        String proveedores = "";
        BufferedReader bf = null;
        laboratorioRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            bf.readLine();
            while((bfRead = bf.readLine()) != null){
                String id_proveedor = bfRead.split(";")[0];
                String grasa = bfRead.split(";")[1];
                String solidos = bfRead.split(";")[2];
                ProveedorEntity find_proveedor = proveedorService.findByCodigo(id_proveedor);
                if(find_proveedor != null) {
                    guardarData(find_proveedor, grasa,solidos);
                }
                else {
                    proveedores = proveedores  + id_proveedor + " - ";
                }
                temp = temp + "\n" + bfRead;
            }
            texto = temp;
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
        if(proveedores.equals("")){
            return "Se leyo exitosamente el archivo";
        }
        else{
            return "El archivo se leyo exitosamente, pero no se pudo agregar los siguientes Proveedores: \n" + proveedores;
        }
    }
    public void guardarData(ProveedorEntity proveedor, String grasa, String solido){
        LaboratorioEntity data = new LaboratorioEntity();
        try{
            data.setProveedor(proveedor);
            data.setPorcentaje_grasa(Double.valueOf(grasa));
            data.setPorcentaje_solidos_totales(Double.valueOf(solido));
        } catch (Exception e){
            System.err.println("Error al guardar los datos");
        }
        laboratorioRepository.save(data);
    }
}
