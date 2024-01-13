import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Wf2 {

    public static void main(String[] args) {
        Wf2 solution = new Wf2();
        System.out.println(solution.rangeAssociation(new String[]{"128", "12", "34", "0"}));
    }

    private int rangeAssociation(String[] ipAddress) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String address : ipAddress) {
            int value = Integer.parseInt(address);
            if (value < 0 || value > 255) return -1;
            stringBuilder.append(String.format("%03d", value));
        }

        BigInteger newIpAddress = new BigInteger(stringBuilder.toString());
        RangeDefinitionList definitionList = new RangeDefinitionList();
        return definitionList.compareTo(newIpAddress);

    }

    private class RangeDefinition {
        BigInteger start;
        BigInteger end;

        public RangeDefinition(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }

    }

    private class RangeDefinitionList {
        List<RangeDefinition> rangeDefinitions;

        public RangeDefinitionList() {
            this.rangeDefinitions = new ArrayList<>();
            this.rangeDefinitions.add(new RangeDefinition(new BigInteger("0"), new BigInteger("127255255255")));
            this.rangeDefinitions.add(new RangeDefinition(new BigInteger("128000000000"), new BigInteger("191255255255")));
            this.rangeDefinitions.add(new RangeDefinition(new BigInteger("192000000000"), new BigInteger("223255255255")));
            this.rangeDefinitions.add(new RangeDefinition(new BigInteger("224000000000"), new BigInteger("239255255255")));
            this.rangeDefinitions.add(new RangeDefinition(new BigInteger("240000000000"), new BigInteger("255255255255")));
        }

        public int compareTo(BigInteger ipAddress) {
            for (int i = 0; i < rangeDefinitions.size(); i++) {
                RangeDefinition definition = rangeDefinitions.get(i);
                if ((ipAddress.compareTo(definition.start) > 0 && ipAddress.compareTo(definition.end) < 0) || (ipAddress.equals(definition.start) || ipAddress.equals(definition.end))) {
                    return i;
                }
            }
            return -1;
        }
    }


}
