package net.ramso.dita.beans.admin;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.ramso.utils.ConfManager;
import net.ramso.utils.Messages;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@ViewScoped
public class AdminMenu implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6322913289881074721L;
	private final MenuModel model;

	public AdminMenu() {

		model = new DynamicMenuModel();

		model.addElement(getSetupMenu());
		model.addElement(getSecurityMenu());

		model.addElement(getPropertiesMenu());

	}

	private MenuElement getPropertiesMenu() {
		final DefaultSubMenu submenu = new DefaultSubMenu(
				Messages.getString("menu.config.title")); //$NON-NLS-1$
		submenu.setIcon("ui-icon-wrench"); //$NON-NLS-1$
		for (final String key : ConfManager.getInstance().getPropertiesKeys()) {
			final DefaultMenuItem item = new DefaultMenuItem(
					Messages.getString(key));
			item.setUpdate(":messages :form");
			item.setCommand("#{configEditorBean.setKey('" + key + "')}");
			item.setIcon("ui-icon-gear");
			submenu.addElement(item);
		}
		return submenu;
	}

	public MenuModel getModel() {
		return model;
	}

	private MenuElement getSecurityMenu() {
		final DefaultSubMenu submenu = new DefaultSubMenu("Security"); //$NON-NLS-1$
		submenu.setIcon("ui-icon-locked"); //$NON-NLS-1$
		DefaultMenuItem item = new DefaultMenuItem("Realms");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('realm')}");
		item.setIcon("ui-icon-gear");
		submenu.addElement(item);
		item = new DefaultMenuItem("Users");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('users')}");
		item.setIcon("ui-icon-person");
		submenu.addElement(item);
		item = new DefaultMenuItem("Roles");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('roles')}");
		item.setIcon("ui-icon-contact");
		submenu.addElement(item);

		return submenu;
	}

	private MenuElement getSetupMenu() {
		final DefaultSubMenu submenu = new DefaultSubMenu("Setup"); //$NON-NLS-1$
		submenu.setIcon("ui-icon-wrench"); //$NON-NLS-1$
		DefaultMenuItem item = new DefaultMenuItem("Log");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('log')}");
		item.setIcon("ui-icon-script");
		submenu.addElement(item);
		item = new DefaultMenuItem("Repository");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('repo')}");
		item.setIcon("ui-icon-transfer-e-w");
		submenu.addElement(item);
		item = new DefaultMenuItem("Database");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('jta')}");
		item.setIcon("ui-icon-suitcase");
		submenu.addElement(item);
		item = new DefaultMenuItem("Search index");
		item.setUpdate(":messages :form");
		item.setCommand("#{adminBean.setType('index')}");
		item.setIcon("ui-icon-tag");
		submenu.addElement(item);

		return submenu;
	}
}
