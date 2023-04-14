package Tingeso.MilkStgo.Controllers;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Tingeso.MilkStgo.Services.AcopioService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping
public class AcopioController {
    @Autowired
    private AcopioService acopioService;

    @GetMapping("/fileUpload")
    public String main() { return "fileUpload"; }

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        acopioService.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        acopioService.leerCsv(file.getOriginalFilename());
        return "redirect:/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        ArrayList<AcopioEntity> datas = acopioService.ObtenerData();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
    @GetMapping("/countAcopio/{id_proveedor}/{anio}/{mes}/{dia}/{turno}")
    public Integer countAcopio(@PathVariable("id_proveedor") String id_proveedor,
                                @PathVariable("anio") String anio,
                                @PathVariable("mes") String mes,
                                @PathVariable("dia") String dia,
                                @PathVariable("turno") String turno){
        LocalDate fecha = LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
        return acopioService.countAcopioSinceFecha(fecha, turno, id_proveedor);
    }
    @GetMapping("/countDaysAcopioByProveedor/{id_proveedor}")
    public ResponseEntity<Integer> countDaysAcopioByProveedor(@PathVariable("id_proveedor") String id_proveedor){
        return ResponseEntity.ok(acopioService.countDaysAcopioByProveedor(id_proveedor, LocalDate.of(2023, 3, 18)));
    }

    @GetMapping("/PromLecheByProveedor/{id_proveedor}")
    public ResponseEntity<Double> PromLecheByProveedor(@PathVariable("id_proveedor") String id_proveedor){
        LocalDate fecha = LocalDate.of(2023, 3, 18);
        return ResponseEntity.ok(acopioService.lecheByProveedor(id_proveedor, fecha).doubleValue() /
                acopioService.countDaysAcopioByProveedor(id_proveedor, fecha).doubleValue());
    }
}
