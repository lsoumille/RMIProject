package demormi;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * @author Lucas Martinez
 * @version 19/05/16.
 */
public class RemoteObj implements Remote, Serializable {
    private int n;

    public RemoteObj(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "RemoteObj: n = " + n;
    }
}
