package Tingeso.MilkStgo.Controllers;

import Tingeso.MilkStgo.Entities.PagosEntity;
import Tingeso.MilkStgo.Services.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping
public class PagosController {
    @Autowired
    private PagosService pagosService;

    @GetMapping("/pagos")
    public String pagos(Model model) {
        ArrayList<PagosEntity> pagos = pagosService.ObtenerData();
        model.addAttribute("pagos", pagos);
        return "pagos";
    }
    @GetMapping("/calcularPagos")
    public String calcularPagos(){
        pagosService.calcularPagos();
        return "redirect:/pagos";
    }
}
