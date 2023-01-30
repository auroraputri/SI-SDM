package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.repository.SertifikasiKaryawanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SertifikasiKarServiceImpl implements SertifikasiKarService {
    @Autowired
    SertifikasiKaryawanDB sertifikasiKaryawanDB;

    @Override
    public List<SertifikasiKaryawanModel> getListSertifikasiKar() {
        return sertifikasiKaryawanDB.findAll();
    }

    @Override
    public List<SertifikasiKaryawanModel> getListSertifikasiKarFiltered(Long idSertifikasi) {
        return sertifikasiKaryawanDB.findBySertif(idSertifikasi);
    }
    @Override
    public void deleteSertifikasiKar(SertifikasiKaryawanModel seritifKar){
        sertifikasiKaryawanDB.delete(seritifKar);
    }
    @Override
    public void saveSertifikasiKar(SertifikasiKaryawanModel seritifKar){
        sertifikasiKaryawanDB.save(seritifKar);
    }

    @Override
    public List<SertifikasiKaryawanModel> getSertifikasiByIdKaryawan(KaryawanModel kar){
        return sertifikasiKaryawanDB.findByIdKar(kar.getIdKaryawan());
    }
}
