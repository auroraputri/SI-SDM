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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KaryawanController {
    @Qualifier("sertifikasiKarServiceImpl")
    @Autowired
    private SertifikasiKarService sertifikasiKarService;

    @Qualifier("sertifikasiServiceImpl")
    @Autowired
    private SertifikasiService sertifikasiService;
    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @GetMapping("karyawan/tambah")
    public String addKaryawanForm(Model model) {
        KaryawanModel karyawan= new KaryawanModel();
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();
        List<SertifikasiKaryawanModel> listSertifikasiNew = new ArrayList<>();

        karyawan.setSertifikasiKaryawan(listSertifikasiNew);
        karyawan.getSertifikasiKaryawan().add(new SertifikasiKaryawanModel());

        model.addAttribute("listSertifikasiExisting", listSertifikasi);
        model.addAttribute("karyawan", karyawan);
        return "form-add-karyawan";
    }

    @PostMapping(value="karyawan/tambah", params = {"save"})
    public String addKaryawanSubmit(@ModelAttribute KaryawanModel karyawan, Model model) {
        List<SertifikasiKaryawanModel> listSertifikasiKaryawan = karyawan.getSertifikasiKaryawan();
        if(karyawan.getSertifikasiKaryawan() == null){
            karyawan.setListPresensi(new ArrayList<>());
        }else {
            for (int i = 0; i < listSertifikasiKaryawan.size(); i++) {
                listSertifikasiKaryawan.get(i).setKaryawan(karyawan);
                Long idSertifikasi = listSertifikasiKaryawan.get(i).getIdSertifikasiKaryawan().getIdSertifikasi();
                listSertifikasiKaryawan.get(i).setSertifikasi(sertifikasiService.getSertifikasi(idSertifikasi));
                listSertifikasiKaryawan.get(i).setNoSertifikasi("no");
            }
        }

        karyawanService.addKaryawan(karyawan);
        String fullName = karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang();
        System.out.println(fullName);
        model.addAttribute("fullName", fullName);
        return "add-karyawan";
    }

    @PostMapping(value = "karyawan/tambah", params = {"addRow"})
    private String addRowSertifikasiMultiple(
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        if (karyawan.getSertifikasiKaryawan() == null || karyawan.getSertifikasiKaryawan().size() == 0 ){
            karyawan.setSertifikasiKaryawan(new ArrayList<>());
        }
        karyawan.getSertifikasiKaryawan().add(new SertifikasiKaryawanModel());
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);
        return "form-add-karyawan";
    }

    @PostMapping(value = "karyawan/tambah", params = {"deleteRow"})
    private String deleteRowSertifikasiMultiple(
            @ModelAttribute KaryawanModel karyawan,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        karyawan.getSertifikasiKaryawan().remove(rowId.intValue());

        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);
        return "form-add-karyawan";
    }

    @GetMapping("karyawan/viewall")
    public String listKaryawan(Model model) {
        List<KaryawanModel> listKaryawan = karyawanService.getListKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);
        return "viewall-karyawan";
    }

    @GetMapping("karyawan/{idKaryawan}")
    public String viewDetailKaryawan(@PathVariable Long idKaryawan, Model model) {
        KaryawanModel karyawan = karyawanService.getKaryawan(idKaryawan);

        String fullName = karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang();
        String jenisKelamin = "Laki-laki";

        if (karyawan.getJenisKelamin() == 2){
            jenisKelamin = "Perempuan";
        }

        model.addAttribute("jenisKelamin", jenisKelamin);
        model.addAttribute("fullName", fullName);
        model.addAttribute("karyawan", karyawan);
        return "detail-karyawan";
    }

    @GetMapping("karyawan/{idKaryawan}/ubah")
    public String formUpdateKaryawan(@PathVariable Long idKaryawan, Model model) {
        KaryawanModel karyawan = karyawanService.getKaryawan(idKaryawan);
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("listSertifikasiExisting", listSertifikasi);
        model.addAttribute("karyawan", karyawan);
        return "form-update-karyawan";
    }
    @PostMapping(value = "/karyawan/{idKaryawan}/ubah", params = "save")
    public String submitUpdateKaryawan(@ModelAttribute KaryawanModel karyawan, Model model) {
        KaryawanModel oldKar= karyawanService.getKaryawan(karyawan.getIdKaryawan());
        List<SertifikasiKaryawanModel> oldSerKar = sertifikasiKarService.getSertifikasiByIdKaryawan(oldKar);
        List<SertifikasiKaryawanModel> newSerka = karyawan.getSertifikasiKaryawan();
        List<SertifikasiKaryawanModel> delete = new ArrayList<>();
        for (int i = 0; i < oldSerKar.size(); i++) {
            for (int j = 0; j < newSerka.size(); j++) {
                if (oldSerKar.get(i).getIdSertifikasiKaryawan().getIdSertifikasi() == newSerka.get(j).getIdSertifikasiKaryawan().getIdSertifikasi()) {
                    break;
                } if (j == newSerka.size() - 1) {
                    delete.add(oldSerKar.get(i));
                }
            }
        }

        for (SertifikasiKaryawanModel sertif : newSerka) {
            if (!oldSerKar.contains(sertif)) {;
                SertifikasiModel sertifikasi = sertifikasiService.getSertifikasi(sertif.getSertifikasi().getIdSertifikasi());
                sertif.setKaryawan(karyawan);
                sertif.setSertifikasi(sertifikasi);
                sertifikasiKarService.saveSertifikasiKar(sertif);
            }
        }

        if(delete.size() != 0) {
            for (SertifikasiKaryawanModel sertif : delete) {
                sertifikasiKarService.deleteSertifikasiKar(sertif);
            }
        }

        karyawanService.addKaryawan(karyawan);
        String fullName = karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang();
        model.addAttribute("fullName", fullName);

        return "update-karyawan";
    }

    @PostMapping(value = "karyawan/{idKaryawan}/ubah", params = {"addRoww"})
    private String addRowSertifikasiMultipleUp(
            @ModelAttribute KaryawanModel karyawan,
            Model model
    ){
        if (karyawan.getSertifikasiKaryawan() == null || karyawan.getSertifikasiKaryawan().size() == 0 ){
            karyawan.setSertifikasiKaryawan(new ArrayList<>());
        }
        karyawan.getSertifikasiKaryawan().add(new SertifikasiKaryawanModel());
        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);
        return "form-update-karyawan";
    }

    @PostMapping(value = "karyawan/{idKaryawan}/ubah", params = {"deleteRoww"})
    private String deleteRowSertifikasiMultipleUp(
            @ModelAttribute KaryawanModel karyawan,
            @RequestParam("deleteRoww") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        karyawan.getSertifikasiKaryawan().remove(rowId.intValue());

        List<SertifikasiModel> listSertifikasi = sertifikasiService.getListSertifikasi();

        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasiExisting", listSertifikasi);
        return "form-update-karyawan";
    }

    @PostMapping(value = "/karyawan/{idKaryawan}/hapus", params = {"idHapus"})
    public String deleteKaryawanSubmit(@RequestParam("idHapus") String idKaryawan, Model model) {
        Long longID = Long.valueOf(idKaryawan);
        KaryawanModel karyawan = karyawanService.getKaryawan(longID);
        String fullName = karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang();
        model.addAttribute("fullName", fullName);

        KaryawanModel deleteKaryawan = karyawanService.deleteKaryawan(karyawan);
        return "delete-karyawan";
    }

    private static int charToCode(char c) throws IllegalArgumentException {
        // Sumber: https://coderanch.com/t/738297/java/Converting-String-Letters-Numbers
        if (c == ' ') return 0;
        if (c >= 'A' && c <= 'Z') return c - 'A' + 1;
        throw new IllegalArgumentException(
                "Only spaces and capital letters are allowed");
    }

}
