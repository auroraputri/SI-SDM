package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.PresensiModel;
import apap.tugas.sisdm.model.TugasModel;

import java.util.List;
public interface PresensiService {
    void addPresensi(PresensiModel presensi);
    List<PresensiModel> getListPresensi();
    List<TugasModel> getListTugas(Long id);
    PresensiModel getPresensi(Long id);
    PresensiModel updatePresensi(PresensiModel presensi);
}
