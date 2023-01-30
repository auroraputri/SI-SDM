package apap.tugas.sisdm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sertifikasi_karyawan")
public class SertifikasiKaryawanModel {

    //https://www.baeldung.com/jpa-many-to-many
    @EmbeddedId
    SertifikasiKaryawanID idSertifikasiKaryawan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("idKaryawan")
//    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan", nullable = false, insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KaryawanModel karyawan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("idSertifikasi")
//    @JoinColumn(name = "id_sertifikasi", referencedColumnName = "id_sertifikasi", nullable = false, insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SertifikasiModel sertifikasi;

    @NotNull
    @Column(name = "tanggal_pengambilan", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengambilan;

    @NotNull
    @Size(max = 14)
    @Column(name = "no_sertifikasi", nullable = false)
    private String noSertifikasi;
}
