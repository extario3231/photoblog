package hkmu.comps380f.photoblog.model;

import jakarta.persistence.*;

import static jakarta.persistence.CascadeType.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo.id")
    private Photo photo;
    private String username;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
