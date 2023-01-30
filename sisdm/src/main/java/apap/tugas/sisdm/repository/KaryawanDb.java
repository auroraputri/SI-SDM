package apap.tugas.sisdm.repository;

import apap.tugas.sisdm.model.KaryawanModel;
import apap.tugas.sisdm.model.SertifikasiKaryawanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface KaryawanDb extends JpaRepository<KaryawanModel, String>{
    //JPA
    Optional<KaryawanModel> findByIdKaryawan(Long idKaryawan);
    @Query(value = "SELECT * FROM `sertifikasi_karyawan` WHERE karyawan_id_karyawan =:idKaryawan", nativeQuery = true)
    List<SertifikasiKaryawanModel> getListSertifikasi(Long idKaryawan);


}
