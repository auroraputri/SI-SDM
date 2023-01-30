package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;

import java.time.LocalDateTime;
import java.util.List;
public interface KaryawanService {
    void addKaryawan(KaryawanModel karyawan);
    List<KaryawanModel> getListKaryawan();
    KaryawanModel getKaryawan(Long idKaryawan);
    KaryawanModel updateKaryawan(KaryawanModel karyawan);
    KaryawanModel deleteKaryawan(KaryawanModel karyawan);
    List<SertifikasiKaryawanModel> getListSertifikasiKaryawan(Long id);


}
