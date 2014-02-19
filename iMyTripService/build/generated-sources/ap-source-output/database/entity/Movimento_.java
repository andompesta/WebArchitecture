package database.entity;

import database.entity.Conto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-01-16T15:06:04")
@StaticMetamodel(Movimento.class)
public class Movimento_ { 

    public static volatile SingularAttribute<Movimento, String> descrizione;
    public static volatile SingularAttribute<Movimento, Integer> idMovimento;
    public static volatile SingularAttribute<Movimento, String> data;
    public static volatile SingularAttribute<Movimento, Float> importo;
    public static volatile SingularAttribute<Movimento, Conto> idConto;

}