import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.net.InetAddress;



public class main {

    public static void main(String args[]) throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        List<NetworkInterface> interfaceList = new ArrayList<>();

        while(interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            interfaceList.add(networkInterface);
        }

        System.out.println("found " + interfaceList.size() + " network interface.");

        for (NetworkInterface ni : interfaceList) {
            if (ni.isUp()) {
                System.out.println(ni.getName());
                byte[] mac = ni.getHardwareAddress();
                if (mac == null) {
                    System.out.println(ni.getName() + " as no MAC address.");
                    continue;
                }
                for (int i = 0; i < mac.length; i++) {
                    if (i > 0) {
                        System.out.print(":");
                    }

                    System.out.printf("%02x", mac[i] & 0xff);
                }

                System.out.println();

                Enumeration<InetAddress> addresses = ni.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    System.out.println(address.getHostAddress()
                    + " |IPv4=" + (address instanceof java.net.Inet4Address)
                    + " |IPv6=" + (address instanceof java.net.Inet6Address)
                    + " | loopback=" + address.isLoopbackAddress()
                    + " | linkLocal=" + address.isLinkLocalAddress()
                    );
                }
            }
            
        }
    }
}