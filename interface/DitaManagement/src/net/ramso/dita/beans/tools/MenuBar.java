package net.ramso.dita.beans.tools;

import javax.faces.bean.ManagedBean;

import net.ramso.utils.ConfManager;
import net.ramso.utils.Messages;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class MenuBar {
	private MenuModel model;

	public MenuBar() {

		model = new DynamicMenuModel();
		
		DefaultMenuItem item =new DefaultMenuItem(""); //$NON-NLS-1$
		item.setUrl("home.xhtml"); //$NON-NLS-1$
		item.setIcon("ui-icon-home"); //$NON-NLS-1$
		model.addElement(item);
		model.addElement(getConfigMenu());

		item = new DefaultMenuItem("logout"); //$NON-NLS-1$
		item.setCommand("#{loginBean.logout}"); //$NON-NLS-1$
		item.setUpdate(":messages"); //$NON-NLS-1$
		item.setIcon("ui-icon-power"); //$NON-NLS-1$
		model.addElement(item);
	}

	private MenuElement getConfigMenu() {
		DefaultSubMenu submenu = new DefaultSubMenu(Messages.getString("menu.config.title"));		 //$NON-NLS-1$
		submenu.setIcon("ui-icon-wrench"); //$NON-NLS-1$
		for (String key : ConfManager.getInstance().getPropertiesKeys()) {
			DefaultMenuItem item = new DefaultMenuItem(Messages.getString(key));
			item.setUrl("config.xhtml?name="+key);
			item.setIcon("ui-icon-gear");
			submenu.addElement(item);
		}
		return submenu;
	}

	public MenuModel getModel() {
		return model;
	}

}
