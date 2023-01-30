package apap.tugas.sisdm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(name = "tugas")
public class TugasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Size(max = 20)
    @Column(name = "id_tugas")
    private Long idTugas;

    @NotNull
    @Size(max = 225)
    @Column(name = "nama", nullable = false)
    private String namaTugas;

    @NotNull
    @Size(max = 225)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsiTugas;

    @NotNull
    @Column(name = "storypoint", nullable = false)
    private int storypoint;

    @NotNull
    @Column(name = "status", nullable = false)
    private int statusTugas;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_presensi", referencedColumnName = "id_presensi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PresensiModel presensi;
}
