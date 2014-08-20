/**
 *
 */
package net.ramso.dita.beans.admin.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.ramso.dita.beans.tools.ApplicationMessage;
import net.ramso.dita.jpa.Role;
import net.ramso.dita.jpa.User;
import net.ramso.utils.PersistanceManager;

/**
 * @author ramso
 *
 */
@ManagedBean
@ViewScoped
public class UsersBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4139941516134940332L;

	private boolean change;
	private EntityManager em;

	private List<String> selectedRole;
	private List<String> roles;
	private User selected4edit;

	private EntityTransaction tx;

	private List<User> users;

	public void add() {
		change = false;
		System.out.println("Add user");
		final User nUser = new User();
		setSelected4edit(nUser);
	}

	public void addRole() {
		System.out.println("Add role");
		System.out.println("Role ");

	}

	public void changeRole() {
		System.out.println("ChangeRole");
	}

	public void delete() {
		if ((tx != null) && tx.isActive()) {
			tx.rollback();
		}
		em.getTransaction().begin();
		em.remove(getSelected4edit());
		em.getTransaction().commit();

	}

	public void deleteRole() {

	}

	public List<String> getRoles() {
		if (roles == null) {
			if ((tx != null) && tx.isActive()) {
				tx.rollback();
			}
			roles = new ArrayList<String>();
			List<Role> rs = em.createNamedQuery("Role.findAll").getResultList();
			for (Role role : rs) {
				roles.add(role.getRole());
			}
		}
		return roles;
	}

	public User getSelected4edit() {
		if (selected4edit == null) {
			selected4edit = new User();
		}
		return selected4edit;
	}

	public List<User> getUsers() {
		return users;
	}

	@PostConstruct
	public void init() {
		users = new ArrayList<User>();
		em = PersistanceManager.getEntityManager();
		users.addAll(em.createNamedQuery("User.findAll").getResultList());
		change = true;
		selectedRole = new ArrayList<String>();
		if (users.size() > 0) {
			setSelected4edit(users.get(0));
		} else {
			selected4edit = new User();
		}

	}

	public boolean isChange() {
		return change;
	}

	public void save() {
		try {
			if (tx == null) {
				tx = em.getTransaction();
			}
			if (!tx.isActive()) {
				tx.begin();
			}
			getSelected4edit().getRoles().clear();
			for (String rolename : getSelectedRole()) {
				Role rol = em.find(Role.class, rolename);
				getSelected4edit().getRoles().add(rol);
			}
			if (!change) {

				em.persist(getSelected4edit());
			}
			if (tx.isActive()) {
				tx.commit();
			} else {
				ApplicationMessage.setError("Imposible to save the changes",
						null);
			}

			if (!change) {
				getUsers().add(getSelected4edit());
			}

		} catch (final Exception e) {

			ApplicationMessage.setError("Error persisting user "
					+ getSelected4edit().getUserid(), e);
		}
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public void setSelected4edit(User selected4edit) {
		this.selected4edit = selected4edit;
		setSelectedRoles(selected4edit.getRoles());
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void update() {
		if (tx == null) {
			tx = em.getTransaction();
		}

		if (!tx.isActive()) {
			tx.begin();
		}
		change = true;
	}

	public List<String> getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(List<String> selectedRole) {
		this.selectedRole = selectedRole;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRole = new ArrayList<String>();
		if (selectedRoles != null) {
			for (Role role : selectedRoles) {
				selectedRole.add(role.getRole());
			}
		}
	}

}
