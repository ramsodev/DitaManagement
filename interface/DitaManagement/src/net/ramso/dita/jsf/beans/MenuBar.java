package net.ramso.dita.jsf.beans;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class MenuBar {
	private MenuModel model;

	public MenuBar() {

		model = new DynamicMenuModel();
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Configuration");
		DefaultMenuItem item = new DefaultMenuItem("Configuration");
		item.setUrl("config.xhtml");
		
		item.setIcon("ui-icon-setup");
		firstSubmenu.addElement(item);
		model.addElement(firstSubmenu);

		item = new DefaultMenuItem("logout");
		item.setCommand("#{loginBean.logout}");
		item.setUpdate(":messages");
		model.addElement(item);
	}

	public MenuModel getModel() {
		return model;
	}

}
