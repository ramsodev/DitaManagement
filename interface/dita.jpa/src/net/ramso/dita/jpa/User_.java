package net.ramso.dita.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T20:36:05.373+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> userid;
	public static volatile SingularAttribute<User, byte[]> avatar;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> theme;
	public static volatile ListAttribute<User, Role> roles;
}
