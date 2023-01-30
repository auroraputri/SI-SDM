package apap.tugas.sisdm.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanDb karyawanDb;
    @Override
    public void addKaryawan(KaryawanModel karyawan){
        karyawanDb.save(karyawan);
    }

    @Override
    public List<KaryawanModel> getListKaryawan(){
        return karyawanDb.findAll();
    }

    @Override
    public KaryawanModel getKaryawan(Long id){
        Optional<KaryawanModel> karyawan = karyawanDb.findByIdKaryawan(id);
        if(karyawan.isPresent()){
            return karyawan.get();
        } else{
            return null;
        }
    }

    @Override
    public KaryawanModel updateKaryawan(KaryawanModel karyawan){
        karyawanDb.save(karyawan);
        return karyawan;
    }

    @Override
    public KaryawanModel deleteKaryawan(KaryawanModel karyawan){
        karyawanDb.delete(karyawan);
        return karyawan;
    }

    @Override
    public List<SertifikasiKaryawanModel> getListSertifikasiKaryawan(Long id) {
        return karyawanDb.getListSertifikasi(id);
    }
}
