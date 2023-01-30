package apap.tugas.sisdm.controller;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.PresensiModel;
import apap.tugas.sisdm.model.TugasModel;
import apap.tugas.sisdm.service.KaryawanService;
import apap.tugas.sisdm.service.PresensiService;
import apap.tugas.sisdm.service.TugasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TugasController {
    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiService presensiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("/tambah-tugas")
    public String addTugasForm(Model model) {
        TugasModel tugas = new TugasModel();

        model.addAttribute("tugas", tugas);
        return "form-add-tugas";
    }

    @PostMapping(value="/tambah-tugas", params = {"save"})
    public String addTugasSubmit(@ModelAttribute TugasModel tugas, Model model) {
        tugasService.addTugas(tugas);
        System.out.println(tugas.getNamaTugas());
        model.addAttribute("namaTugas", tugas.getNamaTugas());
        return "add-tugas";
    }

    @GetMapping("filter-tugas")
    public String listTugas(Model model) {
        List<TugasModel> listTugas = tugasService.getListTugas();
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listTugas", listTugas);
        model.addAttribute("listKaryawanExisting", listKaryawan);
        return "viewall-tugas";
    }


    @GetMapping(value="filter-tugas", params = {"filter"})
    public String listTugasFiltered(@RequestParam(value = "id-karyawan") Long idKaryawan, @RequestParam(value = "status") int statusTugas, Model model) {
        List<TugasModel> listTugasFiltered = tugasService.getListTugasFiltered(idKaryawan, statusTugas);
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();

        if (listTugasFiltered != null){
            model.addAttribute("listKaryawanExisting", listKaryawan);
            model.addAttribute("listTugas", listTugasFiltered);
            return "viewall-tugas";
        }else {

            return null;
        }

    }



}
