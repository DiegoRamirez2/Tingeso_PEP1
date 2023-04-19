package tingeso.milkstgo.services;

import tingeso.milkstgo.entities.LaboratorioEntity;
import tingeso.milkstgo.entities.ProveedorEntity;
import tingeso.milkstgo.repositories.LaboratorioRepository;
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
import java.util.List;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;
    @Autowired
    private ProveedorService proveedorService;
    private final Logger logg = LoggerFactory.getLogger(LaboratorioService.class);

    public Double porcentajeGrasaByProveedor(Long idProveedor) {
        return laboratorioRepository.findByProveedorId(idProveedor).getPorcentajeGrasa();
    }
    public Double porcentajeSolidosByProveedor(Long idProveedor) {
        return laboratorioRepository.findByProveedorId(idProveedor).getPorcentajeSolidosTotales();
    }
    public String getRetencionByProveedor(Long idProveedor) {
        return laboratorioRepository.findRetencionByProveedorId(idProveedor).getRetencion();
    }
    public String getNombreByProveedor(Long idProveedor) {
        return laboratorioRepository.findRetencionByProveedorId(idProveedor).getNombre();
    }
    public String getCategoriaByProveedor(Long idProveedor) {
        return laboratorioRepository.findRetencionByProveedorId(idProveedor).getCategoria();
    }
    public List<LaboratorioEntity> obtenerData(){
        return laboratorioRepository.findAll();
    }
    public LaboratorioEntity getLaboratorioByProveedor(Long idProveedor){
        return laboratorioRepository.findByProveedorId(idProveedor);
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
        if(filename != null && !filename.isEmpty()){
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
    public void leerCsv(String direccion){
        laboratorioRepository.deleteAll();
        boolean primeraLineaLeida = false;
        try(BufferedReader bf = new BufferedReader(new FileReader(direccion))){
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                if(!primeraLineaLeida){
                    primeraLineaLeida = true;
                    continue;
                }
                String idProveedor = bfRead.split(";")[0];
                String grasa = bfRead.split(";")[1];
                String solidos = bfRead.split(";")[2];
                ProveedorEntity findProveedor = proveedorService.findByCodigo(Long.valueOf(idProveedor));
                if(findProveedor != null) {
                    guardarDataDB(findProveedor, grasa,solidos);
                }
            }
            logg.info("Archivo leido exitosamente");
        }catch(Exception e) {
            logg.error("No se encontro el archivo");
        }
    }
    public void guardarDataDB(ProveedorEntity proveedor, String grasa, String solido){
        LaboratorioEntity data = new LaboratorioEntity();
        data.setProveedor(proveedor);
        data.setPorcentajeGrasa(Double.valueOf(grasa));
        data.setPorcentajeSolidosTotales(Double.valueOf(solido));
        laboratorioRepository.save(data);
    }
}