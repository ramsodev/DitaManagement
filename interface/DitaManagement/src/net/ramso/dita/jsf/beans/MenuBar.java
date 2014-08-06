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
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
		DefaultMenuItem item = new DefaultMenuItem("External");
		item.setUrl("http://www.primefaces.org");
		item.setIcon("ui-icon-home");
		firstSubmenu.addElement(item);
		model.addElement(firstSubmenu);

		// Second submenu
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
		item = new DefaultMenuItem("Save");
		item.setIcon("ui-icon-disk");
		item.setCommand("#{menuBean.save}");
		item.setUpdate(":messages");
		secondSubmenu.addElement(item);
		item = new DefaultMenuItem("Delete");
		item.setIcon("ui-icon-close");
		item.setCommand("#{menuBean.delete}");
		item.setAjax(false);
		secondSubmenu.addElement(item);
		item = new DefaultMenuItem("Redirect");
		item.setIcon("ui-icon-search");
		item.setCommand("#{menuBean.redirect}");
		secondSubmenu.addElement(item);
		model.addElement(secondSubmenu);
		item = new DefaultMenuItem("logout");
		item.setCommand("#{loginBean.logout}");
		item.setUpdate(":messages");
		model.addElement(item);
	}

	public MenuModel getModel() {
		return model;
	}

}
