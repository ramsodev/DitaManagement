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
import net.ramso.utils.PersistanceManager;

/**
 * @author ramso
 *
 */
@ManagedBean
@ViewScoped
public class RolesBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4139941516134940332L;

	private boolean change;
	private EntityManager em;
	private List<Role> roles;

	private Role selected4edit;

	private EntityTransaction tx;

	public void add() {
		change = false;
		final Role nRole = new Role();
		setSelected4edit(nRole);
	}

	public void delete() {
		if ((tx != null) && tx.isActive()) {
			tx.rollback();
		}
		em.getTransaction().begin();
		em.remove(getSelected4edit());
		em.getTransaction().commit();

	}

	public List<Role> getRoles() {
		return roles;
	}

	public Role getSelected4edit() {
		if (selected4edit == null) {
			selected4edit = new Role();
		}
		return selected4edit;
	}

	@PostConstruct
	public void init() {
		roles = new ArrayList<Role>();
		em = PersistanceManager.getEntityManager();
		roles.addAll(em.createNamedQuery("Role.findAll").getResultList());
		change = true;
	}

	public boolean isChange() {
		return change;
	}

	public void save() {
		try {
			if (!change) {
				if (tx == null) {
					tx = em.getTransaction();
				}
				if (!tx.isActive()) {
					tx.begin();
				}
				em.persist(getSelected4edit());
			}
			System.out.println(getSelected4edit().getRole() + " - "
					+ getSelected4edit().getDescription());
			if (tx.isActive()) {
				tx.commit();
			} else {
				ApplicationMessage.setError(
						"Imposible to save to the database", null);
			}
			if (!change) {
				getRoles().add(getSelected4edit());
			}

		} catch (final Exception e) {

			ApplicationMessage.setError("Error persisting user "
					+ getSelected4edit().getRole(), e);
		}
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setSelected4edit(Role selected4edit) {
		this.selected4edit = selected4edit;
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

}
