package tingeso.milkstgo.controllers;

import tingeso.milkstgo.entities.AcopioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tingeso.milkstgo.services.AcopioService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

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
        List<AcopioEntity> datas = acopioService.obtenerData();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
    @GetMapping("/countAcopio/{id_proveedor}/{anio}/{mes}/{dia}/{turno}")
    public Integer countAcopio(@PathVariable("id_proveedor") String idProveedor,
                                @PathVariable("anio") String anio,
                                @PathVariable("mes") String mes,
                                @PathVariable("dia") String dia,
                                @PathVariable("turno") String turno){
        LocalDate fecha = LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
        return acopioService.countAcopioSinceFecha(fecha, turno, idProveedor);
    }
}
