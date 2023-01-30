package apap.tugas.sisdm.controller;

import apap.tugas.sisdm.model.*;
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
public class PresensiController {
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiService presensiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @GetMapping("presensi/tambah")
    public String addPresensiForm(Model model) {
        PresensiModel presensi= new PresensiModel();
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        KaryawanModel karyawanNew = new KaryawanModel();

        List<TugasModel> listTugas = tugasService.getListTugas();
        List<TugasModel> listTugasNew = new ArrayList<>();

        presensi.setListTugas(listTugasNew);
        presensi.getListTugas().add(new TugasModel());
        presensi.setKaryawan(karyawanNew);

        model.addAttribute("listTugasExisting", listTugas);
        model.addAttribute("presensi", presensi);
        model.addAttribute("listKaryawanExisting", listKaryawan);

        return "form-add-presensi";
    }

    @PostMapping(value="presensi/tambah", params = {"save"})
    public String addPresensiSubmit(@ModelAttribute PresensiModel presensi, Model model) {
        if(presensi.getWaktuMasuk().compareTo(LocalTime.of(7,0,0)) > 0){
            presensi.setStatusPresensi(0);
        } else {presensi.setStatusPresensi(1);}

        KaryawanModel karyawan = karyawanService.getKaryawan(presensi.getKaryawan().getIdKaryawan());
        presensi.setKaryawan(karyawan);

        if(presensi.getListTugas() == null){
            presensi.setListTugas(new ArrayList<>());
        }
        presensiService.addPresensi(presensi);

        for (int i = 0; i < presensi.getListTugas().size(); i++) {
            TugasModel tugas = tugasService.getTugasById(presensi.getListTugas().get(i).getIdTugas());
            tugas.setPresensi(presensi);
            tugas.setStatusTugas(presensi.getListTugas().get(i).getStatusTugas());
            tugasService.updateTugas(tugas);
        }


        String fullName = karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang();
        model.addAttribute("fullName", fullName);
        model.addAttribute("presensi", presensi);
        return "add-presensi";
    }

    @PostMapping(value = "presensi/tambah", params = {"addRow"})
    private String addRowTugasMultiple(
            @ModelAttribute PresensiModel presensi,
            Model model
    ){
        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0 ){
            presensi.setListTugas(new ArrayList<>());
        }
        presensi.getListTugas().add(new TugasModel());
        List<TugasModel> listTugas = tugasService.getListTugas();

        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawanExisting", listKaryawan);

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);
        return "form-add-presensi";
    }

    @PostMapping(value = "presensi/tambah", params = {"deleteRow"})
    private String deleteRowTugasMultiple(
            @ModelAttribute PresensiModel presensi,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        presensi.getListTugas().remove(rowId.intValue());

        List<TugasModel> listTugas = tugasService.getListTugas();
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawanExisting", listKaryawan);

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);
        return "form-add-presensi";
    }

    @GetMapping("presensi/viewall")
    public String listPresensi(Model model) {
        List<PresensiModel> listPresensi = presensiService.getListPresensi();

        model.addAttribute("listPresensi", listPresensi);
        return "viewall-presensi";
    }

    @GetMapping("presensi/{idPresensi}")
    public String viewDetailPresensi(@PathVariable Long idPresensi, Model model) {
        PresensiModel presensi = presensiService.getPresensi(idPresensi);

        model.addAttribute("presensi", presensi);
        return "detail-presensi";
    }

    @GetMapping("presensi/{idPresensi}/ubah")
    public String formUpdatePresensi(@PathVariable Long idPresensi, Model model) {
        PresensiModel presensi = presensiService.getPresensi(idPresensi);
        List<TugasModel> listTugas = tugasService.getListTugas();
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);
        return "form-update-presensi";
    }

    @PostMapping(value = "presensi/{idPresensi}/ubah")
    public String updatePresensiSubmitPage(@ModelAttribute PresensiModel presensi, Model model) {
        PresensiModel updatedPresensi = presensiService.updatePresensi(presensi);
        for (int i = 0; i < presensi.getListTugas().size(); i++) {
            TugasModel tugas = tugasService.getTugasById(presensi.getListTugas().get(i).getIdTugas());
            tugas.setPresensi(presensi);
            tugas.setStatusTugas(presensi.getListTugas().get(i).getStatusTugas());
            tugasService.updateTugas(tugas);
        }
        model.addAttribute("presensi", presensi);
        return "update-presensi";
    }

    @PostMapping(value = "presensi/{idPresensi}/ubah", params = {"addRow"})
    private String addRowTugasMultipleUp(
            @ModelAttribute PresensiModel presensi,
            Model model
    ){
        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0 ){
            presensi.setListTugas(new ArrayList<>());
        }
        presensi.getListTugas().add(new TugasModel());
        List<TugasModel> listTugas = tugasService.getListTugas();

        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawanExisting", listKaryawan);

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);
        return "form-update-presensi";
    }

    @PostMapping(value = "presensi/{idPresensi}/ubah", params = {"deleteRow"})
    private String deleteRowTugasMultipleUp(
            @ModelAttribute PresensiModel presensi,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        presensi.getListTugas().remove(rowId.intValue());

        List<TugasModel> listTugas = tugasService.getListTugas();
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawanExisting", listKaryawan);

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasExisting", listTugas);
        return "form-update-presensi";
    }



}
