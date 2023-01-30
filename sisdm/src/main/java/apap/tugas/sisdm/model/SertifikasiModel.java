package apap.tugas.sisdm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sertifikasi")
public class SertifikasiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Size(max = 20)
    @Column(name = "id_sertifikasi")
    private Long idSertifikasi;

    @NotNull
    @Size(max = 225)
    @Column(name = "nama_sertifikasi", nullable = false)
    private String namaSertifikasi;

    @OneToMany(mappedBy = "sertifikasi")
    List<SertifikasiKaryawanModel> sertifikasiKaryawan;
}

//merged