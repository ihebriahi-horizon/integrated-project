package com.example.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "role")
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;

	@Column(name = "role_name")
	private String roleName;
	@JsonIgnore
	@OneToMany(targetEntity = User.class, mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> users;

	public Role() {

	}

	public Role(int roleId, String roleName, List<User> users) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
	}

	public int getId() {
		return roleId;
	}

	public void setId(int id) {
		this.roleId = id;
	}

	public String getName() {
		return roleName;
	}

	public void setName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + roleId + ", name=" + roleName + ", users=" + users + "]";
	}

}