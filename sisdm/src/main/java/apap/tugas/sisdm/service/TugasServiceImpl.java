package apap.tugas.sisdm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.PresensiModel;
import apap.tugas.sisdm.model.TugasModel;
import apap.tugas.sisdm.repository.KaryawanDb;
import apap.tugas.sisdm.repository.PresensiDB;
import apap.tugas.sisdm.repository.TugasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TugasServiceImpl implements TugasService{
    @Autowired
    TugasDB tugasDB;

    @Override
    public void addTugas(TugasModel tugas) {
        tugasDB.save(tugas);
    }
    @Override
    public void updateTugas(TugasModel tugas){
        tugasDB.save(tugas);
    }

    @Override
    public List<TugasModel> getListTugas() {
        return tugasDB.findAll();
    }

    @Override
    public List<TugasModel> getListTugasFiltered(Long idKaryawan, int status) {
        return tugasDB.findByKaryawanStatus(idKaryawan, status);
    }

    @Override
    public TugasModel getTugasById(Long id){
        Optional<TugasModel> tugas = tugasDB.findByIdTugas(id);
        if(tugas.isPresent()){
            return tugas.get();
        } else{
            return null;
        }
    }
}
