package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.TugasModel;

import java.util.List;
public interface TugasService {
    void addTugas(TugasModel tugas);
    List<TugasModel> getListTugas();
    List<TugasModel> getListTugasFiltered(Long idKaryawan, int status);
    TugasModel getTugasById(Long id);
    void updateTugas(TugasModel tugas);

}
