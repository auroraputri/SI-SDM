package apap.tugas.sisdm.repository;

import apap.tugas.sisdm.model.SertifikasiModel;
import apap.tugas.sisdm.model.TugasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TugasDB extends JpaRepository<TugasModel, String>{
    //JPA
    Optional<TugasModel> findByIdTugas(Long idTugas);

    @Query(value = "SELECT t.id_tugas, t.deskripsi, t.nama, t.status, t.storypoint, t.id_presensi FROM `tugas` as t INNER JOIN `presensi` as p ON t.id_presensi = p.id_presensi INNER JOIN `karyawan` as k ON p.id_karyawan = k.id_karyawan WHERE k.id_karyawan =:idKaryawan AND t.status=:status" , nativeQuery = true)
    List<TugasModel> findByKaryawanStatus(@Param("idKaryawan") Long idKaryawan,@Param("status") int status);
}
