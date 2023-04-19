package tingeso.milkstgo.controllers;

import tingeso.milkstgo.entities.PagosEntity;
import tingeso.milkstgo.services.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class PagosController {
    @Autowired
    private PagosService pagosService;

    @GetMapping("/pagos")
    public String pagos(Model model) {
        List<PagosEntity> pagos = pagosService.obtenerData();
        model.addAttribute("pagos", pagos);
        return "pagos";
    }
    @GetMapping("/calcularPagos")
    public String calcularPagos(){
        List<String> proveedores = pagosService.getProveedores();
        for(String proveedor : proveedores){
            pagosService.calcularPago(proveedor, LocalDate.now());
        }
        return "redirect:/pagos";
    }
}
