package net.ramso.dita.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T20:36:05.345+0200")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, String> role;
	public static volatile SingularAttribute<Role, String> description;
	public static volatile ListAttribute<Role, User> users;
}
