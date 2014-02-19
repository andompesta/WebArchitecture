/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iMyTripJsonInterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tlmarco
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement

public class loginParam
{
    @XmlElement
    private String userName;
    @XmlElement
    private String password;
    @XmlElement
    private String device;

    public loginParam()
    {
    }
    
    public loginParam(String userName, String password,String device)
    {
        this.userName = userName;
        this.password = password;
        this.device = device;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDevice()
    {
        return device;
    }

    public void setDevice(String device)
    {
        this.device = device;
    }
}
