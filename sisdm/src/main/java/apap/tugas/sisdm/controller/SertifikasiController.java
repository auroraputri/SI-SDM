package apap.tugas.sisdm.controller;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.model.SertifikasiModel;
import apap.tugas.sisdm.service.KaryawanService;
import apap.tugas.sisdm.service.SertifikasiKarService;
import apap.tugas.sisdm.service.SertifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SertifikasiController {

    @Qualifier("sertifikasiKarServiceImpl")
    @Autowired
    private SertifikasiKarService sertifikasiKarService;

    @Qualifier("sertifikasiServiceImpl")
    @Autowired
    private SertifikasiService sertifikasiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("filter-sertifikasi")
    public String listSertifikasi(Model model) {
        List<SertifikasiKaryawanModel> listSertifikasiKar = sertifikasiKarService.getListSertifikasiKar();
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();
        model.addAttribute("listSertifikasi", listSertifikasi);
        model.addAttribute("listSertifikasiKar", listSertifikasiKar);
        return "viewall-sertifikasi";
    }


    @GetMapping(value="filter-sertifikasi", params = {"filter"})
    public String listSertifikasiFiltered(@RequestParam(value = "id-sertifikasi") Long idSertifikasi, Model model) {
        List<SertifikasiKaryawanModel> listSertifikasiKarFiltered = sertifikasiKarService.getListSertifikasiKarFiltered(idSertifikasi);
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        if (listSertifikasiKarFiltered != null){
            model.addAttribute("listSertifikasi", listSertifikasi);
            model.addAttribute("listSertifikasiKar", listSertifikasiKarFiltered);
            return "viewall-sertifikasi";
        }else {

            return null;
        }

    }
}
