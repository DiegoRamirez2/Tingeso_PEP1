package tingeso.milkstgo.controllers;

import tingeso.milkstgo.entities.LaboratorioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tingeso.milkstgo.services.LaboratorioService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping
public class LaboratorioController {
    @Autowired
    private LaboratorioService laboratorioService;

    @GetMapping("/LaboratorioUpload")
    public String main() { return "LaboratorioUpload"; }

    @PostMapping("/LaboratorioUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        laboratorioService.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        laboratorioService.leerCsv(file.getOriginalFilename());
        return "redirect:/LaboratorioUpload";
    }

    @GetMapping("/LaboratorioInformation")
    public String listar(Model model) {
        List<LaboratorioEntity> laboratorio = laboratorioService.obtenerData();
        model.addAttribute("lab", laboratorio);
        return "LaboratorioInformation";
    }

}
