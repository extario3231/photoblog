package hkmu.comps380f.photoblog.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo.id")
    private Photo photo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
