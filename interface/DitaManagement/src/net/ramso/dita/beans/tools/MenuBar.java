package net.ramso.dita.beans.tools;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DynamicMenuModel;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class MenuBar implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6322913289881074721L;
	private final MenuModel model;

	public MenuBar() {

		model = new DynamicMenuModel();

		DefaultMenuItem item = new DefaultMenuItem(""); //$NON-NLS-1$
		item.setUrl("/home.xhtml"); //$NON-NLS-1$
		item.setIcon("ui-icon-home"); //$NON-NLS-1$
		model.addElement(item);

		item = new DefaultMenuItem("Admin"); //$NON-NLS-1$
		item.setUrl("/admin/admin.xhtml"); //$NON-NLS-1$
		item.setIcon("ui-icon-wrench"); //$NON-NLS-1$
		model.addElement(item);
		item = new DefaultMenuItem("logout"); //$NON-NLS-1$
		item.setCommand("#{loginBean.logout}"); //$NON-NLS-1$
		item.setUpdate(":messages"); //$NON-NLS-1$
		item.setIcon("ui-icon-power"); //$NON-NLS-1$
		model.addElement(item);
	}

	public MenuModel getModel() {
		return model;
	}

}
