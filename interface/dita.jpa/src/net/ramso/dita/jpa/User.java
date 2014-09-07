package net.ramso.dita.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String userid;

	@Lob
	private byte[] avatar;

	@Column(length=255)
	private String email;

	@Column(length=255)
	private String name;

	@Column(nullable=false, length=20)
	private String password;

	@Column(length=50)
	private String theme;

	//bi-directional many-to-many association to Role
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
		name="USERROLES"
		, joinColumns={
			@JoinColumn(name="USERID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE", nullable=false)
			}
		)
	private List<Role> roles;

	public User() {
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public byte[] getAvatar() {
		return this.avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}