package com.secondlife.demo.advertisement;

import com.secondlife.demo.category.ItemCategory;
import com.secondlife.demo.category.PriceCategory;
import com.secondlife.demo.user.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "appearance_date")
    Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserProfile owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_category")
    private PriceCategory priceCategory;

    @OneToMany
    @JoinTable(
            name="advertisement_item_category",
            joinColumns = @JoinColumn(name="advertisement_id"),
            inverseJoinColumns = @JoinColumn( name="item_category_id")
    )
    private Set<ItemCategory> itemCategories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_liked_advertisements",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "advertisement_id"))
    private Set<UserProfile> likedBy;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image_url", joinColumns = @JoinColumn(name = "advertisement_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls;

    public void addLike(UserProfile user) {
        if(likedBy == null) {
            likedBy = new HashSet<>();
        }
        likedBy.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
