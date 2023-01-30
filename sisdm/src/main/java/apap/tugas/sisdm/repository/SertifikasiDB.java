package apap.tugas.sisdm.repository;

import apap.tugas.sisdm.model.SertifikasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SertifikasiDB extends JpaRepository<SertifikasiModel, String>{
    //JPA
    Optional<SertifikasiModel> findByIdSertifikasi(Long idSertifikasi);
}
