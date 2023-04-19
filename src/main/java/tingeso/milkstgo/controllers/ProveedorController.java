package tingeso.milkstgo.controllers;

import tingeso.milkstgo.entities.ProveedorEntity;
import tingeso.milkstgo.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedor", proveedores);
        return "index";
    }
    @GetMapping("/nuevo-proveedor")
    public String proveedor() { return "nuevo-proveedor";}

    @PostMapping("/nuevo-proveedor")
    public String nuevoProveedor(@RequestParam("id_proveedor") String idProveedor,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("categoria") String categoria,
                                 @RequestParam("retencion") String retencion){
        proveedorService.guardarProveedor(Long.parseLong(idProveedor), nombre, categoria, retencion);
        return "redirect:nuevo-proveedor";
    }
}
