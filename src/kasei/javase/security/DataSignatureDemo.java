/** TODO Java 中的数据签名 */
public class DataSignatureDemo {
    try {
        Signature signature = Signature.getInstance("SHA384withECDSA");
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
}
