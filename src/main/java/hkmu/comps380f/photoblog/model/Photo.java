package hkmu.comps380f.photoblog.model;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String content;
    private String description;
    private String uploader;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime uploadTime;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_user.id")
    private BlogUser user;
    @OneToMany(mappedBy = "photo", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {MERGE, REFRESH, DETACH})
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 20)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public String getUploadTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm:ss");
        return uploadTime.format(formatter);
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public BlogUser getUser() {
        return user;
    }

    public void setUser(BlogUser blogUser) {
        this.user = blogUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}













