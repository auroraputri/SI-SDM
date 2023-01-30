package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.model.SertifikasiModel;

import java.util.List;

public interface SertifikasiService {
    List<SertifikasiModel> getListSertifikasi();
    SertifikasiModel getSertifikasi(Long id);
}
