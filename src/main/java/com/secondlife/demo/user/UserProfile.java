package com.secondlife.demo.user;

import com.secondlife.demo.advertisement.Advertisement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LASTNAME")
    private String userLastname;

    @Column(name = "RADIUS")
    private Double radius;

//    @OneToMany
//    @JoinTable(
//            name = "user_liked_advertisements",
//            joinColumns = @JoinColumn(name = "advertisement_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<Advertisement> advertisements;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_liked_advertisements",
            joinColumns = @JoinColumn(name = "advertisement_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Advertisement> likedAdvertisements;


    public void likeAdvert(Advertisement ad) {
        if(likedAdvertisements == null) {
            likedAdvertisements = new HashSet<>();
        }
        likedAdvertisements.add(ad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
