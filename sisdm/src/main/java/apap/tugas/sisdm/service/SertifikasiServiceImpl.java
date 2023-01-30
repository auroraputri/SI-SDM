package apap.tugas.sisdm.service;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiModel;
import apap.tugas.sisdm.repository.SertifikasiDB;
import apap.tugas.sisdm.repository.TugasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SertifikasiServiceImpl implements SertifikasiService{
    @Autowired
    SertifikasiDB sertifikasiDB;
    @Override
    public List<SertifikasiModel> getListSertifikasi() {
        return sertifikasiDB.findAll();
    }

    @Override
    public SertifikasiModel getSertifikasi(Long id) {
        Optional<SertifikasiModel> sertifikasi = sertifikasiDB.findByIdSertifikasi(id);
        if (sertifikasi.isPresent()) {
            return sertifikasi.get();
        } else {
            return null;
        }
    }
}
