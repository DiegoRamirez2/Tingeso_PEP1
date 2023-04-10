package Tingeso.MilkStgo.Controllers;

import Tingeso.MilkStgo.Entities.ProveedorEntity;
import Tingeso.MilkStgo.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/listar")
    public String listar(Model model) {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedor", proveedores);
        return "index";
    }
    @GetMapping("/nuevo-proveedor")
    public String proveedor() { return "nuevo-proveedor";}

    @PostMapping("/nuevo-proveedor")
    public String nuevoProveedor(@RequestParam("id_proveedor") String id_proveedor,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("categoria") String categoria,
                                 @RequestParam("retencion") String retencion){
        proveedorService.guardarProveedor(Long.parseLong(id_proveedor), nombre, categoria, retencion);
        System.out.println("Proveedor guardado");
        return "redirect:nuevo-proveedor";
    }
}
