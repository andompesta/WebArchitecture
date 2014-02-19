package database.entity;

import database.entity.Conto;
import database.entity.LocalitaPrivata;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-16T15:06:04")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> mail;
    public static volatile ListAttribute<Account, Conto> contoList;
    public static volatile SingularAttribute<Account, String> dataNascita;
    public static volatile SingularAttribute<Account, String> userName;
    public static volatile SingularAttribute<Account, String> nome;
    public static volatile SingularAttribute<Account, String> cognome;
    public static volatile ListAttribute<Account, LocalitaPrivata> localitaPrivataList;
    public static volatile SingularAttribute<Account, String> pass;
    public static volatile SingularAttribute<Account, Boolean> abilitato;

}