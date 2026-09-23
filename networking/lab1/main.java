import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class main {

    public static void main(String args[]) throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        List<NetworkInterface> interfaceList = new ArrayList<>();

        while(interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            interfaceList.add(networkInterface);
        }

        System.out.println("found " + interfaceList.size() + " network interface.");

    }
}