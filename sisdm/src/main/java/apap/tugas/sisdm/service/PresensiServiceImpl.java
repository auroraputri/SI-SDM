package apap.tugas.sisdm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.PresensiModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.model.TugasModel;
import apap.tugas.sisdm.repository.KaryawanDb;
import apap.tugas.sisdm.repository.PresensiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PresensiServiceImpl implements PresensiService{
    @Autowired
    PresensiDB presensiDB;

    @Override
    public void addPresensi(PresensiModel presensi){
        presensiDB.save(presensi);
    }

    @Override
    public List<PresensiModel> getListPresensi(){
        return presensiDB.findAll();
    }

    @Override
    public PresensiModel getPresensi(Long id){
        Optional<PresensiModel> presensi = presensiDB.findByIdPresensi(id);
        if(presensi.isPresent()){
            return presensi.get();
        } else{
            return null;
        }
    }

    @Override
    public PresensiModel updatePresensi(PresensiModel presensi){
        presensiDB.save(presensi);
        return presensi;
    }
    @Override
    public List<TugasModel> getListTugas(Long id) {
        return presensiDB.getListTugas(id);
    }
}
