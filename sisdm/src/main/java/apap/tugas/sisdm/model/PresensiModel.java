package apap.tugas.sisdm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"karyawan"}, allowSetters = true)
@Table(name = "presensi")
public class PresensiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presensi", nullable = false)
    private Long idPresensi;

    @NotNull
    @Column(name = "status", nullable = false)
    private int statusPresensi;

    @NotNull
    @Column(name = "tanggal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPresensi;

    @NotNull
    @Column(name = "waktu_masuk", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuMasuk;

    @NotNull
    @Column(name = "waktu_keluar", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuKeluar;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KaryawanModel karyawan;

    @OneToMany(mappedBy = "presensi", fetch = FetchType.LAZY)
    private List<TugasModel> listTugas;

}
