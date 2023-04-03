package Tingeso.MilkStgo.Controllers;

import Tingeso.MilkStgo.Entities.AcopioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import Tingeso.MilkStgo.Services.AcopioService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        acopioService.Guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");

        return "redirect:/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        acopioService.leerCsv("Acopio.csv");
        ArrayList<AcopioEntity> datas = acopioService.ObtenerData();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
}
