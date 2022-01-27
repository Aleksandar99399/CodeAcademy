package bg.codeacademy.spring.gossiptalks.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User
{
   private Long         id;
   private String       username;
   private String       password;
   private String       email;
   private String       name;
   private Boolean      following;
   private List<Gossip> gossips = new ArrayList<>();
   private Set<User>    friends = new HashSet<>();

   public User(Long id, String username, String password, String email, String name)
   {
      this.id = id;
      this.username = username;
      this.password = password;
      this.email = email;
      this.name = name;
   }

   public User()
   {
   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }

   public User setId(Long id)
   {
      this.id = id;
      return this;
   }

   @Pattern(regexp = "^[a-z0-9\\.\\-]+$", message = "Username is not valid! (a-z0-9.-)")
   @Length(min = 4, message = "Username must be least 4 symbols")
   @NotNull
   @Column(unique = true)
   public String getUsername()
   {
      return username;
   }

   public User setUsername(String username)
   {
      this.username = username;
      return this;
   }

   public String getPassword()
   {
      return password;
   }

   public User setPassword(String password)
   {
      this.password = password;
      return this;
   }

   @Email(message = "Email is not valid")
   public String getEmail()
   {
      return email;
   }

   public User setEmail(String email)
   {
      this.email = email;
      return this;
   }

   @Transient
   public Boolean getFollowing()
   {
      return following;
   }

   public User setFollowing(Boolean following)
   {
      this.following = following;
      return this;
   }

   @Length(max = 50, message = "Name is too long")
   public String getName()
   {
      return name;
   }

   public User setName(String name)
   {
      this.name = name;
      return this;
   }

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_gossips",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "gossip_id"))
   public List<Gossip> getGossips()
   {
      return gossips;
   }

   public User setGossips(List<Gossip> gossips)
   {
      this.gossips = gossips;
      return this;
   }


   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_friends",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "friends_id"))
   public Set<User> getFriends()
   {
      return friends;
   }

   public User setFriends(Set<User> friends)
   {
      this.friends = friends;
      return this;
   }


}
