package net.ramso.dita.beans.admin;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.ramso.utils.Messages;

@ManagedBean
@ViewScoped
public class ConfigEditorBean  extends AbstractConfigEditor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5161871701960982098L;

	@ManagedProperty("#{adminBean}")
	private AdminBean adminBean;
	

	public AdminBean getAdminBean() {
		return adminBean;
	}

	
	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}

	public void setKey(String key) {
		adminBean.setType("config");
		name = Messages.getString(key);
		this.key = key;
		configs = null;
	}

	

}
