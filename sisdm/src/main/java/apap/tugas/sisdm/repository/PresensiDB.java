package apap.tugas.sisdm.repository;

import apap.tugas.sisdm.model.PresensiModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.model.TugasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PresensiDB extends JpaRepository<PresensiModel, String>{
    //JPA
    Optional<PresensiModel> findByIdPresensi(Long idPresensi);
    @Query(value = "SELECT * FROM `tugas` WHERE id_karyawan =:idKaryawan", nativeQuery = true)
    List<TugasModel> getListTugas(Long idKaryawan);
}
