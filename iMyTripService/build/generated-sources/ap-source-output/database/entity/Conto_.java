package database.entity;

import database.entity.Account;
import database.entity.Movimento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-16T15:06:04")
@StaticMetamodel(Conto.class)
public class Conto_ { 

    public static volatile SingularAttribute<Conto, String> codiceCarta;
    public static volatile ListAttribute<Conto, Movimento> movimentoList;
    public static volatile SingularAttribute<Conto, String> tipo;
    public static volatile SingularAttribute<Conto, Account> userName;
    public static volatile SingularAttribute<Conto, Float> saldo;
    public static volatile SingularAttribute<Conto, Integer> idConto;

}