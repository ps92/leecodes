import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IPAddressRangeChecker {

    public static void main(String[] args) {
        IPAddressRangeChecker solution = new IPAddressRangeChecker();
        System.out.println(solution.getRangeIndex("128.12.34.0"));
    }

    final int IP_START = 0;
    final int IP_END = 255;

    private int getRangeIndex(String ipAddress) {
        String [] ipAddressParts = ipAddress.split("\\.");

        StringBuilder ipStringBuilder = new StringBuilder();
        for (String part : ipAddressParts) {
            int partValue = Integer.parseInt(part);
            if (partValue < IP_START || partValue > IP_END) return -1;
            ipStringBuilder.append(String.format("%03d", partValue));
        }

        BigInteger ipAddressBigInt = new BigInteger(ipStringBuilder.toString());
        return RangeList.INSTANCE.compareTo(ipAddressBigInt) + 1;
    }

    private static class Range {
        BigInteger start;
        BigInteger end;

        Range(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }
    }

    private enum RangeList {
        INSTANCE;

        List<Range> ranges;

        RangeList() {
            this.ranges = new ArrayList<>();

            this.ranges.add(new Range(BigInteger.valueOf(0L), new BigInteger("127255255255")));
            this.ranges.add(new Range(BigInteger.valueOf(128000000000L), new BigInteger("191255255255")));
            this.ranges.add(new Range(BigInteger.valueOf(192000000000L), new BigInteger("223255255255")));
            this.ranges.add(new Range(BigInteger.valueOf(224000000000L), new BigInteger("239255255255")));
            this.ranges.add(new Range(BigInteger.valueOf(240000000000L), new BigInteger("255255255255")));
        }

        public int compareTo(BigInteger ipAddress) {
            for (int i = 0; i < ranges.size(); i++) {
                Range range = ranges.get(i);
                if (ipAddress.compareTo(range.start) >= 0 && ipAddress.compareTo(range.end) <= 0) {
                    return i;
                }
            }
            return -1;
        }
    }
}
