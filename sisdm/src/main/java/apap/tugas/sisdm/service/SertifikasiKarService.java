package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;

import java.util.List;
public interface SertifikasiKarService {
    List<SertifikasiKaryawanModel> getListSertifikasiKar();
    List<SertifikasiKaryawanModel> getListSertifikasiKarFiltered(Long idSertifikasi);
    void deleteSertifikasiKar(SertifikasiKaryawanModel seritifKar);
    void saveSertifikasiKar(SertifikasiKaryawanModel seritifKar);
    List<SertifikasiKaryawanModel> getSertifikasiByIdKaryawan(KaryawanModel kar);
}
