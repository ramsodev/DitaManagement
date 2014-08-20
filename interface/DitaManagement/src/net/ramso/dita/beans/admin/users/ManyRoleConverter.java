package net.ramso.dita.beans.admin.users;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.ramso.dita.jpa.Role;
import net.ramso.utils.PersistanceManager;

@FacesConverter("manyRoleConverter")
public class ManyRoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		System.out.println("Converter " + value);
		if (value != null && value.trim().length() > 0) {
			Role role = PersistanceManager.getEntityManager().find(Role.class,
					value);
			System.out.println(role.getRole());
			return role;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		System.out.println("object" + object.getClass().getCanonicalName());
		if (object != null) {
			String value = "";
			if (object instanceof Role) {
				Role role = ((Role) object);
				value = role.getRole();
			} else {
				value = object.toString();
			}
			return value;
		} else {
			return null;
		}
	}

}
