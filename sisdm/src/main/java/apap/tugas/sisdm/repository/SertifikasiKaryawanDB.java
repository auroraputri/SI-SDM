package apap.tugas.sisdm.repository;

import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import apap.tugas.sisdm.model.TugasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SertifikasiKaryawanDB extends JpaRepository<SertifikasiKaryawanModel, String>{
    //JPA
    Optional<SertifikasiKaryawanModel> findByIdSertifikasiKaryawan(String idSertifikasiKaryawan);

    @Query(value = "SELECT * FROM `sertifikasi_karyawan` WHERE sertifikasi_id_sertifikasi =:idSertifikasi", nativeQuery = true)
    List<SertifikasiKaryawanModel> findBySertif(@Param("idSertifikasi") Long idSertifikasi);

    @Query(value = "SELECT * FROM `sertifikasi_karyawan` WHERE karyawan_id_karyawan =:id", nativeQuery = true)
    List<SertifikasiKaryawanModel> findByIdKar(@Param("id") Long id);


}
