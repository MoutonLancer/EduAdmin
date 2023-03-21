package admin.Utils;

import admin.Utils.Exception.UtilsCreateException;
import javax.crypto.Cipher;
import java.security.*;


public  class RSAUtil {
    private static String algorithm= RSAUtil.Algorithm_RSA;
    private static Integer keyLength= 2048;

    private static Cipher encrypt = null;           //加密器
    private static Cipher decrypt = null;           //解密器
    private static PublicKey publicKey = null;      //公钥
    private static PrivateKey privateKey = null;    //密钥

    public static final String Algorithm_DiffieHellman = "DiffieHellman";
    public static final String Algorithm_DSA = "DSA";
    public static final String Algorithm_RSA = "RSA";
    public static final String Algorithm_EC  = "EC";

    private RSAUtil() throws UtilsCreateException {
        throw new UtilsCreateException("No EduAdmin.Utils.RSA instances for you!");
    }

    static {init();}

    //设置加密算法
    public static void setAlgorithm(String newAlgorithm) throws Exception {
        if(MyUtils.StringEquals(newAlgorithm,"DiffieHellman","DSA","RSA","EC"))
            return;
        algorithm = newAlgorithm;
        init();
    }
    //设置密钥长度
    public static void setKeyLength(Integer length) throws Exception {
        if (length>2048 || length<512)
            return;
        keyLength = length;
        init();
    }
    //加密数据
    public static byte[] encrypt(byte[] plainData) throws Exception {
        return encrypt.doFinal(plainData);
    }
    //解密数据
    public static byte[] decrypt(byte[] cipherData) throws Exception {
        return decrypt.doFinal(cipherData);
    }

    //初始化
    private static void init(){
        try {
            initKeyPair();      //初始化密钥对生成器,并生成密钥对

            encrypt = Cipher.getInstance(algorithm); //初始化加密器
            encrypt.init(Cipher.ENCRYPT_MODE, publicKey);

            decrypt = Cipher.getInstance(algorithm);//初始化解密器
            decrypt.init(Cipher.DECRYPT_MODE, privateKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void initKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance(algorithm);
        gen.initialize(keyLength);
        KeyPair keyPair=  gen.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public static void main(String[] args) throws Exception {
        String s = "1";
        byte [] encrypt = encrypt(s.getBytes());
        String decrypt = new String(decrypt(encrypt));
        System.out.println(decrypt);
    }
}
